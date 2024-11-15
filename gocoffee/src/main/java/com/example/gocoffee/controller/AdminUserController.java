package com.example.gocoffee.controller;

import com.example.gocoffee.dto.request.user.UserRequest;
import com.example.gocoffee.dto.response.ApiResponse;
import com.example.gocoffee.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/users")
@Validated
public class AdminUserController extends AbstractController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ApiResponse<?> getUserById(@PathVariable Long id) {
        return this.respond(() -> userService.getUserById(id));
    }

    @PostMapping
    public ApiResponse<?> addUser(@RequestBody @Valid UserRequest dto) {
        return this.respond(() -> userService.addUser(dto));
    }

    @PutMapping
    public ApiResponse<?> updateUser(@RequestBody @Valid UserRequest dto) {
        return this.respond(() -> userService.updateUser(dto));
    }

    @DeleteMapping
    public ApiResponse<?> deleteUser(@RequestBody List<Long> idList) {
        return this.respond(() -> userService.deleteUser(idList));
    }
}

