package com.salt.boilerplate.api.admin.contoller;

import com.salt.boilerplate.api.admin.dto.AdminResponse;
import com.salt.boilerplate.api.admin.dto.AdminUpdateRequest;
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
    public AdminResponse updateAdmin(@PathVariable Long id, @Valid @RequestBody AdminUpdateRequest data) throws AdminNotFoundException {
        return new AdminResponse(updateAdminUseCase.execute(id, data));
    }
}
