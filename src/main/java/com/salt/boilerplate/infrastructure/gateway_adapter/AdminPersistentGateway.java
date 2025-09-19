package com.salt.boilerplate.infrastructure.gateway_adapter;

import com.salt.boilerplate.domain.admin.gateway.AdminGateway;
import com.salt.boilerplate.domain.admin.model.Admin;
import com.salt.boilerplate.infrastructure.db.repository.UserRepository;
import com.salt.boilerplate.infrastructure.db.schema.UserSchema;

import java.util.List;
import java.util.Optional;

public class AdminPersistentGateway implements AdminGateway {

    private final UserRepository userRepository;

    public AdminPersistentGateway(UserRepository userRepository) {
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
                .findByIdAndRole(id, "ROLE_ADMIN")
                .map(UserSchema::toAdmin);
    }

    @Override
    public List<Admin> findAll() {
        return userRepository
                .findAll()
                .stream()
                .filter(user -> "ROLE_ADMIN".equals(user.getRole()))
                .map(UserSchema::toAdmin)
                .toList();
    }
}
