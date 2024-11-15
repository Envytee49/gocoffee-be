package com.example.gocoffee.controller;

import com.example.gocoffee.dto.request.tag.CreateTagRequest;
import com.example.gocoffee.dto.response.ApiResponse;
import com.example.gocoffee.service.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/tags")
@RequiredArgsConstructor
public class AdminTagController extends AbstractController {
    private final TagService tagService;

    @PostMapping
    public ApiResponse<?> createPurpose(@Valid @RequestBody CreateTagRequest request) {
        return respond(() -> tagService.createTag(request));
    }
}
