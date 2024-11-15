package com.example.gocoffee.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

@UtilityClass
@Slf4j
public class SecurityUtil {

    public String getUserEmail() {
        return extractPrincipal().getSubject();
    }

    public List<String> getUserRoles() {
        return extractPrincipal().getClaimAsStringList("scope");
    }
    private Jwt extractPrincipal() {
        return ((Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

}
