package com.upc.pe.backend.iam.application.internal.commandservices;

import com.upc.pe.backend.iam.domain.model.commands.CreateMechanicProfileCommand;
import com.upc.pe.backend.iam.domain.model.entities.MechanicProfile;
import com.upc.pe.backend.iam.infrastructure.persistence.jpa.repositories.MechanicProfileRepository;
import com.upc.pe.backend.iam.infrastructure.persistence.jpa.repositories.SpecialtyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class MechanicProfileCommandServiceImplTest {


    @Mock
    private MechanicProfileRepository mechanicProfileRepository;

    @Mock
    private SpecialtyRepository specialtyRepository;


    @InjectMocks
    private MechanicProfileCommandServiceImpl service;



    @Test
    void shouldCreateMechanicProfileSuccessfully(){

        // Arrange
        var command = new CreateMechanicProfileCommand(
                1L,
                "Expert mechanic",
                "Max Workshop",
                "Av. Lima 123"
        );


        when(mechanicProfileRepository.existsByUserId(1L))
                .thenReturn(false);


        var profile = new MechanicProfile(
                1L,
                "Expert mechanic",
                "Max Workshop",
                "Av. Lima 123"
        );


        when(mechanicProfileRepository.save(any(MechanicProfile.class)))
                .thenReturn(profile);



        // Act
        var result = service.handle(command);



        // Assert
        assertTrue(result.isPresent());

        assertEquals(
                "Max Workshop",
                result.get().getWorkshopName()
        );


        verify(mechanicProfileRepository)
                .existsByUserId(1L);


        verify(mechanicProfileRepository)
                .save(any(MechanicProfile.class));

    }



    @Test
    void shouldThrowExceptionWhenMechanicProfileAlreadyExists(){

        var command = new CreateMechanicProfileCommand(
                1L,
                "Description",
                "Workshop",
                "Address"
        );


        when(mechanicProfileRepository.existsByUserId(1L))
                .thenReturn(true);



        assertThrows(
                IllegalStateException.class,
                () -> service.handle(command)
        );


        verify(mechanicProfileRepository)
                .existsByUserId(1L);


        verify(mechanicProfileRepository, never())
                .save(any());

    }

}