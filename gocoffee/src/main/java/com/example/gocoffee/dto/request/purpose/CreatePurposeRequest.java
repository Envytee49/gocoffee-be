package com.example.gocoffee.dto.request.purpose;

import com.example.gocoffee.enums.EntityStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatePurposeRequest(@NotBlank(message = "name must not be blank") String name,
                                   @NotNull(message = "status must not be null") EntityStatus status) {
}
