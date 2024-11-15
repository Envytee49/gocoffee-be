package com.example.gocoffee.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRequest {
    @NotBlank(message = "ID must not be null")
    private Long id;
    @NotNull(message = "Role ID must not be null")
    private List<Long> roleIdList;
    @NotBlank(message = "Email must not be null")
    private String email;
    @NotBlank(message = "Full name must not be null")
    private String fullName;
    @Pattern(regexp = "\\d{10,11}", message = "Phone number must be 10 to 11 digits")
    private String phone;
    private String profileUrl;
    private Integer status;
}
