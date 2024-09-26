package org.rakibhasan.blog.controllers;

import org.rakibhasan.blog.payloads.JwtAuthRequest;
import org.rakibhasan.blog.payloads.JwtAuthResponse;
import org.rakibhasan.blog.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> createToken(@RequestBody JwtAuthRequest request) {
        try {
            JwtAuthResponse response = authService.authenticate(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Let the GlobalExceptionHandler handle the exception
            throw e;
        }
    }
}
























