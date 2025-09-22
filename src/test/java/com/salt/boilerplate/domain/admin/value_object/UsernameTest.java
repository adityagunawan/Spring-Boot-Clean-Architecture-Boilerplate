package com.salt.boilerplate.domain.admin.value_object;

import com.salt.boilerplate.domain.common.exception.DomainException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UsernameTest {

    @ParameterizedTest
    @ValueSource(strings = {"Alice", "Bob", "Carol"})
    void valid_usernames_are_accepted(String s) {
        Username u = new Username(s);
        assertThat(u.value()).isEqualTo(s);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"a", "ab", "a1b", "John_Doe", "ThisNameIsWayTooLongToBeAccepted123"})
    void invalid_usernames_throw(String s) {
        assertThatThrownBy(() -> new Username(s)).isInstanceOf(DomainException.class);
    }
}

