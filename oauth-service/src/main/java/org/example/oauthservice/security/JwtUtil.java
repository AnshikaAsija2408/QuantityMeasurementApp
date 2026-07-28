package org.example.oauthservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.expiration}")
    private long expiration;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String email) {

        System.out.println("========== GENERATING JWT ==========");
        System.out.println("Secret : " + secret);
        System.out.println("Email  : " + email);

        String token = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

        System.out.println("Generated Token : " + token);
        System.out.println("===================================");

        return token;
    }

    public String extractEmail(String token) {

        System.out.println("========== EXTRACT EMAIL ==========");
        System.out.println("Received Token : " + token);

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        System.out.println("Email From Token : " + claims.getSubject());
        System.out.println("===================================");

        return claims.getSubject();
    }

    public boolean validateToken(String token) {

        System.out.println("========== VALIDATING JWT ==========");
        System.out.println("Secret : " + secret);
        System.out.println("Received Token : " + token);

        try {

            extractEmail(token);

            System.out.println("TOKEN IS VALID");
            System.out.println("===================================");

            return true;

        } catch (Exception e) {

            System.out.println("TOKEN VALIDATION FAILED");
            e.printStackTrace();

            return false;
        }
    }
}