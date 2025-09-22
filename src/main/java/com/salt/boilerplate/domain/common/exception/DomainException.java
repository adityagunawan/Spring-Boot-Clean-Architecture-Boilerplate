package com.salt.boilerplate.domain.common.exception;

public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }
}
