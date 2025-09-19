package com.salt.boilerplate.domain.user.exception;

public class UserNotFoundException  extends Exception {
    public UserNotFoundException() {
        super("User Not Found!!");
    }
}
