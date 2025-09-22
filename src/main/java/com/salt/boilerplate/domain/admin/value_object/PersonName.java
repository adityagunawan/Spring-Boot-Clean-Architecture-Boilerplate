package com.salt.boilerplate.domain.admin.value_object;

import com.salt.boilerplate.domain.common.exception.DomainException;

import java.util.Objects;

public final class PersonName {
    private final String value;

    public PersonName(String value) {
        if (value == null || value.isBlank())
            throw new DomainException("Name is required");
        // allow spaces between alphabetic parts
        if (!value.matches("^[A-Za-z]+(?: [A-Za-z]+)*$"))
            throw new DomainException("Name must be alphabetic words separated by single spaces");
        this.value = value;
    }

    public String value() { return value; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonName)) return false;
        return value.equals(((PersonName) o).value);
    }
    @Override public int hashCode() { return Objects.hash(value); }
    @Override public String toString() { return value; }
}
