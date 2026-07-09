package com.upc.pe.backend.iam.interfaces.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upc.pe.backend.iam.domain.model.aggregates.User;
import com.upc.pe.backend.iam.domain.model.commands.SignInCommand;
import com.upc.pe.backend.iam.domain.model.entities.Role;
import com.upc.pe.backend.iam.domain.services.UserCommandService;
import com.upc.pe.backend.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.upc.pe.backend.iam.interfaces.rest.resources.SignInResource;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthenticationController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthenticationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserCommandService userCommandService;

    @Test
    void shouldSignInSuccessfully() throws Exception {

        // Arrange
        SignInResource request = new SignInResource(
                "another@test.com",
                "123456"
        );


        User user = new User(
                "another@test.com",
                "hashedPassword",
                "another test",
                "999999999",
                "photo.jpg",
                null
        );


        when(userCommandService.handle(any(SignInCommand.class)))
                .thenReturn(
                        Optional.of(
                                ImmutablePair.of(
                                        user,
                                        "jwt-token"
                                )
                        )
                );


        // Act + Assert
        mockMvc.perform(
                        post("/api/v1/authentication/sign-in")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email")
                        .value("another@test.com"))
                .andExpect(jsonPath("$.token")
                        .value("jwt-token"));
    }
}
