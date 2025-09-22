package com.salt.boilerplate.domain.admin.value_object;

import com.salt.boilerplate.domain.common.exception.DomainException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PersonNameTest {

    @ParameterizedTest
    @ValueSource(strings = {"Alice", "John Doe", "Mary Jane Watson"})
    void valid_names_are_accepted(String s) {
        PersonName n = new PersonName(s);
        assertThat(n.value()).isEqualTo(s);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"John  Doe", "John1", "@lice"})
    void invalid_names_throw(String s) {
        assertThatThrownBy(() -> new PersonName(s)).isInstanceOf(DomainException.class);
    }
}

