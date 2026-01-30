package com.holidays.alcalender_backend.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "holiday_instances")
public class HolidayInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "holiday_id", nullable = false)
    private Holiday holiday;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Boolean isOptional = false;

    // Constructors
    public HolidayInstance() {}

    public HolidayInstance(Holiday holiday, State state, LocalDate date, Integer year) {
        this.holiday = holiday;
        this.state = state;
        this.date = date;
        this.year = year;
    }

    public HolidayInstance(Holiday holiday, State state, LocalDate date, Integer year, Boolean isOptional) {
        this.holiday = holiday;
        this.state = state;
        this.date = date;
        this.year = year;
        this.isOptional = isOptional;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Holiday getHoliday() { return holiday; }
    public void setHoliday(Holiday holiday) { this.holiday = holiday; }

    public State getState() { return state; }
    public void setState(State state) { this.state = state; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

    public Boolean getIsOptional() { return isOptional; }
    public void setIsOptional(Boolean isOptional) { this.isOptional = isOptional; }
}
