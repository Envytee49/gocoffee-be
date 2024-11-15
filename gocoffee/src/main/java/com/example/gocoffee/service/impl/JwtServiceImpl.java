package com.example.gocoffee.service.impl;

import com.example.gocoffee.exception.InvalidTokenException;
import com.example.gocoffee.model.Role;
import com.example.gocoffee.model.User;
import com.example.gocoffee.repository.UserRepository;
import com.example.gocoffee.service.JwtService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    @Value("${security.jwt.token.secret-key}")
    private String secretKey;
    @Value("${security.jwt.token.expire-length-in-seconds}")
    private long expireLengthInSeconds;
    private final UserRepository userRepository;

    public String generateToken(User user) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getEmail())
                .issueTime(new Date())
                .claim("scope", getRoleList(user))
                .expirationTime(Date.from(Instant.now().plusSeconds(expireLengthInSeconds)))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        try {
            JWSSigner signer = new MACSigner(secretKey);
            jwsObject.sign(signer);
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new JwtException(e.getMessage());
        }
    }

    public void verifyToken(String token) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(secretKey);
        SignedJWT signedJWT = SignedJWT.parse(token);
        JWTClaimsSet jwtClaimsSet = signedJWT.getJWTClaimsSet();
        Date expirationTime = jwtClaimsSet.getExpirationTime();
        boolean isVerified = signedJWT.verify(verifier);
        if (!(isVerified && expirationTime.after(new Date()))) {
            throw new InvalidTokenException("Invalid Token");
        }
    }

    private List<String> getRoleList(User user) {
        return user.getRoles()
                .stream()
                .map(Role::getName)
                .toList();
    }
}
