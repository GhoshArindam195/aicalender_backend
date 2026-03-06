package com.holidays.alcalender_backend.controller;

import com.holidays.alcalender_backend.dto.UserPreferenceDto;
import com.holidays.alcalender_backend.service.UserPreferenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/preferences")
@Tag(name = "User Preference Management", description = "APIs for managing user preferences")
public class UserPreferenceController {

    @Autowired
    private UserPreferenceService userPreferenceService;

    /**
     * POST /api/preferences - Save user preference (AC2, AC3)
     * When a user selects a state from the State Selector, they can "Set as Default"
     * The preference is linked to the unique sub (Subject) or email claim from JWT
     */
    @PostMapping
    @Operation(summary = "Save user preference", description = "Save the user's preferred state. Linked to JWT sub/email claim.")
    public ResponseEntity<UserPreferenceDto> savePreference(@RequestBody UserPreferenceDto userPreferenceDto) {
        UserPreferenceDto savedPreference = userPreferenceService.savePreference(userPreferenceDto);
        return ResponseEntity.ok(savedPreference);
    }

    /**
     * PUT /api/preferences/{userId} - Update user preference
     * Update an existing user's preferred state
     */
    @PutMapping("/{userId}")
    @Operation(summary = "Update user preference", description = "Update the user's preferred state.")
    public ResponseEntity<UserPreferenceDto> updatePreference(
            @PathVariable String userId, 
            @RequestBody UserPreferenceDto userPreferenceDto) {
        userPreferenceDto.setUserId(userId);
        UserPreferenceDto updatedPreference = userPreferenceService.savePreference(userPreferenceDto);
        return ResponseEntity.ok(updatedPreference);
    }

    /**
     * DELETE /api/preferences/{userId} - Delete user preference
     * Remove a user's saved preference
     */
    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete user preference", description = "Delete the user's preferred state.")
    public ResponseEntity<String> deletePreference(@PathVariable String userId) {
        userPreferenceService.deletePreference(userId);
        return ResponseEntity.ok("User preference deleted successfully");
    }

    /**
     * GET /api/preferences/{userId} - Get user preference (AC4, AC5)
     * Returns the user's saved state preference or defaults to West Bengal
     */
    @GetMapping("/{userId}")
    @Operation(summary = "Get user preference", description = "Get the user's preferred state. Falls back to West Bengal if not found.")
    public ResponseEntity<UserPreferenceDto> getPreference(@PathVariable String userId) {
        UserPreferenceDto preference = userPreferenceService.getPreferenceByUserId(userId);
        return ResponseEntity.ok(preference);
    }

    /**
     * GET /api/preferences/{userId}/holidays - Get holidays based on user preference (AC4)
     * This endpoint is called during initial handshake after OAuth callback
     * to pre-load the user's state's holidays
     */
    @GetMapping("/{userId}/holidays")
    @Operation(summary = "Get holidays for user's preferred state", 
               description = "Get holidays for the user's preferred state. Used during initial handshake after OAuth callback.")
    public ResponseEntity<?> getHolidaysForUserPreference(
            @PathVariable String userId,
            @RequestParam(required = false, defaultValue = "false") Boolean useDefault) {
        
        UserPreferenceDto preference = userPreferenceService.getPreferenceByUserId(userId);
        
        // Return the preference so frontend can call the Holiday API
        return ResponseEntity.ok(preference);
    }
}
