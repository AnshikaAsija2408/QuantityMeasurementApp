package org.example.quantityservice.dto;

/**
 * Mirrors the response shape returned by the oauth-service's
 * GET /api/auth/validate endpoint.
 */
public class ValidationResponse {

    private boolean valid;

    private String email;

    private String message;

    public ValidationResponse() {
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
