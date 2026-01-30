package com.holidays.alcalender_backend.repository;

import com.holidays.alcalender_backend.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

    Optional<State> findByCode(String code);
}
