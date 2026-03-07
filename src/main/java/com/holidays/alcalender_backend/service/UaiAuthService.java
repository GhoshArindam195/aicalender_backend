package com.holidays.alcalender_backend.service;

import com.holidays.alcalender_backend.config.UaiAuthConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Service for handling UAI authentication operations
 * AC1: Performs server-to-server POST request to UAI revocation endpoint
 * AC2: Includes X-API-Key header for authorization
 * AC4: Error handling - clears local session even if UAI is unreachable
 */
@Service
public class UaiAuthService {

    private static final Logger logger = LoggerFactory.getLogger(UaiAuthService.class);

    @Autowired
    private UaiAuthConfig uaiAuthConfig;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Revoke the JWT token with the UAI provider
     * AC1: Send POST request to UAI_BASE_URL/api/auth/revoke
     * AC2: Include X-API-Key header for authorization
     * AC4: Return true even if UAI is unreachable (local cleanup will still happen)
     *
     * @param token the JWT token to revoke
     * @return true if revocation was successful, false if UAI was unreachable
     */
    public boolean revokeToken(String token) {
        if (token == null || token.isEmpty()) {
            logger.warn("No token provided for revocation");
            return false;
        }

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            // AC2: Add API Key header for server-to-server authorization
            headers.set("X-API-Key", uaiAuthConfig.getApiKey());

            // Create request body with token
            String requestBody = "{\"token\":\"" + token + "\"}";

            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

            logger.info("Sending token revocation request to UAI: {}", uaiAuthConfig.getRevokeUrl());

            ResponseEntity<String> response = restTemplate.exchange(
                    uaiAuthConfig.getRevokeUrl(),
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            if (response.getStatusCode() == HttpStatus.OK || response.getStatusCode() == HttpStatus.NO_CONTENT) {
                logger.info("Token revocation successful with UAI");
                return true;
            } else {
                logger.warn("Token revocation returned unexpected status: {}", response.getStatusCode());
                return false;
            }

        } catch (HttpClientErrorException e) {
            logger.error("HTTP error during token revocation: {} - {}", e.getStatusCode(), e.getResponseBodyAsString());
            // AC4: Return false but don't throw - local cleanup should still happen
            return false;
        } catch (Exception e) {
            // AC4: If UAI server (:5000) is unreachable, log error but continue
            logger.error("Failed to reach UAI server for token revocation: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Check if UAI configuration is properly set
     */
    public boolean isConfigured() {
        return uaiAuthConfig.getApiKey() != null && !uaiAuthConfig.getApiKey().isEmpty();
    }
}
