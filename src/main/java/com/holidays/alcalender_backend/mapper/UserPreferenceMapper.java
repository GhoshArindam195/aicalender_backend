package com.holidays.alcalender_backend.mapper;

import com.holidays.alcalender_backend.dto.UserPreferenceDto;
import com.holidays.alcalender_backend.entity.UserPreference;
import org.springframework.stereotype.Component;

@Component
public class UserPreferenceMapper {

    public UserPreferenceDto toDto(UserPreference userPreference) {
        if (userPreference == null) {
            return null;
        }
        return new UserPreferenceDto(
            userPreference.getId(),
            userPreference.getUserId(),
            userPreference.getPreferredStateCode(),
            userPreference.getPreferredStateName(),
            userPreference.getPreferredYear(),
            userPreference.getIsDefault()
        );
    }

    public UserPreference toEntity(UserPreferenceDto userPreferenceDto) {
        if (userPreferenceDto == null) {
            return null;
        }
        UserPreference userPreference = new UserPreference();
        userPreference.setId(userPreferenceDto.getId());
        userPreference.setUserId(userPreferenceDto.getUserId());
        userPreference.setPreferredStateCode(userPreferenceDto.getPreferredStateCode());
        userPreference.setPreferredStateName(userPreferenceDto.getPreferredStateName());
        userPreference.setPreferredYear(userPreferenceDto.getPreferredYear());
        userPreference.setIsDefault(userPreferenceDto.getIsDefault());
        return userPreference;
    }
}
