package com.salt.boilerplate.usecase.admin;

import com.salt.boilerplate.domain.admin.exception.AdminNotFoundException;
import com.salt.boilerplate.domain.admin.gateway.AdminGateway;
import com.salt.boilerplate.domain.admin.model.Admin;
import com.salt.boilerplate.usecase.admin.dto.IAdminUpdateData;

public class UpdateAdminUseCase {
    private final AdminGateway adminGateway;

    public UpdateAdminUseCase(AdminGateway adminGateway) {
        this.adminGateway = adminGateway;
    }

    public Admin execute(Long id, IAdminUpdateData data) throws AdminNotFoundException {
        Admin customer = this.adminGateway.findById(id)
                .orElseThrow(AdminNotFoundException::new);

        if(data.username() != null && !data.username().isBlank())
            customer.setUsername(data.username());

        if(data.password() != null && !data.password().isBlank())
            customer.setPassword(data.password());

        if(data.name() != null && !data.name().isBlank())
            customer.setName(data.name());

        if(data.email() != null && !data.email().isBlank())
            customer.setEmail(data.email());

        return this.adminGateway.update(customer);
    }
}
