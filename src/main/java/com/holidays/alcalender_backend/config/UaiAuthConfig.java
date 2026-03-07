package com.holidays.alcalender_backend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for UAI (User Authentication Integration) service
 * AC2: API Key Security - stores API key for server-to-server calls
 */
@Configuration
@ConfigurationProperties(prefix = "uai.auth")
public class UaiAuthConfig {

    private String baseUrl = "http://localhost:5000";
    private String apiKey;
    private String revokeEndpoint = "/api/auth/revoke";

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getRevokeEndpoint() {
        return revokeEndpoint;
    }

    public void setRevokeEndpoint(String revokeEndpoint) {
        this.revokeEndpoint = revokeEndpoint;
    }

    /**
     * Get the full revocation URL
     */
    public String getRevokeUrl() {
        return baseUrl + revokeEndpoint;
    }
}
