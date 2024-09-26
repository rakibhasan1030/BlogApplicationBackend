package org.rakibhasan.blog.services.impl;

import org.rakibhasan.blog.exceptions.InvalidPasswordException;
import org.rakibhasan.blog.exceptions.ResourceNotFoundException;
import org.rakibhasan.blog.payloads.JwtAuthRequest;
import org.rakibhasan.blog.payloads.JwtAuthResponse;
import org.rakibhasan.blog.security.JWTTokenHelper;
import org.rakibhasan.blog.services.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private JWTTokenHelper jwtTokenHelper;
    private UserDetailsService userDetailsService;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;

    public AuthServiceImpl(
            JWTTokenHelper jwtTokenHelper,
            UserDetailsService userDetailsService,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.jwtTokenHelper = jwtTokenHelper;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public JwtAuthResponse authenticate(JwtAuthRequest request) {
        try {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
            if (passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
                this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
                String generatedToken = this.jwtTokenHelper.generateToken(userDetails);
                JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
                jwtAuthResponse.setToken(generatedToken);
                return jwtAuthResponse;
            } else {
                throw new InvalidPasswordException("Invalid password for user: " + request.getUsername());
            }
        } catch (UsernameNotFoundException e) {
            throw new ResourceNotFoundException("User not found: " + request.getUsername());
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
