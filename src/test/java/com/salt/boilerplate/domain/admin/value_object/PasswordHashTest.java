package com.salt.boilerplate.domain.admin.value_object;

import com.salt.boilerplate.domain.common.exception.DomainException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PasswordHashTest {

    @ParameterizedTest
    @ValueSource(strings = {"$2b$10$abcdefghijklmnopqrstuv", "hash-value"})
    void valid_hash_is_accepted(String s) {
        PasswordHash h = new PasswordHash(s);
        assertThat(h.value()).isEqualTo(s);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t"})
    void blank_or_null_hash_throws(String s) {
        assertThatThrownBy(() -> new PasswordHash(s)).isInstanceOf(DomainException.class);
    }
}

