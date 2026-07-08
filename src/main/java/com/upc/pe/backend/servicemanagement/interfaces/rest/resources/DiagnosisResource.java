package com.upc.pe.backend.servicemanagement.interfaces.rest.resources;

import java.util.Date;

public record DiagnosisResource(Long id, Long serviceRequestId, String summary, String recommendedSpecialty, Date createdAt) {
}
