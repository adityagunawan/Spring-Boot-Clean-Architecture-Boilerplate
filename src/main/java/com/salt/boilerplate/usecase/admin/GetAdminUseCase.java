package com.salt.boilerplate.usecase.admin;

import com.salt.boilerplate.domain.admin.exception.AdminNotFoundException;
import com.salt.boilerplate.domain.admin.gateway.AdminGateway;
import com.salt.boilerplate.domain.admin.model.Admin;

public class GetAdminUseCase {
    private final AdminGateway adminGateway;

    public GetAdminUseCase(AdminGateway adminGateway) {
        this.adminGateway = adminGateway;
    }

    public Admin execute(Long id) throws AdminNotFoundException {
        return this.adminGateway
                .findById(id)
                .orElseThrow(AdminNotFoundException::new);
    }
}
