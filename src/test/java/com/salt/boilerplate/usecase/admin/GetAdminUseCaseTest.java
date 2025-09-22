package com.salt.boilerplate.usecase.admin;

import com.salt.boilerplate.domain.admin.exception.AdminNotFoundException;
import com.salt.boilerplate.domain.admin.gateway.AdminGateway;
import com.salt.boilerplate.domain.admin.model.Admin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAdminUseCaseTest {

    @Mock
    private AdminGateway adminGateway;

    private GetAdminUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new GetAdminUseCase(adminGateway);
    }

    @Test
    void execute_whenFound_returnsAdmin() throws Exception {
        Long id = 1L;
        Admin admin = Admin.createNew(
                new com.salt.boilerplate.domain.admin.value_object.Username("carol"),
                new com.salt.boilerplate.domain.admin.value_object.Email("carol@example.com"),
                new com.salt.boilerplate.domain.admin.value_object.PersonName("Carol"),
                new com.salt.boilerplate.domain.admin.value_object.PasswordHash("pwd")
        );
        when(adminGateway.findById(id)).thenReturn(Optional.of(admin));

        Admin result = useCase.execute(id);
        assertThat(result).isSameAs(admin);
        verify(adminGateway).findById(id);
    }

    @Test
    void execute_whenMissing_throwsNotFound() {
        Long id = 2L;
        when(adminGateway.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.execute(id))
                .isInstanceOf(AdminNotFoundException.class);
        verify(adminGateway).findById(id);
    }
}
