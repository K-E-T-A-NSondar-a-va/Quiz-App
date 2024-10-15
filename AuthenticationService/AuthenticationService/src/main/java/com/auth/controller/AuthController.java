package com.auth.controller;

import com.auth.dto.AuthRequest;
import com.auth.entity.UserCredential;
import com.auth.service.AuthService;
import com.auth.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/register")
    public ResponseEntity<String> addNewUser(@RequestBody UserCredential user) {
        return new ResponseEntity<>(authService.saveUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest request) {
        logger.info("inside controller getToken method ");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        logger.info("auth checking...");
        if(authentication.isAuthenticated()) {
            logger.info("authenticated");
            return jwtService.createToken(request.getUsername());
        } else {
            logger.info("not authenticated");
            throw new RuntimeException("invalid access");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        logger.info("inside the validation method");
        if(jwtService.isTokenValid(token)) {
            return "token is valid";
        } else {
            return "token not valid";
        }
    }

}
