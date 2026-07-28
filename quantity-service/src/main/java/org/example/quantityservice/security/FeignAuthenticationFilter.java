package org.example.quantityservice.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.quantityservice.client.OAuthServiceClient;
import org.example.quantityservice.dto.ValidationResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@Component
public class FeignAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger =
            Logger.getLogger(FeignAuthenticationFilter.class.getName());

    private final OAuthServiceClient oAuthServiceClient;

    public FeignAuthenticationFilter(OAuthServiceClient oAuthServiceClient) {
        this.oAuthServiceClient = oAuthServiceClient;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        logger.info("========== FeignAuthenticationFilter ==========");
        logger.info("Request URI : " + request.getRequestURI());
        logger.info("Authorization Header : " + header);

        if (header != null && header.startsWith("Bearer ")) {

            try {

                logger.info("Calling OAuth Service for token validation...");

                ValidationResponse validationResponse =
                        oAuthServiceClient.validate(header);

                logger.info("Validation Response : " + validationResponse);

                if (validationResponse != null && validationResponse.isValid()) {

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    validationResponse.getEmail(),
                                    null,
                                    List.of(new SimpleGrantedAuthority("ROLE_USER"))
                            );

                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(authentication);

                    logger.info("Authentication SUCCESS for : "
                            + validationResponse.getEmail());

                } else {

                    logger.warning("Token validation returned INVALID.");
                }

            } catch (Exception exception) {

                logger.severe("========= OAUTH VALIDATION FAILED =========");
                exception.printStackTrace();

                logger.severe("Message : " + exception.getMessage());
                logger.severe("Exception : " + exception);

            }

        } else {

            logger.warning("Authorization header missing or invalid.");
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {

        String path = request.getServletPath();

        return path.startsWith("/swagger-ui/")
                || path.startsWith("/v3/api-docs/")
                || path.startsWith("/h2-console/")
                || path.startsWith("/actuator/");
    }
}