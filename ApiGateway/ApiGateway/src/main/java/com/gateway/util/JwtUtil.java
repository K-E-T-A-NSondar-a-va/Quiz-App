package com.gateway.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtUtil {

    private static final String ENCRYPTION_KEY = "458564494FGD561448984D5849849F654G48R84964D5465484F498498498E49848R84484G8D487487854";

    private Key generateKey() {
        byte[] keyBytes = Decoders.BASE64.decode(ENCRYPTION_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(generateKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Boolean isTokenNotExpired(String token) {
        return !(extractAllClaims(token).getExpiration().before(new Date()));
    }

    public void validateToken(String token) {
        extractAllClaims(token);
    }

}
