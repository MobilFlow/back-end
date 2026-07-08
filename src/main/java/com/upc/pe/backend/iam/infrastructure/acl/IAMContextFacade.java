package com.upc.pe.backend.iam.infrastructure.acl;

import java.util.List;

public interface IAMContextFacade {

    boolean existsMechanicProfileById(Long mechanicProfileId);

    boolean existsDriverProfileById(Long driverProfileId);

    boolean existsCarById(Long carId);

    List<MechanicMatchingCandidateDTO> getAllMechanicMatchingCandidates();
}