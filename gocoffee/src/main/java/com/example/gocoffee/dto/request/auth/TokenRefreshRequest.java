package com.example.gocoffee.dto.request.auth;

import jakarta.validation.constraints.NotBlank;

public record TokenRefreshRequest(@NotBlank String refresh) {
}
