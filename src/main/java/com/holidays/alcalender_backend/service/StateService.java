package com.holidays.alcalender_backend.service;

import com.holidays.alcalender_backend.dto.StateDto;
import com.holidays.alcalender_backend.entity.State;
import com.holidays.alcalender_backend.mapper.StateMapper;
import com.holidays.alcalender_backend.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private StateMapper stateMapper;

    public List<StateDto> getAllStates() {
        List<State> states = stateRepository.findAll();
        return states.stream()
                .map(stateMapper::toDto)
                .collect(Collectors.toList());
    }
}
