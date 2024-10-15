package com.auth.service;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import io.jsonwebtoken.io.Decoders;

import java.util.Date;
import java.util.HashMap;
import io.jsonwebtoken.Jwts;
import java.util.Map;

@Component
public class JwtService {
    private static final String ENCRYPTION_KEY = "458564494FGD561448984D5849849F654G48R84964D5465484F498498498E49848R84484G8D487487854";

    public String createToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return generateToken(claims, username);
    }

    private String generateToken(Map<String, Object> claims, String username) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(username)
                .signWith(generateKey(), SignatureAlgorithm.HS256)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000L *60*60*60*24*7))
                .compact();
    }

    private Key generateKey() {
        byte[] keyBytes = Decoders.BASE64.decode(ENCRYPTION_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
