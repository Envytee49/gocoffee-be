package com.example.gocoffee.dto.response.user;

import lombok.Builder;

@Builder
public record UserInfoResponse (String name, String phone, String email, String profileUrl) {
}
