package org.rakibhasan.blog.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <p><b>Implement JWT authentication in our Spring Boot backend project to secure REST APIs.</b></p>
 * <p>Steps to follow:</p>
 *
 * <ol>
 *   <li><b>Add Dependency:</b> Include <code>io.jsonwebtoken</code> in your project dependencies.</li>
 *   <li><b>Create JWT Authentication Entry Point:</b> Implement <code>AuthenticationEntryPoint</code> to handle unauthorized access.</li>
 *   <li><b>Create JWTTokenHelper:</b> A utility class to handle JWT token creation, validation, and parsing.</li>
 *   <li><b>Create JwtAuthenticationFilter:</b> (extends <code>OnceRequestFilter</code>):
 *     <ul>
 *       <li>Extract JWT token from the request.</li>
 *       <li>Validate the extracted token.</li>
 *       <li>Retrieve the user associated with the token.</li>
 *       <li>Load the user details and set it in Spring Security.</li>
 *     </ul>
 *   </li>
 *   <li><b>Create JwtAuthResponse:</b> A class to structure the JWT response.</li>
 *   <li><b>Configure JWT in Spring Security Configuration:</b> Add necessary JWT configurations in your Spring Security setup.</li>
 *   <li><b>Create Login API:</b> Develop a login API to generate and return the JWT token upon successful authentication.</li>
 *   <li><b>Test the Application:</b> Ensure all endpoints are secured with JWT and the authentication flow works as expected.</li>
 * </ol>
 */

@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationEntryPoint.class);

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        logger.error("Unauthorized error: {}", authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }

}





































