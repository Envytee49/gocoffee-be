package com.example.gocoffee.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNKNOWN(1, "Unknown Error"),

    BAD_REQUEST(400, "Bad Request"),

    INVALID_PARAMETER_DATA(998, null),

    PARAMETER_NOT_VALID(999, null),

    INVALID_PARAMETER_TYPE(1000, "Parameter type is not suitable."),

    INVALID_DATA_FORMAT(1001, "Invalid data format."),

    NO_RESOURCE_FOUND(1002, "No resource found."),

    USERNAME_NOT_FOUND(1003, "Username Not Found"),

    EMAIL_EXISTED(1004, "Email Existed"),

    NAME_EXISTED(1005, "Shop Name Existed"),

    NOT_FOUND(1006, "Not Found"),

    TAG_LIMIT_EXCEEDED(1007, "You reached your tags limit for this shop");

    private final int code;

    private String message;

    public ErrorCode setMessage(String message) {
        this.message = message;
        return this;
    }

    }
