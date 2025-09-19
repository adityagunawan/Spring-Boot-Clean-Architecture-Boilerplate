package com.salt.boilerplate.domain.admin.exception;

public class AdminNotFoundException extends Exception {
    public AdminNotFoundException() {
        super("Admin Not Found!!");
    }
}
