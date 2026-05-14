package com.upc.pe.backend.servicecatalog.infrastructure.acl;

/**
 * IAMContextFacade interface
 *
 * This interface provides access
 * to the IAM bounded context.
 *
 * It is used by the ServiceCatalog
 * bounded context to validate
 * mechanic-related information.
 */
public interface IAMContextFacade {

    /**
     * Validates if a mechanic profile exists.
     *
     * @param mechanicProfileId mechanic profile identifier
     * @return true if exists
     */
    boolean existsMechanicProfileById(Long mechanicProfileId);
}