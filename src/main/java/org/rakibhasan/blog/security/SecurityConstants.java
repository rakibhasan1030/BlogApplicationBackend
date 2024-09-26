package org.rakibhasan.blog.security;

import java.util.List;

public final class SecurityConstants {
    public static final List<String> AUTH_WHITELIST = List.of(
            "/api/auth/**",
            "/v3/api-docs/**",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/webjars/**",
            "/.well-known/**",
            "/api-docs",
            "/resource/**"
    );
}
