package org.example.oauthservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.example.oauthservice.dto.AuthResponse;
import org.example.oauthservice.dto.LoginRequest;
import org.example.oauthservice.dto.RegisterRequest;
import org.example.oauthservice.dto.ValidationResponse;
import org.example.oauthservice.security.JwtUtil;
import org.example.oauthservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @Operation(summary = "Redirect to Google Login")
    @GetMapping("/api/auth/login")
    public String login() {
        return "Redirect to /oauth2/authorization/google";
    }

    @Operation(summary = "Register a new user with email/password")
    @PostMapping("/api/auth/register")
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(userService.register(request));
    }

    @Operation(summary = "Login with email/password")
    @PostMapping("/api/auth/login")
    public ResponseEntity<AuthResponse> loginWithPassword(
            @Valid @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(userService.login(request));
    }

    @Operation(summary = "Validate a JWT (used internally by other microservices via OpenFeign)")
    @GetMapping("/api/auth/validate")
    public ResponseEntity<ValidationResponse> validate(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader
    ) {

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.ok(
                    new ValidationResponse(false, null, "Missing or malformed Authorization header")
            );
        }

        String token = authorizationHeader.substring(7);

        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.ok(
                    new ValidationResponse(false, null, "Invalid or expired token")
            );
        }

        String email = jwtUtil.extractEmail(token);

        return ResponseEntity.ok(
                new ValidationResponse(true, email, "Token is valid")
        );
    }

    @Operation(summary = "Google OAuth Callback")
    @GetMapping("/api/auth/callback/google")
    public Map<String, Object> callback(
            @AuthenticationPrincipal OAuth2User user
    ) {

        Map<String, Object> response = new LinkedHashMap<>();

        response.put("name", user.getAttribute("name"));
        response.put("email", user.getAttribute("email"));
        response.put("picture", user.getAttribute("picture"));

        return response;
    }

    @Operation(summary = "Protected Profile API")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/api/profile")
    public String profile() {
        return "JWT Authentication Successful";
    }

    @Operation(summary = "Test API")
    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
}
