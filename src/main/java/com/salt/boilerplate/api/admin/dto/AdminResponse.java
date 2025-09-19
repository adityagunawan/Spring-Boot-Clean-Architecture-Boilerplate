package com.salt.boilerplate.api.admin.dto;

import com.salt.boilerplate.domain.admin.model.Admin;
import com.salt.boilerplate.usecase.admin.dto.IAdminPublicData;

public record AdminResponse(String id, String username, String name, String email) implements IAdminPublicData {
    public AdminResponse(Admin admin) {
        this(
                admin.getId().toString(),
                admin.getUsername(),
                admin.getName(),
                admin.getEmail()
        );
    }
}
