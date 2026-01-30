package com.holidays.alcalender_backend.controller;

import com.holidays.alcalender_backend.dto.HolidayTypeDto;
import com.holidays.alcalender_backend.service.HolidayTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/holiday-types")
@Tag(name = "Holiday Type Management", description = "APIs for managing holiday types")
public class HolidayTypeController {

    @Autowired
    private HolidayTypeService holidayTypeService;

    @GetMapping
    @Operation(summary = "Get all holiday types")
    public ResponseEntity<List<HolidayTypeDto>> getAllHolidayTypes() {
        List<HolidayTypeDto> holidayTypes = holidayTypeService.getAllHolidayTypes();
        return ResponseEntity.ok(holidayTypes);
    }
}
