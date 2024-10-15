package com.auth.service;

import com.auth.controller.AuthController;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import io.jsonwebtoken.io.Decoders;

import java.util.Date;
import java.util.HashMap;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class JwtService {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private CustomUserDetailsService userDetailsService;

    private static final String ENCRYPTION_KEY = "458564494FGD561448984D5849849F654G48R84964D5465484F498498498E49848R84484G8D487487854";

    public String createToken(String username) {
        logger.info("creating token");
        Map<String, Object> claims = new HashMap<>();
        return generateToken(claims, username);
    }

    private String generateToken(Map<String, Object> claims, String username) {
        logger.info("generating token");
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
        logger.info("parsing key");
        byte[] keyBytes = Decoders.BASE64.decode(ENCRYPTION_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // validation method: ================================================================

   private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(generateKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
   }

   public Boolean isTokenValid(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(extractAllClaims(token).getSubject());
        return (userDetails != null) && !(extractAllClaims(token).getExpiration().before(new Date()));
   }
}
