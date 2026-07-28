package org.example.oauthservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.oauthservice.dto.UserResponse;
import org.example.oauthservice.entity.User;
import org.example.oauthservice.exception.AuthException;
import org.example.oauthservice.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Management API", description = "Endpoints for managing authenticated user profiles")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Operation(summary = "Get the currently authenticated user's profile")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/me")
    public ResponseEntity<UserResponse> me(Authentication authentication) {

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AuthException("User not found"));

        return ResponseEntity.ok(
                new UserResponse(
                        user.getId(),
                        user.getEmail(),
                        user.getName(),
                        user.getProvider(),
                        user.getCreatedAt()
                )
        );
    }
}
