package com.upc.pe.backend.servicemanagement.domain.services;

import com.upc.pe.backend.servicemanagement.domain.model.aggregates.ServiceRequest;
import com.upc.pe.backend.servicemanagement.domain.model.commands.CancelServiceCommand;
import com.upc.pe.backend.servicemanagement.domain.model.commands.ConfirmServiceCompletionCommand;
import com.upc.pe.backend.servicemanagement.domain.model.commands.RequestServiceCommand;

import java.util.Optional;

public interface ServiceRequestCommandService {

    Optional<ServiceRequest> handle(RequestServiceCommand command);

    Optional<ServiceRequest> handle(ConfirmServiceCompletionCommand command);

    Optional<ServiceRequest> handle(CancelServiceCommand command);
}
