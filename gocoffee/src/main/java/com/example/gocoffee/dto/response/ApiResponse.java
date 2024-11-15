package com.example.gocoffee.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private Integer errorCode; // defined error code
    @Builder.Default
    private Integer statusCode = 200; // http status code

    private String message = "Success"; // message

    private T data;
}
