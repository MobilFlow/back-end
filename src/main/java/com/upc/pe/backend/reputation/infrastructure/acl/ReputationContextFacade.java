package com.upc.pe.backend.reputation.infrastructure.acl;

import java.util.Optional;

public interface ReputationContextFacade {
    Optional<MechanicReputationDTO> getReputationByMechanicId(Long mechanicId);
}
