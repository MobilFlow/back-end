package com.upc.pe.backend.iam.infrastructure.acl;

import com.upc.pe.backend.iam.infrastructure.persistence.jpa.repositories.MechanicProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class IAMContextFacadeImpl implements IAMContextFacade {

    private final MechanicProfileRepository mechanicProfileRepository;

    public IAMContextFacadeImpl(
            MechanicProfileRepository mechanicProfileRepository
    ) {
        this.mechanicProfileRepository = mechanicProfileRepository;
    }

    @Override
    public boolean existsMechanicProfileById(Long mechanicProfileId) {
        return mechanicProfileRepository.existsById(mechanicProfileId);
    }
}