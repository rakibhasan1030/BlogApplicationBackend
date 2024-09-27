package org.rakibhasan.blog.security;

import java.util.List;

public final class SecurityConstants {
    public static final List<String> AUTH_WHITELIST = List.of(
            "/api/v1/auth/**",
            "/v3/api-docs/**",
            "/v3/api-docs.yaml",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/webjars/**",
            "/.well-known/**",
            "/api-docs/**",
            "/resource/**",
            "/swagger-ui/index.html"
    );
}