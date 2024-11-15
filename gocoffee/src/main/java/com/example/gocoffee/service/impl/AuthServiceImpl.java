package com.example.gocoffee.service.impl;

import com.example.gocoffee.dto.response.auth.GoogleUserResponse;
import com.example.gocoffee.dto.response.auth.JwtTokenResponse;
import com.example.gocoffee.exception.InternalServerException;
import com.example.gocoffee.model.User;
import com.example.gocoffee.repository.UserRepository;
import com.example.gocoffee.service.AuthService;
import com.example.gocoffee.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    @Value("${oauth2.client.registration.google.userinfo-endpoint}")
    private String userInfoEndpoint;
    @Value("${oauth2.client.registration.google.client-id}")
    private String clientId;
    @Value("${oauth2.client.registration.google.client-secret}")
    private String clientSecret;

    @Override
    public String getGoogleAuthorizationCodeRequestUrl(String redirectUri) {
        return new GoogleAuthorizationCodeRequestUrl(
                clientId,
                redirectUri,
                Arrays.asList("email", "profile", "openid")
        ).build();
    }

    @Override
    public JwtTokenResponse getJwtToken(String code, String redirectUri) {
        try {
            NetHttpTransport transport = new NetHttpTransport();
            String accessToken = getGoogleAccessToken(code, transport, redirectUri);
            GoogleUserResponse userInfo = getUserInfoFromGoogle(accessToken, transport);
            User user = userRepository.findByEmail(userInfo.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
            user.setProfileUrl(userInfo.getPicture());
            user = userRepository.save(user);
            String jwtToken = jwtService.generateToken(user);
            return new JwtTokenResponse(jwtToken, null);
        } catch (IOException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    private String getGoogleAccessToken(String code, NetHttpTransport transport, String redirectUri) throws IOException {
        String accessToken;
        GsonFactory gsonFactory = new GsonFactory();
        accessToken = new GoogleAuthorizationCodeTokenRequest(
                transport,
                gsonFactory,
                clientId,
                clientSecret,
                code,
                redirectUri
        ).execute().getAccessToken();
        return accessToken;
    }

    private GoogleUserResponse getUserInfoFromGoogle(String accessToken, NetHttpTransport transport) throws IOException {
        Credential credential = new Credential(BearerToken.authorizationHeaderAccessMethod()).setAccessToken(accessToken);
        HttpRequest request = transport
                .createRequestFactory(credential)
                .buildGetRequest(new GenericUrl(userInfoEndpoint));
        HttpResponse response = request.execute();
        String userInfoResponse = response.parseAsString();
        return objectMapper.readValue(userInfoResponse, GoogleUserResponse.class);
    }
}
