package com.holidays.alcalender_backend.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "states")
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    @OneToMany(mappedBy = "state", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HolidayInstance> holidayInstances;

    // Constructors
    public State() {}

    public State(String name, String code) {
        this.name = name;
        this.code = code;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public List<HolidayInstance> getHolidayInstances() { return holidayInstances; }
    public void setHolidayInstances(List<HolidayInstance> holidayInstances) { this.holidayInstances = holidayInstances; }
}
