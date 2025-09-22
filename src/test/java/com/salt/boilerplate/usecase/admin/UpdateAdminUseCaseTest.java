package com.salt.boilerplate.usecase.admin;

import com.salt.boilerplate.domain.admin.exception.AdminNotFoundException;
import com.salt.boilerplate.domain.admin.gateway.AdminGateway;
import com.salt.boilerplate.domain.admin.model.Admin;
import com.salt.boilerplate.usecase.admin.dto.IAdminUpdateData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateAdminUseCaseTest {

    @Mock
    private AdminGateway adminGateway;

    private UpdateAdminUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new UpdateAdminUseCase(adminGateway);
    }

    @Test
    void execute_updatesAllFields_andReturnsUpdated() throws Exception {
        Long id = 3L;
        Admin existing = Admin.createNew(
                new com.salt.boilerplate.domain.admin.value_object.Username("oldUser"),
                new com.salt.boilerplate.domain.admin.value_object.Email("old@example.com"),
                new com.salt.boilerplate.domain.admin.value_object.PersonName("Old Name"),
                new com.salt.boilerplate.domain.admin.value_object.PasswordHash("oldPass")
        );
        when(adminGateway.findById(id)).thenReturn(Optional.of(existing));

        IAdminUpdateData data = mock(IAdminUpdateData.class);
        when(data.username()).thenReturn("newUser");
        when(data.password()).thenReturn("newPass");
        when(data.name()).thenReturn("New Name");
        when(data.email()).thenReturn("old@example.com");

        Admin updatedReturn = Admin.createNew(
                new com.salt.boilerplate.domain.admin.value_object.Username("newUser"),
                new com.salt.boilerplate.domain.admin.value_object.Email("old@example.com"),
                new com.salt.boilerplate.domain.admin.value_object.PersonName("New Name"),
                new com.salt.boilerplate.domain.admin.value_object.PasswordHash("newPass")
        );
        when(adminGateway.update(any(Admin.class))).thenReturn(updatedReturn);

        Admin result = useCase.execute(id, data);

        ArgumentCaptor<Admin> captor = ArgumentCaptor.forClass(Admin.class);
        verify(adminGateway).update(captor.capture());
        Admin passed = captor.getValue();

        assertThat(passed.getUsername().value()).isEqualTo("newUser");
        assertThat(passed.getPassword().value()).isEqualTo("newPass");
        assertThat(passed.getName().value()).isEqualTo("New Name");
        assertThat(passed.getEmail().value()).isEqualTo("old@example.com");

        assertThat(result).isSameAs(updatedReturn);
    }

    @Test
    void execute_whenAdminMissing_throwsNotFound() {
        Long id = 4L;
        when(adminGateway.findById(id)).thenReturn(Optional.empty());

        IAdminUpdateData data = mock(IAdminUpdateData.class);

        assertThatThrownBy(() -> useCase.execute(id, data))
                .isInstanceOf(AdminNotFoundException.class);

        verify(adminGateway).findById(id);
        verify(adminGateway, never()).update(any());
    }
}
