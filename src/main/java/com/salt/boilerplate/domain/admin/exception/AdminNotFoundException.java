package com.salt.boilerplate.domain.admin.exception;

import com.salt.boilerplate.domain.common.exception.NotFoundException;

public class AdminNotFoundException extends NotFoundException {
    public AdminNotFoundException() {
        super("Admin Not Found!!");
    }
}
