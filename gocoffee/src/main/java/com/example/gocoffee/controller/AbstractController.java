package com.example.gocoffee.controller;

import com.example.gocoffee.dto.response.ApiResponse;
import com.example.gocoffee.dto.response.DataResponse;
import com.example.gocoffee.dto.response.VoidResponse;
import org.springframework.http.HttpStatus;

public abstract class AbstractController {

    // response with data
    public <T> ApiResponse<T> respond(DataResponse<T> dataResponse) {
        return respond(dataResponse, HttpStatus.OK);
    }

    public <T> ApiResponse<T> respond(DataResponse<T> dataResponse, HttpStatus status) {
        T data = dataResponse.execute();
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setData(data);
        apiResponse.setStatusCode(status.value());
        return apiResponse;
    }

    // response with no data
    public ApiResponse<?> respond(VoidResponse voidResponse, String message) {
        return respond(voidResponse, HttpStatus.OK, message);
    }

    public ApiResponse<?> respond(VoidResponse voidResponse) {
        return respond(voidResponse, HttpStatus.OK);
    }

    public ApiResponse<?> respond(VoidResponse voidResponse, HttpStatus status, String message) {
        voidResponse.execute();
        ApiResponse<?> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(status.value());
        apiResponse.setMessage(message);
        return apiResponse;
    }

    public ApiResponse<?> respond(VoidResponse voidResponse, HttpStatus status) {
        voidResponse.execute();
        ApiResponse<?> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(status.value());
        return apiResponse;
    }

}
