package com.holidays.alcalender_backend.repository;

import com.holidays.alcalender_backend.entity.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPreferenceRepository extends JpaRepository<UserPreference, Long> {

    Optional<UserPreference> findByUserId(String userId);

    boolean existsByUserId(String userId);

    @Modifying
    void deleteByUserId(String userId);
}
