package com.salt.boilerplate.domain.user.exception;

import com.salt.boilerplate.domain.common.exception.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException() {
        super("User Not Found!!");
    }
}
