package com.salt.boilerplate.infrastructure.validation.unique;

import com.salt.boilerplate.infrastructure.db.repository.UserRepository;
import com.salt.boilerplate.infrastructure.db.schema.UserSchema;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    private UserRepository repository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (repository != null) {
            Optional<UserSchema> user = repository.findByUsername(username);
            return user.isEmpty();
        }

        return true;
    }
}