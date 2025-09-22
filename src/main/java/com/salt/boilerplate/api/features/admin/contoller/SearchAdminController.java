package com.salt.boilerplate.api.features.admin.contoller;

import com.salt.boilerplate.api.config.dto.GeneralResponse;
import com.salt.boilerplate.api.features.admin.dto.AdminResponse;
import com.salt.boilerplate.domain.admin.model.Admin;
import com.salt.boilerplate.usecase.admin.SearchAdminUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchAdminController {
    private final SearchAdminUseCase searchAdminUseCase;

    public SearchAdminController(SearchAdminUseCase searchAdminUseCase) {
        this.searchAdminUseCase = searchAdminUseCase;
    }

    @GetMapping("/admins")
    @ResponseStatus(HttpStatus.OK)
    public GeneralResponse<List<AdminResponse>> searchAdmin() {
        List<Admin> admins = this.searchAdminUseCase.execute();

        return new GeneralResponse<>("Success", admins.stream().map(AdminResponse::new).toList());
    }

}
