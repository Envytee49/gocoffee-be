package com.example.gocoffee.exception;

import org.springframework.security.core.AuthenticationException;

public class UnauthorizedException extends AuthenticationException {

    public UnauthorizedException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

    public UnauthorizedException(final String msg) {
        super(msg);
    }
}
