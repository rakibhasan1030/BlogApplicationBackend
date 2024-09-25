package org.rakibhasan.blog.controllers;

import org.rakibhasan.blog.payloads.JwtAuthRequest;
import org.rakibhasan.blog.payloads.JwtAuthResponse;
import org.rakibhasan.blog.security.JWTTokenHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

    private JWTTokenHelper jwtTokenHelper;
    private UserDetailsService userDetailsService;
    private AuthenticationManager authenticationManager;

    public AuthController(
            JWTTokenHelper jwtTokenHelper,
            UserDetailsService userDetailsService,
            AuthenticationManager authenticationManager
    ) {
        this.jwtTokenHelper = jwtTokenHelper;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(
            @RequestBody JwtAuthRequest request
    ) {
        this.authenticate(request.getUsername(), request.getPassword());
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
        String generatedToken = this.jwtTokenHelper.generateToken(userDetails);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setToken(generatedToken);
        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }

    private void authenticate(String username, String password) {
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }


}

























