package com.example.gocoffee.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class PageDtoRequest {
    @Min(value = 1)
    private Integer page;

    @Min(value = 1)
    @Max(value = 500)
    private Integer size;
}
