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
class DeleteAdminUseCaseTest {

    @Mock
    private AdminGateway adminGateway;

    private DeleteAdminUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new DeleteAdminUseCase(adminGateway);
    }

    @Test
    void execute_whenAdminExists_deletesAndReturnsAdmin() throws Exception {
        Long id = 10L;
        Admin admin = Admin.createNew(
                new com.salt.boilerplate.domain.admin.value_object.Username("bob"),
                new com.salt.boilerplate.domain.admin.value_object.Email("bob@example.com"),
                new com.salt.boilerplate.domain.admin.value_object.PersonName("Bob"),
                new com.salt.boilerplate.domain.admin.value_object.PasswordHash("p@ss")
        );
        when(adminGateway.findById(id)).thenReturn(Optional.of(admin));

        Admin result = useCase.execute(id);

        assertThat(result).isSameAs(admin);
        verify(adminGateway).findById(id);
        verify(adminGateway).delete(id);
        verifyNoMoreInteractions(adminGateway);
    }

    @Test
    void execute_whenAdminMissing_throwsNotFound() {
        Long id = 99L;
        when(adminGateway.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.execute(id))
                .isInstanceOf(AdminNotFoundException.class);

        verify(adminGateway).findById(id);
        verify(adminGateway, never()).delete(anyLong());
    }
}
