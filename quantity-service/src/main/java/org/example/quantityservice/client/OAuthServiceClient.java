package org.example.quantityservice.client;

import org.example.quantityservice.dto.ValidationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * OpenFeign client used for inter-service communication with the
 * oauth-service. The quantity-service does not duplicate JWT
 * validation logic or the JWT secret; instead it delegates every
 * incoming token to the oauth-service, which is the single source
 * of truth for authentication.
 */
@FeignClient(name = "oauth-service", url = "${oauth.service.url}")
public interface OAuthServiceClient {

    @GetMapping("/api/auth/validate")
    ValidationResponse validate(@RequestHeader("Authorization") String authorizationHeader);
}
