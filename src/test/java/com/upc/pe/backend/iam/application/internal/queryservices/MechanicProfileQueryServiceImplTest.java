package com.upc.pe.backend.iam.application.internal.queryservices;


import com.upc.pe.backend.iam.domain.model.entities.MechanicProfile;
import com.upc.pe.backend.iam.domain.model.queries.GetMechanicProfileByUserIdQuery;
import com.upc.pe.backend.iam.infrastructure.persistence.jpa.repositories.MechanicProfileRepository;
import com.upc.pe.backend.iam.infrastructure.persistence.jpa.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class MechanicProfileQueryServiceImplTest {


    @Mock
    private MechanicProfileRepository mechanicProfileRepository;


    @Mock
    private SpecialtyRepository specialtyRepository;


    @InjectMocks
    private MechanicProfileQueryServiceImpl service;



    @Test
    void shouldReturnAllMechanicProfiles(){

        var profile = new MechanicProfile(
                1L,
                "Mechanic",
                "Workshop",
                "Address"
        );


        when(mechanicProfileRepository.findAll())
                .thenReturn(List.of(profile));


        var result = service.handle(
                new com.upc.pe.backend.iam.domain.model.queries.GetAllMechanicProfilesQuery()
        );


        assertEquals(1,result.size());


        verify(mechanicProfileRepository)
                .findAll();

    }



    @Test
    void shouldReturnMechanicProfileByUserId(){


        var profile = new MechanicProfile(
                1L,
                "Mechanic",
                "Workshop",
                "Address"
        );


        when(mechanicProfileRepository.findByUserId(1L))
                .thenReturn(Optional.of(profile));



        var result = service.handle(
                new GetMechanicProfileByUserIdQuery(1L)
        );


        assertTrue(result.isPresent());


        assertEquals(
                "Workshop",
                result.get().getWorkshopName()
        );


        verify(mechanicProfileRepository)
                .findByUserId(1L);

    }

}