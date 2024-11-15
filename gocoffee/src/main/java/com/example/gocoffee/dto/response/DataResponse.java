package com.example.gocoffee.dto.response;

@FunctionalInterface
public interface DataResponse<T> {
    T execute();
}
