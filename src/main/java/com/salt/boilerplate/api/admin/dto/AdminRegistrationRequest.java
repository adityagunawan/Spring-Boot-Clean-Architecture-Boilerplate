package com.salt.boilerplate.api.admin.dto;

import com.salt.boilerplate.usecase.admin.dto.IAdminRegistrationData;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AdminRegistrationRequest(
        @NotBlank
        String username,

        @NotBlank
        String password,

        @NotBlank
        String name,

        @NotBlank
        @Email
        String email
) implements IAdminRegistrationData{
}
