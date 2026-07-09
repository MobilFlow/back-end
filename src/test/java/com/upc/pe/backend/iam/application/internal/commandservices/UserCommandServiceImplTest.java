package com.upc.pe.backend.iam.application.internal.commandservices;

import com.upc.pe.backend.iam.domain.model.aggregates.User;
import com.upc.pe.backend.iam.domain.model.commands.SignInCommand;
import com.upc.pe.backend.iam.domain.model.commands.SignUpCommand;
import com.upc.pe.backend.iam.domain.model.entities.Role;
import com.upc.pe.backend.iam.domain.model.valueobjects.Roles;
import com.upc.pe.backend.iam.infrastructure.hashing.bcrypt.BCryptHashingService;
import com.upc.pe.backend.iam.infrastructure.persistence.jpa.repositories.DriverProfileRepository;
import com.upc.pe.backend.iam.infrastructure.persistence.jpa.repositories.MechanicProfileRepository;
import com.upc.pe.backend.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.upc.pe.backend.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.upc.pe.backend.iam.infrastructure.tokens.jwt.BearerTokenService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserCommandServiceImplTest {

        @Mock
        private UserRepository userRepository;

        @Mock
        private RoleRepository roleRepository;

        @Mock
        private BCryptHashingService hashingService;

        @Mock
        private BearerTokenService tokenService;

        @Mock
        private DriverProfileRepository driverProfileRepository;

        @Mock
        private MechanicProfileRepository mechanicProfileRepository;

        @InjectMocks
        private UserCommandServiceImpl userCommandService;

        @Test
        void shouldReturnUserAndTokenWhenCredentialsAreValid() {
                Role role = new Role(Roles.ROLE_DRIVER
                );

                User user = new User("unit@test.com",
                        "hashedPassword",
                        "unit test",
                        "999999999",
                        "photo.jpg",
                        role);

                when(userRepository.findByEmail("unit@test.com"))
                        .thenReturn(Optional.of(user));

                when(hashingService.matches("123456", user.getPassword()))
                        .thenReturn(true);

                when(tokenService.generateToken(any(), any()))
                        .thenReturn("jwt-token");

                SignInCommand command =
                        new SignInCommand("unit@test.com", "123456");

                var result = userCommandService.handle(command);

                assertTrue(result.isPresent());
        }

        @Test
        void shouldReturnEmptyWhenUserDoesNotExist() {

                        // Arrange
                        SignInCommand command = new SignInCommand(
                                "nouser@test.com",
                                "123456"
                        );

                        when(userRepository.findByEmail("nouser@test.com"))
                                .thenReturn(Optional.empty());

                        // Act
                        var result = userCommandService.handle(command);

                        // Assert
                        assertTrue(result.isEmpty());

                        verify(userRepository).findByEmail("nouser@test.com");

                        verifyNoInteractions(hashingService);
                        verifyNoInteractions(tokenService);

        }

        @Test
        void shouldThrowExceptionWhenEmailAlreadyExists() {

                        // Arrange
                        SignUpCommand command = new SignUpCommand(
                                "unit@test.com",
                                "123456",
                                "unit test",
                                "999999999",
                                "ROLE_DRIVER"
                        );

                        when(userRepository.existsByEmail("unit@test.com"))
                                .thenReturn(true);

                        // Act y Assert
                        IllegalArgumentException exception = assertThrows(
                                IllegalArgumentException.class,
                                () -> userCommandService.handle(command)
                        );

                        assertEquals(
                                "User with email unit@test.com already exists",
                                exception.getMessage()
                        );

                        verify(userRepository).existsByEmail("unit@test.com");

                        verifyNoInteractions(roleRepository);
                        verifyNoInteractions(hashingService);
                        verifyNoInteractions(driverProfileRepository);
                        verifyNoInteractions(mechanicProfileRepository);
                        verifyNoInteractions(tokenService);

        }

}
