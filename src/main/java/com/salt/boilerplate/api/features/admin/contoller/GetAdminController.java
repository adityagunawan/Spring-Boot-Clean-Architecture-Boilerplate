package com.salt.boilerplate.api.features.admin.contoller;

import com.salt.boilerplate.api.config.dto.GeneralResponse;
import com.salt.boilerplate.api.features.admin.dto.AdminResponse;
import com.salt.boilerplate.domain.admin.exception.AdminNotFoundException;
import com.salt.boilerplate.domain.admin.model.Admin;
import com.salt.boilerplate.usecase.admin.GetAdminUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetAdminController {
    private final GetAdminUseCase getAdminUseCase;

    public GetAdminController(GetAdminUseCase getAdminUseCase) {
        this.getAdminUseCase = getAdminUseCase;
    }

    @GetMapping("/admins/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public GeneralResponse<AdminResponse> getAdmin(@PathVariable Long id) throws AdminNotFoundException {
        return new GeneralResponse<>("Success", new AdminResponse(getAdminUseCase.execute(id)));
    }
}
