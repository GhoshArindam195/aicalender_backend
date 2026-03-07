package com.holidays.alcalender_backend.controller;

import com.holidays.alcalender_backend.service.UaiAuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller for authentication-related endpoints
 * AC1: Handles /logout request to revoke JWT and clear session
 */
@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UaiAuthService uaiAuthService;

    /**
     * Cookie name for JWT token
     */
    private static final String JWT_COOKIE_NAME = "JWT_TOKEN";

    /**
     * Logout endpoint
     * AC1: Retrieve JWT from cookie, send revocation request to UAI, clear local session
     * AC4: Always clear local cookie, even if UAI revocation fails
     *
     * @param request HTTP request to get cookies
     * @param response HTTP response to clear cookies and redirect
     * @return Response with logout status
     */
    @PostMapping("/logout")
    public ResponseEntity<Map<String, Object>> logout(
            HttpServletRequest request,
            HttpServletResponse response) {

        Map<String, Object> result = new HashMap<>();

        // AC1: Retrieve JWT from cookie
        String token = extractTokenFromCookies(request);

        if (token != null && !token.isEmpty()) {
            logger.info("Found token in cookie, attempting revocation with UAI");

            // AC1 & AC2: Send revocation request to UAI with API key
            boolean revocationSuccess = uaiAuthService.revokeToken(token);

            if (revocationSuccess) {
                result.put("message", "Token revoked successfully with UAI");
                logger.info("Token revocation successful");
            } else {
                result.put("message", "UAI revocation failed, local session cleared");
                logger.warn("UAI revocation failed, but continuing with local cleanup");
            }
        } else {
            result.put("message", "No token found in cookies, clearing local session");
            logger.info("No token found in cookies");
        }

        // AC4: Always clear local cookie (MaxAge=0) to protect local session
        clearJwtCookie(response);
        result.put("success", true);

        return ResponseEntity.ok(result);
    }

    /**
     * Get logout status (for checking if user is logged out)
     */
    @GetMapping("/logout/status")
    public ResponseEntity<Map<String, Boolean>> getLogoutStatus(HttpServletRequest request) {
        Map<String, Boolean> status = new HashMap<>();
        String token = extractTokenFromCookies(request);
        status.put("loggedIn", token != null && !token.isEmpty());
        return ResponseEntity.ok(status);
    }

    /**
     * Extract JWT token from cookies
     */
    private String extractTokenFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (JWT_COOKIE_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * Clear JWT cookie by setting MaxAge to 0
     * AC4: Ensures local session is cleared regardless of UAI status
     */
    private void clearJwtCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(JWT_COOKIE_NAME, null);
        cookie.setPath("/");
        cookie.setMaxAge(0); // Delete cookie
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        logger.info("JWT cookie cleared locally");
    }
}
