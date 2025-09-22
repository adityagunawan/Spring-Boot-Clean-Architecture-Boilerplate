package com.salt.boilerplate.domain.admin.value_object;

import com.salt.boilerplate.domain.common.exception.DomainException;

import java.util.Objects;

public final class Username {
    private final String value;

    public Username(String value) {
        if (value == null || value.isBlank())
            throw new DomainException("Username is required");
        // example policy: alphabets only, 3..32 chars
        if (!value.matches("^[A-Za-z]{3,32}$"))
            throw new DomainException("Username must be alphabetic (3–32 chars)");
        this.value = value;
    }

    public String value() { return value; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Username)) return false;
        return value.equals(((Username) o).value);
    }
    @Override public int hashCode() { return Objects.hash(value); }
    @Override public String toString() { return value; }
}
