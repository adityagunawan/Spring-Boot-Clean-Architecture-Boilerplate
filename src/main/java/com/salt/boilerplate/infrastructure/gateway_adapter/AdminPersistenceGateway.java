package com.salt.boilerplate.infrastructure.gateway_adapter;

import com.salt.boilerplate.domain.admin.gateway.AdminGateway;
import com.salt.boilerplate.domain.admin.model.Admin;
import com.salt.boilerplate.infrastructure.db.repository.UserRepository;
import com.salt.boilerplate.infrastructure.db.schema.UserSchema;
import com.salt.boilerplate.domain.common.valueobject.Role;

import java.util.List;
import java.util.Optional;

public class AdminPersistenceGateway implements AdminGateway {

    private final UserRepository userRepository;

    public AdminPersistenceGateway(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Admin create(Admin admin) {
        return this.userRepository.save(new UserSchema(admin)).toAdmin();
    }

    @Override
    public Admin update(Admin admin) {
        return this.userRepository.save(new UserSchema(admin)).toAdmin();
    }

    @Override
    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public Optional<Admin> findById(Long id) {
        return userRepository
                .findByIdAndRole(id, Role.ADMIN)
                .map(UserSchema::toAdmin);
    }

    @Override
    public List<Admin> findAll() {
        return userRepository
                .findAll()
                .stream()
                .filter(user -> Role.ADMIN.equals(user.getRole()))
                .map(UserSchema::toAdmin)
                .toList();
    }
}
