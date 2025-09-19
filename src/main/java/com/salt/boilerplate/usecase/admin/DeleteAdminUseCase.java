package com.salt.boilerplate.usecase.admin;

import com.salt.boilerplate.domain.admin.exception.AdminNotFoundException;
import com.salt.boilerplate.domain.admin.gateway.AdminGateway;
import com.salt.boilerplate.domain.admin.model.Admin;

public class DeleteAdminUseCase {
    private final AdminGateway adminGateway;

    public DeleteAdminUseCase(AdminGateway adminGateway) {
        this.adminGateway = adminGateway;
    }

    public Admin execute(Long id) throws AdminNotFoundException {
        Admin admin = adminGateway.findById(id)
                .orElseThrow(AdminNotFoundException::new);

        adminGateway.delete(id);

        return admin;
    }
}
