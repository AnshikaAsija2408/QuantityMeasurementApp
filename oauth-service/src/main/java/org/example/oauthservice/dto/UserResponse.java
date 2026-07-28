package org.example.oauthservice.dto;

import java.time.LocalDateTime;

public class UserResponse {

    private Long id;

    private String email;

    private String name;

    private String provider;

    private LocalDateTime createdAt;

    public UserResponse() {
    }

    public UserResponse(Long id, String email, String name, String provider, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.provider = provider;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
