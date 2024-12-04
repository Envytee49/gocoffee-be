package com.example.gocoffee.controller;

import com.example.gocoffee.dto.response.ApiResponse;
import com.example.gocoffee.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Validated
public class UserController extends AbstractController {
    private final UserService userService;

    @GetMapping("/info")
    public ApiResponse<?> getUserInfo() {
        log.info("here");
        return respond(() -> userService.getUserInfo());
    }

}
