package com.holidays.alcalender_backend.dto;

public class HolidayDto {

    private Long id;
    private String name;
    private String description;
    private HolidayTypeDto holidayType;

    // Constructors
    public HolidayDto() {}

    public HolidayDto(Long id, String name, String description, HolidayTypeDto holidayType) {
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

    public HolidayTypeDto getHolidayType() { return holidayType; }
    public void setHolidayType(HolidayTypeDto holidayType) { this.holidayType = holidayType; }
}
