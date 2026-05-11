package com.upc.pe.backend.servicecatalog.application.internal.commandservices;

import com.upc.pe.backend.servicecatalog.domain.model.commands.*;
import com.upc.pe.backend.servicecatalog.domain.model.entities.ServiceImage;
import com.upc.pe.backend.servicecatalog.domain.model.entities.Tag;
import com.upc.pe.backend.servicecatalog.domain.model.valueobjects.MediaUrl;
import com.upc.pe.backend.servicecatalog.domain.model.valueobjects.PriceRange;
import com.upc.pe.backend.servicecatalog.domain.model.valueobjects.ServiceStatus;
import com.upc.pe.backend.servicecatalog.domain.services.ServiceCommandService;
import com.upc.pe.backend.servicecatalog.infrastructure.acl.IAMContextFacade;
import com.upc.pe.backend.servicecatalog.infrastructure.persistence.jpa.repositories.CategoryRepository;
import com.upc.pe.backend.servicecatalog.infrastructure.persistence.jpa.repositories.ServiceImageRepository;
import com.upc.pe.backend.servicecatalog.infrastructure.persistence.jpa.repositories.ServiceRepository;
import com.upc.pe.backend.servicecatalog.infrastructure.persistence.jpa.repositories.TagRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * ServiceCommandServiceImpl
 *
 * Service responsible for handling
 * service-related commands.
 *
 * Responsibilities:
 * - Publish services
 * - Update services
 * - Deactivate services
 * - Manage tags
 * - Manage service images
 */
@Service
public class ServiceCommandServiceImpl implements ServiceCommandService {

    private final ServiceRepository serviceRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final ServiceImageRepository serviceImageRepository;
    private final IAMContextFacade iamContextFacade;

    public ServiceCommandServiceImpl(
            ServiceRepository serviceRepository,
            CategoryRepository categoryRepository,
            TagRepository tagRepository,
            ServiceImageRepository serviceImageRepository,
            IAMContextFacade iamContextFacade
    ) {
        this.serviceRepository = serviceRepository;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
        this.serviceImageRepository = serviceImageRepository;
        this.iamContextFacade = iamContextFacade;
    }

    /**
     * Publishes a new service.
     *
     * @param command publish service command
     * @return created service
     */
    @Override
    @Transactional
    public Optional<com.upc.pe.backend.servicecatalog.domain.model.aggregates.Service>
    handle(PublishServiceCommand command) {

        if (!iamContextFacade.existsMechanicProfileById(
                command.mechanicProfileId()
        )) {

            throw new IllegalArgumentException(
                    String.format(
                            "Mechanic profile %d not found",
                            command.mechanicProfileId()
                    )
            );
        }

        var category = categoryRepository.findById(
                command.categoryId()
        ).orElseThrow(() ->
                new IllegalArgumentException(
                        String.format(
                                "Category %d not found",
                                command.categoryId()
                        )
                )
        );

        var service =
                new com.upc.pe.backend.servicecatalog.domain.model.aggregates.Service(
                        command.mechanicProfileId(),
                        command.title(),
                        command.description(),
                        new PriceRange(
                                command.priceMin(),
                                command.priceMax()
                        ),
                        ServiceStatus.ACTIVE,
                        category
                );

        return Optional.of(
                serviceRepository.save(service)
        );
    }

    /**
     * Updates an existing service.
     *
     * @param command update service command
     * @return updated service
     */
    @Override
    @Transactional
    public Optional<com.upc.pe.backend.servicecatalog.domain.model.aggregates.Service>
    handle(UpdateServiceCommand command) {

        var service = serviceRepository.findById(command.serviceId())
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format(
                                "Service %d not found",
                                command.serviceId()
                        )
                ));

        var category = categoryRepository.findById(
                command.categoryId()
        ).orElseThrow(() ->
                new IllegalArgumentException(
                        String.format(
                                "Category %d not found",
                                command.categoryId()
                        )
                )
        );

        service.setTitle(command.title());
        service.setDescription(command.description());

        service.setPriceRange(
                new PriceRange(
                        command.priceMin(),
                        command.priceMax()
                )
        );

        service.setCategory(category);

        return Optional.of(
                serviceRepository.save(service)
        );
    }

    /**
     * Deactivates a service.
     *
     * @param command deactivate service command
     * @return updated service
     */
    @Override
    @Transactional
    public Optional<com.upc.pe.backend.servicecatalog.domain.model.aggregates.Service>
    handle(DeactivateServiceCommand command) {

        var service = serviceRepository.findById(command.serviceId())
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format(
                                "Service %d not found",
                                command.serviceId()
                        )
                ));

        service.setStatus(ServiceStatus.INACTIVE);

        return Optional.of(
                serviceRepository.save(service)
        );
    }

    /**
     * Uploads and associates
     * an image to a service.
     *
     * @param command upload image command
     * @return created service image
     */
    @Override
    @Transactional
    public Optional<ServiceImage> handle(
            UploadServiceImageCommand command
    ) {

        var service = serviceRepository.findById(command.serviceId())
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format(
                                "Service %d not found",
                                command.serviceId()
                        )
                ));

        var image = new ServiceImage(
                new MediaUrl(command.imageUrl())
        );

        image.setService(service);

        service.addImage(image);

        serviceRepository.save(service);

        return Optional.of(serviceImageRepository.save(image));
    }

    /**
     * Adds a tag to a service.
     *
     * @param command add tag command
     * @return updated service
     */
    @Override
    @Transactional
    public Optional<com.upc.pe.backend.servicecatalog.domain.model.aggregates.Service>
    handle(AddTagToServiceCommand command) {

        var service = serviceRepository.findById(command.serviceId())
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format(
                                "Service %d not found",
                                command.serviceId()
                        )
                ));

        Tag tag = tagRepository.findById(command.tagId())
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format(
                                "Tag %d not found",
                                command.tagId()
                        )
                ));

        service.addTag(tag);

        return Optional.of(serviceRepository.save(service));
    }

    /**
     * Removes a tag from a service.
     *
     * @param command remove tag command
     * @return updated service
     */
    @Override
    @Transactional
    public Optional<com.upc.pe.backend.servicecatalog.domain.model.aggregates.Service>
    handle(RemoveTagFromServiceCommand command) {

        var service = serviceRepository.findById(command.serviceId())
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format(
                                "Service %d not found",
                                command.serviceId()
                        )
                ));

        Tag tag = tagRepository.findById(command.tagId())
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format(
                                "Tag %d not found",
                                command.tagId()
                        )
                ));

        service.removeTag(tag);

        return Optional.of(serviceRepository.save(service));
    }

    /**
     * Deletes a service image.
     *
     * @param command delete image command
     */
    @Override
    @Transactional
    public void handle(DeleteServiceImageCommand command) {

        var image = serviceImageRepository.findById(
                        command.serviceImageId()
                )
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format(
                                "Service image %d not found",
                                command.serviceImageId()
                        )
                ));

        serviceImageRepository.delete(image);
    }
}