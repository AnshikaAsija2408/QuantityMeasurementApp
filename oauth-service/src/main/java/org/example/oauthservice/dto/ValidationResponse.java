package org.example.oauthservice.dto;

/**
 * Returned from GET /api/auth/validate.
 * Consumed by the quantity-service via an OpenFeign client so that
 * token validation logic lives in exactly one place.
 */
public class ValidationResponse {

    private boolean valid;

    private String email;

    private String message;

    public ValidationResponse() {
    }

    public ValidationResponse(boolean valid, String email, String message) {
        this.valid = valid;
        this.email = email;
        this.message = message;
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
