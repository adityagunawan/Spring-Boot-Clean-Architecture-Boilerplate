package com.salt.boilerplate.api.features.admin.contoller;

import com.salt.boilerplate.api.features.admin.dto.AdminResponse;
import com.salt.boilerplate.domain.admin.exception.AdminNotFoundException;
import com.salt.boilerplate.usecase.admin.DeleteAdminUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteAdminController {
    private final DeleteAdminUseCase deleteAdminUserCase;

    public DeleteAdminController(DeleteAdminUseCase deleteAdminUserCase) {
        this.deleteAdminUserCase = deleteAdminUserCase;
    }

    @DeleteMapping("/admins/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AdminResponse deleteAdmin(@PathVariable Long id) throws AdminNotFoundException {
        return new AdminResponse(deleteAdminUserCase.execute(id));
    }

}
