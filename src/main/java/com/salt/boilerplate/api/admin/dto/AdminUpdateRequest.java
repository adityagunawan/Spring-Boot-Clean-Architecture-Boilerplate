package com.salt.boilerplate.api.admin.dto;


import com.salt.boilerplate.infrastructure.validation.format.Name;
import com.salt.boilerplate.infrastructure.validation.unique.UniqueEmail;
import com.salt.boilerplate.infrastructure.validation.unique.UniqueUsername;
import com.salt.boilerplate.usecase.admin.dto.IAdminUpdateData;
import jakarta.validation.constraints.Email;

public record AdminUpdateRequest(
        @UniqueUsername(message = "{Unique.user.username}")
        String username,

        String password,

        @Name(message = "{Name.user.name}")
        String name,

        @UniqueEmail(message = "{Unique.user.email}")
        @Email
        String email) implements IAdminUpdateData {
}
