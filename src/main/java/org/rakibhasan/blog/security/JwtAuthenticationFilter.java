package org.rakibhasan.blog.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);


    private UserDetailsService userDetailsService;
    private JWTTokenHelper jwtTokenHelper;

    public JwtAuthenticationFilter(
            UserDetailsService userDetailsService,
            JWTTokenHelper jwtTokenHelper) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenHelper = jwtTokenHelper;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        logger.debug("JwtAuthenticationFilter processing request to '{}'", request.getRequestURI());

        String requestTokenHeader = request.getHeader("Authorization");
        System.out.println("Request token header = " + requestTokenHeader);

        String username = null;
        String jwtToken = null;
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtTokenHelper.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token, Exception: " + e.getMessage());
            } catch (ExpiredJwtException e) {
                System.out.println("Expired JWT Token, Exception(ExpiredJwtException): " + e.getMessage());
            } catch (SignatureException | MalformedJwtException e) {
                System.out.println("Invalid JWT Token, Exception(SignatureException | MalformedJwtException): " + e.getMessage());
            }
        } else {
            System.out.println("JWT Token not present");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (this.jwtTokenHelper.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
                System.out.println("Invalid JWT Token");
            }
        } else {
            System.out.println("Username is null or context is not null");
        }

        logger.debug("JwtAuthenticationFilter completed processing request to '{}'", request.getRequestURI());
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        logger.debug("Checking if should not filter: {}", path);
        return "/api/v1/auth/login".equals(path);
    }
}




































