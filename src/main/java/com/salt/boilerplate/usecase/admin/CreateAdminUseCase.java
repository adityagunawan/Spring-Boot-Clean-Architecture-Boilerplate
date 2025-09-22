package com.salt.boilerplate.usecase.admin;

import com.salt.boilerplate.domain.admin.gateway.AdminGateway;
import com.salt.boilerplate.domain.admin.model.Admin;
import com.salt.boilerplate.domain.admin.value_object.Email;
import com.salt.boilerplate.domain.admin.value_object.PasswordHash;
import com.salt.boilerplate.domain.admin.value_object.PersonName;
import com.salt.boilerplate.domain.admin.value_object.Username;
import com.salt.boilerplate.usecase.admin.dto.IAdminRegistrationData;

public class CreateAdminUseCase {
    private final AdminGateway adminGateway;

    public CreateAdminUseCase(AdminGateway adminGateway) {
        this.adminGateway = adminGateway;
    }

    public Admin execute(IAdminRegistrationData data) {

        Admin admin = Admin.createNew(new Username(data.username()), new Email(data.email()), new PersonName(data.name()), new PasswordHash(data.password()));

        return this.adminGateway.create(admin);
    }

}
