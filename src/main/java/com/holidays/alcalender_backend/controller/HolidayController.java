package com.holidays.alcalender_backend.controller;

import com.holidays.alcalender_backend.dto.HolidayCalendarDto;
import com.holidays.alcalender_backend.dto.HolidayInstanceDto;
import com.holidays.alcalender_backend.service.HolidayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/holidays")
@Tag(name = "Holiday Management", description = "APIs for managing holidays")
public class HolidayController {

    @Autowired
    private HolidayService holidayService;

    @GetMapping("/{state}/{year}")
    @Operation(summary = "Get holidays for a state and year")
    public ResponseEntity<List<HolidayInstanceDto>> getHolidaysByStateAndYear(
            @PathVariable String state,
            @PathVariable Integer year) {
        List<HolidayInstanceDto> holidays = holidayService.getHolidaysByStateAndYear(state, year);
        return ResponseEntity.ok(holidays);
    }

    @PostMapping
    @Operation(summary = "Add holiday calendar")
    public ResponseEntity<String> createHolidayCalendar(@RequestBody HolidayCalendarDto holidayCalendarDto) {
        holidayService.createHolidayCalendar(holidayCalendarDto);
        return ResponseEntity.ok("Holiday calendar created successfully");
    }
}
