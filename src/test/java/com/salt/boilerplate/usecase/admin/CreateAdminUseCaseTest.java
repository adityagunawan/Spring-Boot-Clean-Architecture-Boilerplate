package com.salt.boilerplate.usecase.admin;

import com.salt.boilerplate.domain.admin.gateway.AdminGateway;
import com.salt.boilerplate.domain.admin.model.Admin;
import com.salt.boilerplate.usecase.admin.dto.IAdminRegistrationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateAdminUseCaseTest {

    @Mock
    private AdminGateway adminGateway;

    private CreateAdminUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new CreateAdminUseCase(adminGateway);
    }

    @Test
    void execute_createsAdminWithProvidedData_andReturnsGatewayResult() {
        IAdminRegistrationData data = mock(IAdminRegistrationData.class);
        when(data.username()).thenReturn("alice");
        when(data.password()).thenReturn("S3cr3t!");
        when(data.name()).thenReturn("Alice");
        when(data.email()).thenReturn("alice@example.com");

        Admin created = Admin.createNew(
                new com.salt.boilerplate.domain.admin.value_object.Username("alice"),
                new com.salt.boilerplate.domain.admin.value_object.Email("alice@example.com"),
                new com.salt.boilerplate.domain.admin.value_object.PersonName("Alice"),
                new com.salt.boilerplate.domain.admin.value_object.PasswordHash("S3cr3t!")
        );
        when(adminGateway.create(any(Admin.class))).thenReturn(created);

        Admin result = useCase.execute(data);

        ArgumentCaptor<Admin> captor = ArgumentCaptor.forClass(Admin.class);
        verify(adminGateway).create(captor.capture());
        Admin passed = captor.getValue();

        assertThat(passed.getUsername().value()).isEqualTo("alice");
        assertThat(passed.getPassword().value()).isEqualTo("S3cr3t!");
        assertThat(passed.getName().value()).isEqualTo("Alice");
        assertThat(passed.getEmail().value()).isEqualTo("alice@example.com");
        assertThat(passed.getRole()).isEqualTo("ROLE_ADMIN");

        assertThat(result).isSameAs(created);
    }
}
