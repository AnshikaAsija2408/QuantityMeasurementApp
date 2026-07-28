package org.example.oauthservice.service;

import org.example.oauthservice.dto.AuthResponse;
import org.example.oauthservice.dto.LoginRequest;
import org.example.oauthservice.dto.RegisterRequest;
import org.example.oauthservice.entity.User;
import org.example.oauthservice.exception.AuthException;
import org.example.oauthservice.repository.UserRepository;
import org.example.oauthservice.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserService {

    private static final Logger logger =
            Logger.getLogger(UserService.class.getName());

    private static final String PROVIDER_LOCAL = "LOCAL";
    private static final String PROVIDER_GOOGLE = "GOOGLE";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AuthException("Email is already registered");
        }

        User user = new User(
                request.getEmail(),
                request.getName(),
                passwordEncoder.encode(request.getPassword()),
                PROVIDER_LOCAL
        );

        userRepository.save(user);

        logger.info("Registered new local user: " + user.getEmail());

        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponse(token, user.getEmail(), user.getName());
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AuthException("Invalid email or password"));

        if (user.getPassword() == null
                || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {

            throw new AuthException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        logger.info("User logged in: " + user.getEmail());

        return new AuthResponse(token, user.getEmail(), user.getName());
    }

    /**
     * Finds an existing user by email, or creates one for a first-time
     * Google OAuth2 sign-in. Used by OAuth2LoginSuccessHandler so that
     * Google sign-ins are also tracked in User Management.
     */
    public User findOrCreateOAuthUser(String email, String name) {

        return userRepository.findByEmail(email)
                .orElseGet(() -> {

                    User newUser = new User(
                            email,
                            name,
                            null,
                            PROVIDER_GOOGLE
                    );

                    logger.info("Creating new Google OAuth user: " + email);

                    return userRepository.save(newUser);
                });
    }
}
