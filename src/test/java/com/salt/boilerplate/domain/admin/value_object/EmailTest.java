package com.salt.boilerplate.domain.admin.value_object;

import com.salt.boilerplate.domain.common.exception.DomainException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EmailTest {

    @ParameterizedTest
    @ValueSource(strings = {"Alice@Example.com", "john.doe@example.org", "a+b@sub.example.co"})
    void valid_emails_are_accepted_and_lowercased(String s) {
        Email e = new Email(s);
        assertThat(e.value()).isEqualTo(s.toLowerCase());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "no-at.com", "@no-user.com", "user@no-tld", "user@@example.com"})
    void invalid_emails_throw(String s) {
        assertThatThrownBy(() -> new Email(s)).isInstanceOf(DomainException.class);
    }
}

