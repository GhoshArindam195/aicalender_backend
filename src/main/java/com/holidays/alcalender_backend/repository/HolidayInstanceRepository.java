package com.holidays.alcalender_backend.repository;

import com.holidays.alcalender_backend.entity.HolidayInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HolidayInstanceRepository extends JpaRepository<HolidayInstance, Long> {

    @Query("SELECT hi FROM HolidayInstance hi WHERE hi.state.code = :stateCode AND YEAR(hi.date) = :year")
    List<HolidayInstance> findByStateCodeAndYear(@Param("stateCode") String stateCode, @Param("year") Integer year);
}
