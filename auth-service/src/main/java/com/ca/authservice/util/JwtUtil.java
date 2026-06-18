package com.ca.authservice.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.CloseableThreadContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {
    private final Key secretKey;

    public JwtUtil(@Value("${jwt.secret}") String secret){
        byte[] KeyBytes = Base64.getDecoder().
                decode(secret.getBytes(StandardCharsets.UTF_8));
        this.secretKey = Keys.hmacShaKeyFor(KeyBytes);
    }

    public String generateToken(UUID id){
        String token = Jwts.builder().
                subject(String.valueOf(id)).
                claim("id",id).
                issuedAt(new Date()).
                expiration(new Date(System.currentTimeMillis() + 60*60*1000*10 )).
                signWith(secretKey).
                compact();

        return token;
    }
}
