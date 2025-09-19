package com.salt.boilerplate.usecase.admin;

import com.salt.boilerplate.domain.admin.gateway.AdminGateway;
import com.salt.boilerplate.domain.admin.model.Admin;

import java.util.List;


public class SearchAdminUseCase {
    private final AdminGateway adminGateway;

    public SearchAdminUseCase(AdminGateway adminGateway) {
        this.adminGateway = adminGateway;
    }

    public List<Admin> execute() {
        return this.adminGateway.findAll();
    }
}
