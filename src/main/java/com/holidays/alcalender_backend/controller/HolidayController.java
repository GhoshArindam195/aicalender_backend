package com.holidays.alcalender_backend.controller;

import com.holidays.alcalender_backend.dto.HolidayCalendarDto;
import com.holidays.alcalender_backend.dto.HolidayInstanceDto;
import com.holidays.alcalender_backend.dto.UserPreferenceDto;
import com.holidays.alcalender_backend.service.HolidayService;
import com.holidays.alcalender_backend.service.UserPreferenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/holidays")
@Tag(name = "Holiday Management", description = "APIs for managing holidays")
public class HolidayController {

    @Autowired
    private HolidayService holidayService;

    @Autowired
    private UserPreferenceService userPreferenceService;

    @GetMapping("/{state}/{year}")
    @Operation(summary = "Get holidays for a state and year")
    public ResponseEntity<List<HolidayInstanceDto>> getHolidaysByStateAndYear(
            @PathVariable String state,
            @PathVariable Integer year) {
        List<HolidayInstanceDto> holidays = holidayService.getHolidaysByStateAndYear(state, year);
        return ResponseEntity.ok(holidays);
    }

    /**
     * AC4: Get holidays with user preference
     * This endpoint is called during initial handshake after OAuth callback
     * It returns both the holidays and the user's preference
     */
    @GetMapping("/user/{userId}")
    @Operation(summary = "Get holidays for user's preferred state",
               description = "Returns holidays for user's preferred state. Falls back to West Bengal if no preference found.")
    public ResponseEntity<Map<String, Object>> getHolidaysForUser(@PathVariable String userId) {
        // Get user preference (falls back to default if not found - AC5)
        UserPreferenceDto preference = userPreferenceService.getPreferenceByUserId(userId);
        
        // Get holidays for the preferred state
        List<HolidayInstanceDto> holidays = holidayService.getHolidaysByStateAndYear(
            preference.getPreferredStateCode(), 
            preference.getPreferredYear()
        );
        
        // Return both preference and holidays
        Map<String, Object> response = new HashMap<>();
        response.put("preference", preference);
        response.put("holidays", holidays);
        
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Operation(summary = "Add holiday calendar")
    public ResponseEntity<String> createHolidayCalendar(@RequestBody HolidayCalendarDto holidayCalendarDto) {
        holidayService.createHolidayCalendar(holidayCalendarDto);
        return ResponseEntity.ok("Holiday calendar created successfully");
    }
}
