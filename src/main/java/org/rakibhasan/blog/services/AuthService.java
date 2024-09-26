package org.rakibhasan.blog.services;

import org.rakibhasan.blog.payloads.ApiResponse;
import org.rakibhasan.blog.payloads.JwtAuthRequest;
import org.rakibhasan.blog.payloads.JwtAuthResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    JwtAuthResponse authenticate(JwtAuthRequest request);
}
