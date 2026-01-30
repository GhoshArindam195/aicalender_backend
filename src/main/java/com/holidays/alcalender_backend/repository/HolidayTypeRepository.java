package com.holidays.alcalender_backend.repository;

import com.holidays.alcalender_backend.entity.HolidayType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidayTypeRepository extends JpaRepository<HolidayType, Long> {
    HolidayType findByName(String name);
}
