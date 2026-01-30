package com.holidays.alcalender_backend.dto;

import com.holidays.alcalender_backend.entity.HolidayType;

public class HolidayDto {

    private Long id;
    private String name;
    private String description;
    private HolidayType holidayType;

    // Constructors
    public HolidayDto() {}

    public HolidayDto(Long id, String name, String description, HolidayType holidayType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.holidayType = holidayType;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public HolidayType getHolidayType() { return holidayType; }
    public void setHolidayType(HolidayType holidayType) { this.holidayType = holidayType; }
}
