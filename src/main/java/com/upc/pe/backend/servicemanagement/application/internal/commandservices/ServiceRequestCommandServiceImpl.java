package com.upc.pe.backend.servicemanagement.application.internal.commandservices;

import com.upc.pe.backend.iam.infrastructure.acl.IAMContextFacade;
import com.upc.pe.backend.servicemanagement.domain.model.aggregates.ServiceRequest;
import com.upc.pe.backend.servicemanagement.domain.model.commands.CancelServiceCommand;
import com.upc.pe.backend.servicemanagement.domain.model.commands.ConfirmServiceCompletionCommand;
import com.upc.pe.backend.servicemanagement.domain.model.commands.RequestServiceCommand;
import com.upc.pe.backend.servicemanagement.domain.model.valueobjects.ServiceStatus;
import com.upc.pe.backend.servicemanagement.domain.services.ServiceRequestCommandService;
import com.upc.pe.backend.servicemanagement.infrastructure.persistence.jpa.repositories.ServiceRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ServiceRequestCommandServiceImpl implements ServiceRequestCommandService {

    private final ServiceRequestRepository serviceRequestRepository;
    private final IAMContextFacade iamContextFacade;

    public ServiceRequestCommandServiceImpl(
            ServiceRequestRepository serviceRequestRepository,
            IAMContextFacade iamContextFacade
    ) {
        this.serviceRequestRepository = serviceRequestRepository;
        this.iamContextFacade = iamContextFacade;
    }

    @Override
    @Transactional
    public Optional<ServiceRequest> handle(RequestServiceCommand command) {

        if (!iamContextFacade.existsDriverProfileById(command.driverProfileId())) {
            throw new IllegalArgumentException(
                    String.format("DriverProfile %d does not exist", command.driverProfileId())
            );
        }

        if (!iamContextFacade.existsMechanicProfileById(command.mechanicProfileId())) {
            throw new IllegalArgumentException(
                    String.format("MechanicProfile %d does not exist", command.mechanicProfileId())
            );
        }

        if (!iamContextFacade.existsCarById(command.carId())) {
            throw new IllegalArgumentException(
                    String.format("Car %d does not exist", command.carId())
            );
        }

        if (command.scheduledDate() == null) {
            throw new IllegalArgumentException("Scheduled date is required");
        }

        var serviceRequest = new ServiceRequest(command);

        var saved = serviceRequestRepository.save(serviceRequest);

        return Optional.of(saved);
    }

    @Override
    @Transactional
    public Optional<ServiceRequest> handle(ConfirmServiceCompletionCommand command) {

        var serviceRequest = serviceRequestRepository.findById(command.serviceId());

        if (serviceRequest.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("ServiceRequest %d not found", command.serviceId())
            );
        }

        var request = serviceRequest.get();

        if (request.getStatus() == ServiceStatus.CANCELLED) {
            throw new IllegalStateException("Cannot confirm completion on a cancelled service");
        }

        if (request.getStatus() == ServiceStatus.FINALIZED) {
            throw new IllegalStateException("Service is already finalized");
        }

        boolean finalized;
        if ("DRIVER".equalsIgnoreCase(command.role())) {
            finalized = request.confirmCompletionByDriver();
        } else if ("MECHANIC".equalsIgnoreCase(command.role())) {
            finalized = request.confirmCompletionByMechanic();
        } else {
            throw new IllegalArgumentException("Role must be DRIVER or MECHANIC");
        }

        var saved = serviceRequestRepository.save(request);

        return Optional.of(saved);
    }

    @Override
    @Transactional
    public Optional<ServiceRequest> handle(CancelServiceCommand command) {

        var serviceRequest = serviceRequestRepository.findById(command.serviceId());

        if (serviceRequest.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("ServiceRequest %d not found", command.serviceId())
            );
        }

        var request = serviceRequest.get();

        if (request.getStatus() == ServiceStatus.FINALIZED) {
            throw new IllegalStateException("Cannot cancel a finalized service");
        }

        request.cancel();

        var saved = serviceRequestRepository.save(request);

        return Optional.of(saved);
    }
}
