package com.holidays.alcalender_backend.dto;

import java.util.List;

public class StateHolidaysDto {

    private String code;
    private String name;
    private List<HolidayInstanceDto> holidays;

    // Constructors
    public StateHolidaysDto() {}

    public StateHolidaysDto(String code, String name, List<HolidayInstanceDto> holidays) {
        this.code = code;
        this.name = name;
        this.holidays = holidays;
    }

    // Getters and Setters
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<HolidayInstanceDto> getHolidays() { return holidays; }
    public void setHolidays(List<HolidayInstanceDto> holidays) { this.holidays = holidays; }
}
