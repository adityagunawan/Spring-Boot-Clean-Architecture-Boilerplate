package com.salt.boilerplate.api.admin.dto;

import com.salt.boilerplate.infrastructure.validation.format.Name;
import com.salt.boilerplate.infrastructure.validation.unique.UniqueEmail;
import com.salt.boilerplate.infrastructure.validation.unique.UniqueUsername;
import com.salt.boilerplate.usecase.admin.dto.IAdminRegistrationData;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AdminRegistrationRequest(
        @UniqueUsername(message = "{Unique.user.username}")
        @NotBlank
        String username,

        @NotBlank
        String password,

        @NotBlank
        @Name(message = "{Name.user.name}")
        String name,

        @UniqueEmail(message = "{Unique.user.email}")
        @NotBlank
        @Email
        String email
) implements IAdminRegistrationData{
}
