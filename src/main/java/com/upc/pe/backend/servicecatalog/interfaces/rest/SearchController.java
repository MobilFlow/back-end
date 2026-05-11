package com.upc.pe.backend.servicecatalog.interfaces.rest;

import com.upc.pe.backend.servicecatalog.domain.model.queries.SearchServicesQuery;
import com.upc.pe.backend.servicecatalog.domain.services.ServiceQueryService;
import com.upc.pe.backend.servicecatalog.interfaces.rest.resources.ServiceResource;
import com.upc.pe.backend.servicecatalog.interfaces.rest.transform.ServiceResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * SearchController
 *
 * REST controller responsible
 * for service searching endpoints.
 */
@RestController
@RequestMapping("/api/v1/search")
@Tag(name = "Search")
public class SearchController {

    private final ServiceQueryService serviceQueryService;

    public SearchController(
            ServiceQueryService serviceQueryService
    ) {
        this.serviceQueryService = serviceQueryService;
    }

    @GetMapping
    public ResponseEntity<List<ServiceResource>> searchServices(
            @RequestParam String keyword
    ) {

        var query = new SearchServicesQuery(keyword);

        var services = serviceQueryService.handle(query);

        var resources = services.stream()
                .map(ServiceResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }
}