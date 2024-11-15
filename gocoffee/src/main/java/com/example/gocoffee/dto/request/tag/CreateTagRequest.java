package com.example.gocoffee.dto.request.tag;

import jakarta.validation.constraints.NotBlank;

public record CreateTagRequest(@NotBlank(message = "name must not be null") String name) {
}
