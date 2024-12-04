package com.example.gocoffee.service;


import com.example.gocoffee.dto.request.auth.TokenRefreshRequest;
import com.example.gocoffee.dto.response.auth.JwtTokenResponse;

public interface AuthService {
    String getGoogleAuthorizationCodeRequestUrl(String redirectUrl);

    JwtTokenResponse getJwtToken(String code, String redirectUri);
    JwtTokenResponse refreshToken(TokenRefreshRequest request);

    void logOut();
}
