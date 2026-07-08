package com.upc.pe.backend.reputation.infrastructure.acl;

import com.upc.pe.backend.reputation.infrastructure.persistence.jpa.repositories.MechanicReputationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReputationContextFacadeImpl implements ReputationContextFacade {

    private final MechanicReputationRepository mechanicReputationRepository;

    public ReputationContextFacadeImpl(MechanicReputationRepository mechanicReputationRepository) {
        this.mechanicReputationRepository = mechanicReputationRepository;
    }

    @Override
    public Optional<MechanicReputationDTO> getReputationByMechanicId(Long mechanicId) {
        return mechanicReputationRepository.findByMechanicId(mechanicId)
                .map(reputation -> new MechanicReputationDTO(
                        reputation.getAverageScore(),
                        reputation.getRatingsCount()
                ));
    }
}
