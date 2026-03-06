package com.holidays.alcalender_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_preferences")
public class UserPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId;  // JWT sub or email claim

    @Column(nullable = false)
    private String preferredStateCode;

    @Column(nullable = false)
    private String preferredStateName;

    @Column(nullable = false)
    private Integer preferredYear;

    @Column(nullable = false)
    private Boolean isDefault = true;

    // Constructors
    public UserPreference() {}

    public UserPreference(String userId, String preferredStateCode, String preferredStateName, Integer preferredYear) {
        this.userId = userId;
        this.preferredStateCode = preferredStateCode;
        this.preferredStateName = preferredStateName;
        this.preferredYear = preferredYear;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getPreferredStateCode() { return preferredStateCode; }
    public void setPreferredStateCode(String preferredStateCode) { this.preferredStateCode = preferredStateCode; }

    public String getPreferredStateName() { return preferredStateName; }
    public void setPreferredStateName(String preferredStateName) { this.preferredStateName = preferredStateName; }

    public Integer getPreferredYear() { return preferredYear; }
    public void setPreferredYear(Integer preferredYear) { this.preferredYear = preferredYear; }

    public Boolean getIsDefault() { return isDefault; }
    public void setIsDefault(Boolean isDefault) { this.isDefault = isDefault; }
}
