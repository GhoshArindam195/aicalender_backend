package com.holidays.alcalender_backend.dto;

public class UserPreferenceDto {

    private Long id;
    private String userId;
    private String preferredStateCode;
    private String preferredStateName;
    private Integer preferredYear;
    private Boolean isDefault;

    // Constructors
    public UserPreferenceDto() {}

    public UserPreferenceDto(String userId, String preferredStateCode, String preferredStateName, Integer preferredYear) {
        this.userId = userId;
        this.preferredStateCode = preferredStateCode;
        this.preferredStateName = preferredStateName;
        this.preferredYear = preferredYear;
        this.isDefault = true;
    }

    public UserPreferenceDto(Long id, String userId, String preferredStateCode, String preferredStateName, 
                            Integer preferredYear, Boolean isDefault) {
        this.id = id;
        this.userId = userId;
        this.preferredStateCode = preferredStateCode;
        this.preferredStateName = preferredStateName;
        this.preferredYear = preferredYear;
        this.isDefault = isDefault;
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
