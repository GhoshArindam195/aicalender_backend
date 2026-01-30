package com.holidays.alcalender_backend.dto;

import java.util.List;

public class HolidayCalendarDto {

    private Integer year;
    private List<StateHolidaysDto> states;

    // Constructors
    public HolidayCalendarDto() {}

    public HolidayCalendarDto(Integer year, List<StateHolidaysDto> states) {
        this.year = year;
        this.states = states;
    }

    // Getters and Setters
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

    public List<StateHolidaysDto> getStates() { return states; }
    public void setStates(List<StateHolidaysDto> states) { this.states = states; }
}
