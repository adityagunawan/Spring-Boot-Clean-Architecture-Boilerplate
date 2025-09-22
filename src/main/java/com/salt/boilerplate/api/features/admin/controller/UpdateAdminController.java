package com.salt.boilerplate.api.features.admin.controller;

import com.salt.boilerplate.api.config.dto.GeneralResponse;
import com.salt.boilerplate.api.features.admin.dto.AdminResponse;
import com.salt.boilerplate.api.features.admin.dto.AdminUpdateRequest;
import com.salt.boilerplate.domain.admin.exception.AdminNotFoundException;
import com.salt.boilerplate.usecase.admin.UpdateAdminUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UpdateAdminController {
    private final UpdateAdminUseCase updateAdminUseCase;

    public UpdateAdminController(UpdateAdminUseCase updateAdminUseCase) {
        this.updateAdminUseCase = updateAdminUseCase;
    }

    @PutMapping("/admins/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GeneralResponse<AdminResponse> updateAdmin(@PathVariable Long id, @Valid @RequestBody AdminUpdateRequest data) throws AdminNotFoundException {
        return new GeneralResponse<>("Success", new AdminResponse(updateAdminUseCase.execute(id, data)));
    }
}
