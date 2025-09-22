package com.salt.boilerplate.domain.admin.value_object;

import com.salt.boilerplate.domain.common.exception.DomainException;

import java.util.Objects;

public final class Email {
    private final String value;

    public Email(String value) {
        if (value == null || value.isBlank())
            throw new DomainException("Email is required");
        // keep regex reasonable; you can swap in a better validator if needed
        if (!value.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$"))
            throw new DomainException("Invalid email format");
        this.value = value.toLowerCase();
    }

    public String value() { return value; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email email)) return false;
        return value.equals(email.value);
    }
    @Override public int hashCode() { return Objects.hash(value); }
    @Override public String toString() { return value; }
}
