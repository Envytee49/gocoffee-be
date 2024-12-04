package com.example.gocoffee.controller;

import com.example.gocoffee.dto.response.ApiResponse;
import com.example.gocoffee.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController extends AbstractController{
    private final AuthService authService;

    @GetMapping("/url")
    public ApiResponse<?> auth(@RequestParam("redirect_uri") String redirectUri) {
        log.info(authService.getGoogleAuthorizationCodeRequestUrl(redirectUri));
        return respond(() -> authService.getGoogleAuthorizationCodeRequestUrl(redirectUri));
    }

    @GetMapping("/callback")
    public ApiResponse<?> callback(@RequestParam("code") String code,
                           @RequestParam("redirect_uri") String redirectUri) {
        return respond(() ->authService.getJwtToken(code, redirectUri));
    }

//    @PostMapping("/refresh")
//    public ApiResponse<?> refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
//        return respond(() ->authService.refreshToken(request));
//    }

//    @PreAuthorize("hasRole('USER')")
//    @PutMapping("/logout")
//    public void logOut() {
//        authService.logOut();
//    }
}
