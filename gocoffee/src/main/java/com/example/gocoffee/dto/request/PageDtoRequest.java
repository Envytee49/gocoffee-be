package com.example.gocoffee.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageDtoRequest {
    @Min(message = "page must be greater than or equal to 0", value = 0)
    private Integer page = 0;

    @Min(message = "size must be greater than or equal to 0", value = 0)
    @Max(message = "size must be smaller than or equal to 500", value = 500)
    private Integer size = 20;
}
