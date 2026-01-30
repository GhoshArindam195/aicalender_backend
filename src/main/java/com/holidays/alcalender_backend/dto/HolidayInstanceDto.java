package com.holidays.alcalender_backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.holidays.alcalender_backend.entity.HolidayType;
import java.time.LocalDate;

public class HolidayInstanceDto {

    private Long id;
    private String name;
    private HolidayType holidayType;
    private String stateCode;
    private String stateName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private Integer year;
    private Boolean isOptional;

    // Constructors
    public HolidayInstanceDto() {}

    public HolidayInstanceDto(Long id, String name, HolidayType holidayType, String stateCode, String stateName, LocalDate date, Integer year, Boolean isOptional) {
        this.id = id;
        this.name = name;
        this.holidayType = holidayType;
        this.stateCode = stateCode;
        this.stateName = stateName;
        this.date = date;
        this.year = year;
        this.isOptional = isOptional;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public HolidayType getHolidayType() { return holidayType; }
    public void setHolidayType(HolidayType holidayType) { this.holidayType = holidayType; }

    public String getStateCode() { return stateCode; }
    public void setStateCode(String stateCode) { this.stateCode = stateCode; }

    public String getStateName() { return stateName; }
    public void setStateName(String stateName) { this.stateName = stateName; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

    public Boolean getIsOptional() { return isOptional; }
    public void setIsOptional(Boolean isOptional) { this.isOptional = isOptional; }
}
