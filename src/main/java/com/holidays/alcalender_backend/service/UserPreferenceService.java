package com.holidays.alcalender_backend.service;

import com.holidays.alcalender_backend.dto.UserPreferenceDto;
import com.holidays.alcalender_backend.entity.UserPreference;
import com.holidays.alcalender_backend.mapper.UserPreferenceMapper;
import com.holidays.alcalender_backend.repository.UserPreferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserPreferenceService {

    // Default fallback values as per AC5
    private static final String DEFAULT_STATE_CODE = "WB";
    private static final String DEFAULT_STATE_NAME = "West Bengal";
    
    @Autowired
    private UserPreferenceRepository userPreferenceRepository;

    @Autowired
    private UserPreferenceMapper userPreferenceMapper;

    /**
     * Save or update user preference
     * @param userPreferenceDto the preference data
     * @return the saved preference
     */
    @Transactional
    public UserPreferenceDto savePreference(UserPreferenceDto userPreferenceDto) {
        UserPreference userPreference = userPreferenceMapper.toEntity(userPreferenceDto);
        
        // Check if preference already exists for this user
        Optional<UserPreference> existingPreference = userPreferenceRepository.findByUserId(userPreferenceDto.getUserId());
        
        if (existingPreference.isPresent()) {
            // Update existing preference
            UserPreference existing = existingPreference.get();
            existing.setPreferredStateCode(userPreference.getPreferredStateCode());
            existing.setPreferredStateName(userPreference.getPreferredStateName());
            existing.setPreferredYear(userPreference.getPreferredYear());
            existing.setIsDefault(userPreference.getIsDefault());
            userPreference = userPreferenceRepository.save(existing);
        } else {
            // Create new preference
            userPreference = userPreferenceRepository.save(userPreference);
        }
        
        return userPreferenceMapper.toDto(userPreference);
    }

    /**
     * Get user preference by userId
     * @param userId the JWT sub/email claim
     * @return the user preference or default if not found
     */
    @Transactional(readOnly = true)
    public UserPreferenceDto getPreferenceByUserId(String userId) {
        Optional<UserPreference> preference = userPreferenceRepository.findByUserId(userId);
        
        if (preference.isPresent()) {
            return userPreferenceMapper.toDto(preference.get());
        }
        
        // Fallback to default (AC5)
        return getDefaultPreference();
    }

    /**
     * Get the default preference (West Bengal)
     * @return default preference DTO
     */
    public UserPreferenceDto getDefaultPreference() {
        UserPreferenceDto defaultPreference = new UserPreferenceDto();
        defaultPreference.setUserId(null);
        defaultPreference.setPreferredStateCode(DEFAULT_STATE_CODE);
        defaultPreference.setPreferredStateName(DEFAULT_STATE_NAME);
        defaultPreference.setPreferredYear(LocalDate.now().getYear());
        defaultPreference.setIsDefault(true);
        return defaultPreference;
    }

    /**
     * Check if a user has saved preferences
     * @param userId the user ID
     * @return true if preference exists
     */
    @Transactional(readOnly = true)
    public boolean hasPreference(String userId) {
        return userPreferenceRepository.existsByUserId(userId);
    }

    /**
     * Delete user preference
     * @param userId the user ID
     */
    @Transactional
    public void deletePreference(String userId) {
        userPreferenceRepository.deleteByUserId(userId);
    }
}
