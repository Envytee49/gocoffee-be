package com.example.gocoffee.controller;

import com.example.gocoffee.dto.request.purpose.CreatePurposeRequest;
import com.example.gocoffee.dto.response.ApiResponse;
import com.example.gocoffee.service.PurposeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/purposes")
@RequiredArgsConstructor
public class AdminPurposeController extends AbstractController {
    private final PurposeService purposeService;
    @PostMapping
    public ApiResponse<?> createPurpose(@Valid @RequestBody CreatePurposeRequest request) {
        return respond(() -> purposeService.createPurpose(request));
    }
}
