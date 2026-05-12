package com.upc.pe.backend.iam.infrastructure.acl;

public interface IAMContextFacade {

    boolean existsMechanicProfileById(Long mechanicProfileId);

    boolean existsDriverProfileById(Long driverProfileId);

    boolean existsCarById(Long carId);
}