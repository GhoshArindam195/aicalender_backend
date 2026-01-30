package com.holidays.alcalender_backend.controller;

import com.holidays.alcalender_backend.dto.StateDto;
import com.holidays.alcalender_backend.service.StateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/states")
@Tag(name = "State Management", description = "APIs for managing states")
public class StateController {

    @Autowired
    private StateService stateService;

    @GetMapping
    @Operation(summary = "Get all states")
    public ResponseEntity<List<StateDto>> getAllStates() {
        List<StateDto> states = stateService.getAllStates();
        return ResponseEntity.ok(states);
    }
}
