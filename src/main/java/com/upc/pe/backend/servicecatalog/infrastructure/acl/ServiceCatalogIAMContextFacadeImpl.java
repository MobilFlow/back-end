package com.upc.pe.backend.servicecatalog.infrastructure.acl;

import com.upc.pe.backend.iam.infrastructure.persistence.jpa.repositories.MechanicProfileRepository;
import org.springframework.stereotype.Service;

/**
 * ServiceCatalogIAMContextFacadeImpl class
 *
 * Implementation of the ACL facade
 * used to communicate with the
 * IAM bounded context.
 */
@Service
public class ServiceCatalogIAMContextFacadeImpl implements IAMContextFacade {

    private final MechanicProfileRepository mechanicProfileRepository;

    public ServiceCatalogIAMContextFacadeImpl(
            MechanicProfileRepository mechanicProfileRepository
    ) {
        this.mechanicProfileRepository = mechanicProfileRepository;
    }

    /**
     * Checks if a mechanic profile exists.
     *
     * @param mechanicProfileId mechanic profile identifier
     * @return true if exists
     */
    @Override
    public boolean existsMechanicProfileById(Long mechanicProfileId) {
        return mechanicProfileRepository.existsById(mechanicProfileId);
    }
}