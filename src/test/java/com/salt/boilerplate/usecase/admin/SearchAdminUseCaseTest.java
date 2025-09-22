package com.salt.boilerplate.usecase.admin;

import com.salt.boilerplate.domain.admin.gateway.AdminGateway;
import com.salt.boilerplate.domain.admin.model.Admin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchAdminUseCaseTest {

    @Mock
    private AdminGateway adminGateway;

    private SearchAdminUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new SearchAdminUseCase(adminGateway);
    }

    @Test
    void execute_returnsAllFromGateway() {
        Admin a1 = Admin.createNew(
                new com.salt.boilerplate.domain.admin.value_object.Username("userone"),
                new com.salt.boilerplate.domain.admin.value_object.Email("e1@example.com"),
                new com.salt.boilerplate.domain.admin.value_object.PersonName("Name One"),
                new com.salt.boilerplate.domain.admin.value_object.PasswordHash("p1")
        );
        Admin a2 = Admin.createNew(
                new com.salt.boilerplate.domain.admin.value_object.Username("usertwo"),
                new com.salt.boilerplate.domain.admin.value_object.Email("e2@example.com"),
                new com.salt.boilerplate.domain.admin.value_object.PersonName("Name Two"),
                new com.salt.boilerplate.domain.admin.value_object.PasswordHash("p2")
        );
        when(adminGateway.findAll()).thenReturn(List.of(a1, a2));

        List<Admin> result = useCase.execute();

        assertThat(result).containsExactly(a1, a2);
        verify(adminGateway).findAll();
    }
}
