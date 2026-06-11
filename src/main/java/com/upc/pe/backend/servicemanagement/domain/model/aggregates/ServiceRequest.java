package com.upc.pe.backend.servicemanagement.domain.model.aggregates;

import com.upc.pe.backend.servicemanagement.domain.model.commands.RequestServiceCommand;
import com.upc.pe.backend.servicemanagement.domain.model.valueobjects.ServiceStatus;
import com.upc.pe.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class ServiceRequest extends AuditableAbstractAggregateRoot<ServiceRequest> {

    @Column(nullable = false)
    private Long serviceId;   // @Braulio Nuevo

    @Column(nullable = false)
    private Long driverProfileId;

    @Column(nullable = false)
    private Long mechanicProfileId;

    @Column(nullable = false)
    private Long carId;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date scheduledDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ServiceStatus status;

    @Column(nullable = false)
    private Boolean driverConfirmed;

    @Column(nullable = false)
    private Boolean mechanicConfirmed;

    @Temporal(TemporalType.TIMESTAMP)
    private Date completedAt;

    public ServiceRequest() {}

    public ServiceRequest(RequestServiceCommand command) {
        this.serviceId = command.serviceId(); // @Braulio Nuevo
        this.driverProfileId = command.driverProfileId();
        this.mechanicProfileId = command.mechanicProfileId();
        this.carId = command.carId();
        this.description = command.description();
        this.scheduledDate = command.scheduledDate();
        this.status = ServiceStatus.SCHEDULED;
        this.driverConfirmed = false;
        this.mechanicConfirmed = false;
    }

    public boolean confirmCompletionByDriver() {
        this.driverConfirmed = true;
        return tryFinalize();
    }

    public boolean confirmCompletionByMechanic() {
        this.mechanicConfirmed = true;
        return tryFinalize();
    }

    public void cancel() {
        this.status = ServiceStatus.CANCELLED;
    }

    private boolean tryFinalize() {
        if (this.driverConfirmed && this.mechanicConfirmed) {
            this.status = ServiceStatus.FINALIZED;
            this.completedAt = new Date();
            return true;
        }
        this.status = ServiceStatus.COMPLETION_PENDING;
        return false;
    }
}
