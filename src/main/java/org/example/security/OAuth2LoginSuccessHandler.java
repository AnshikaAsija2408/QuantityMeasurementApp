package org.example.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private static final String FRONTEND_URL = "http://localhost:5173";

    private final JwtUtil jwtUtil;

    public OAuth2LoginSuccessHandler(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        DefaultOAuth2User user =
                (DefaultOAuth2User) authentication.getPrincipal();

        String email = user.getAttribute("email");

        String token = jwtUtil.generateToken(email);

        // NEW: redirect to "/" (the Dashboard) instead of "/index.html".
        // DashboardPage picks up the token and auto-navigates to "/app".
        response.sendRedirect(
                FRONTEND_URL + "/?token=" +
                        URLEncoder.encode(token, StandardCharsets.UTF_8)
        );
    }
}