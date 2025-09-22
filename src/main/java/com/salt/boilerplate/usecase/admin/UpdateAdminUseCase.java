package com.salt.boilerplate.usecase.admin;

import com.salt.boilerplate.domain.admin.exception.AdminNotFoundException;
import com.salt.boilerplate.domain.admin.gateway.AdminGateway;
import com.salt.boilerplate.domain.admin.model.Admin;
import com.salt.boilerplate.domain.admin.value_object.Email;
import com.salt.boilerplate.domain.admin.value_object.PasswordHash;
import com.salt.boilerplate.domain.admin.value_object.PersonName;
import com.salt.boilerplate.domain.admin.value_object.Username;
import com.salt.boilerplate.usecase.admin.dto.IAdminUpdateData;

public class UpdateAdminUseCase {
    private final AdminGateway adminGateway;

    public UpdateAdminUseCase(AdminGateway adminGateway) {
        this.adminGateway = adminGateway;
    }

    public Admin execute(Long id, IAdminUpdateData data) throws AdminNotFoundException {
        Admin customer = this.adminGateway.findById(id)
                .orElseThrow(AdminNotFoundException::new);

        customer = new Admin(customer.getId(), new Username(data.username()), new Email(data.email()), new PersonName(data.name()), new PasswordHash(data.password()));

        return this.adminGateway.update(customer);
    }
}
