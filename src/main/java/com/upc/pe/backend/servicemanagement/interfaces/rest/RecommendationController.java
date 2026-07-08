package com.upc.pe.backend.servicemanagement.interfaces.rest;

import com.upc.pe.backend.servicemanagement.domain.services.DiagnosisGeneratorService;
import com.upc.pe.backend.servicemanagement.domain.services.MatchingService;
import com.upc.pe.backend.servicemanagement.interfaces.rest.resources.AnalysisRequestResource;
import com.upc.pe.backend.servicemanagement.interfaces.rest.resources.AnalysisResponseResource;
import com.upc.pe.backend.servicemanagement.interfaces.rest.resources.DiagnosisResource;
import com.upc.pe.backend.servicemanagement.interfaces.rest.resources.RecommendationResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Recommendations")
public class RecommendationController {

    private final MatchingService matchingService;
    private final DiagnosisGeneratorService diagnosisGeneratorService;

    public RecommendationController(MatchingService matchingService, DiagnosisGeneratorService diagnosisGeneratorService) {
        this.matchingService = matchingService;
        this.diagnosisGeneratorService = diagnosisGeneratorService;
    }

    @GetMapping("/service-requests/{serviceRequestId}/recommendations")
    @Operation(summary = "Get mechanic recommendations for a service request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recommendations retrieved successfully."),
            @ApiResponse(responseCode = "404", description = "Service request or diagnosis not found.")
    })
    public ResponseEntity<List<RecommendationResource>> getRecommendations(
            @PathVariable Long serviceRequestId
    ) {
        var recommendations = matchingService.getRecommendations(serviceRequestId);
        return ResponseEntity.ok(recommendations);
    }

    @PostMapping("/recommendations/analyze")
    @Operation(summary = "Analyze symptoms and get recommendations without persisting data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Analysis and recommendations generated successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid request (symptoms empty or null).")
    })
    public ResponseEntity<AnalysisResponseResource> analyzeSymptoms(@RequestBody AnalysisRequestResource resource) {
        if (resource.symptoms() == null || resource.symptoms().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        String recommendedSpecialty = diagnosisGeneratorService.recommendSpecialty(resource.symptoms());
        String summary = diagnosisGeneratorService.generateSummary(resource.symptoms(), recommendedSpecialty);

        DiagnosisResource diagnosisResource = new DiagnosisResource(0L, 0L, summary, recommendedSpecialty, new java.util.Date());
        List<RecommendationResource> recommendations = matchingService.getRecommendationsBySpecialty(recommendedSpecialty);

        AnalysisResponseResource response = new AnalysisResponseResource(diagnosisResource, recommendations);
        return ResponseEntity.ok(response);
    }
}
