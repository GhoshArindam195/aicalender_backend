package com.holidays.alcalender_backend.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "holidays")
public class Holiday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "holiday_type_id", nullable = false)
    private HolidayType holidayType;

    @OneToMany(mappedBy = "holiday", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HolidayInstance> holidayInstances;

    // Constructors
    public Holiday() {}

    public Holiday(String name, String description, HolidayType holidayType) {
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

    public List<HolidayInstance> getHolidayInstances() { return holidayInstances; }
    public void setHolidayInstances(List<HolidayInstance> holidayInstances) { this.holidayInstances = holidayInstances; }
}
