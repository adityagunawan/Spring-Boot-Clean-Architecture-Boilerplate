package com.salt.boilerplate.api.features.admin.contoller;

import com.salt.boilerplate.api.config.dto.GeneralResponse;
import com.salt.boilerplate.api.features.admin.dto.AdminRegistrationRequest;
import com.salt.boilerplate.api.features.admin.dto.AdminResponse;
import com.salt.boilerplate.usecase.admin.CreateAdminUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateAdminController {


    private final CreateAdminUseCase createAdminUseCase;

    public CreateAdminController(CreateAdminUseCase createAdminUseCase) {
        this.createAdminUseCase = createAdminUseCase;
    }

    @PostMapping("/admins")
    @ResponseStatus(HttpStatus.CREATED)
    public GeneralResponse<AdminResponse> createAdmin(@Valid @RequestBody AdminRegistrationRequest request) {
        return new GeneralResponse<>("Success", new AdminResponse(createAdminUseCase.execute(request)));
    }

}
