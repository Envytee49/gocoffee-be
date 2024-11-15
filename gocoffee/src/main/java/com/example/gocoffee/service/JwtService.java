package com.example.gocoffee.service;

import com.example.gocoffee.model.User;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface JwtService {
    String generateToken(User user);

    void verifyToken(String token) throws JOSEException, ParseException;
}
