package com.salt.boilerplate.usecase.admin;

import com.salt.boilerplate.domain.admin.gateway.AdminGateway;
import com.salt.boilerplate.domain.admin.model.Admin;
import com.salt.boilerplate.usecase.admin.dto.IAdminRegistrationData;

public class CreateAdminUseCase {
    private final AdminGateway adminGateway;

    public CreateAdminUseCase(AdminGateway adminGateway) {
        this.adminGateway = adminGateway;
    }

    public Admin execute(IAdminRegistrationData data) {

        Admin admin = new Admin(data.username(), data.password(), data.name(), data.email());

        return this.adminGateway.create(admin);
    }

}
