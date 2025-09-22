package com.salt.boilerplate.domain.admin.value_object;

import com.salt.boilerplate.domain.common.exception.DomainException;

import java.util.Objects;

public final class PasswordHash {
    private final String value;

    public PasswordHash(String value) {
        if (value == null || value.isBlank())
            throw new DomainException("Password hash is required");
        // optional: enforce hash prefix scheme (e.g., BCrypt)
        // if (!value.startsWith("$2a$") && !value.startsWith("$2b$")) ...
        this.value = value;
    }

    public String value() { return value; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PasswordHash)) return false;
        return value.equals(((PasswordHash) o).value);
    }
    @Override public int hashCode() { return Objects.hash(value); }
    @Override public String toString() { return "*****"; } // never expose
}

