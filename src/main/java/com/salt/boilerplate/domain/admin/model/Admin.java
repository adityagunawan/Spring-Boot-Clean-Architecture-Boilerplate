package com.salt.boilerplate.domain.admin.model;

import com.salt.boilerplate.domain.admin.value_object.Email;
import com.salt.boilerplate.domain.admin.value_object.PasswordHash;
import com.salt.boilerplate.domain.admin.value_object.PersonName;
import com.salt.boilerplate.domain.admin.value_object.Username;
import com.salt.boilerplate.domain.common.exception.DomainException;

import java.util.Objects;

public class Admin {
    private final Long id;            // identity (nullable until persisted)
    private final Username username;
    private final Email email;
    private final PersonName name;
    private final PasswordHash password;
    private final String role;        // fixed to ROLE_ADMIN in your example

    public Admin(Long id,
                 Username username,
                 Email email,
                 PersonName name,
                 PasswordHash password) {
        if (username == null) throw new DomainException("Username is required");
        if (email == null) throw new DomainException("Email is required");
        if (name == null) throw new DomainException("Name is required");
        if (password == null) throw new DomainException("Password hash is required");

        this.id = id;
        this.username = username;
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = "ROLE_ADMIN";
    }

    // factories for creation without id
    public static Admin createNew(Username username, Email email, PersonName name, PasswordHash hash) {
        return new Admin(null, username, email, name, hash);
    }

    // getters
    public Long getId() { return id; }
    public Username getUsername() { return username; }
    public Email getEmail() { return email; }
    public PersonName getName() { return name; }
    public PasswordHash getPassword() { return password; }
    public String getRole() { return role; }



    // equality by identity (once persisted). Before persistence, be careful with equals usage.
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Admin that)) return false;
        return Objects.equals(id, that.id);
    }
    @Override public int hashCode() { return Objects.hash(id); }
}
