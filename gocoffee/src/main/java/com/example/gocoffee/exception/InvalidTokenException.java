package com.example.gocoffee.exception;

import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;

public class InvalidTokenException extends InvalidBearerTokenException {
    public InvalidTokenException(String description) {
        super(description);
    }
}
