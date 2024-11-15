package com.example.gocoffee.controller;

import com.example.gocoffee.dto.response.ApiResponse;
import com.example.gocoffee.service.SettingService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/settings")
@RequiredArgsConstructor
public class SettingController extends AbstractController {
    private final SettingService settingService;

    @PutMapping
    public ApiResponse<?> createTagMaxLimitSetting(@RequestParam(name = "tagMaxLimit")
                                                   @Min(1) Integer tagMaxLimit) {
        return respond(() -> settingService.createTagMaxLimitSetting(tagMaxLimit));
    }
}
