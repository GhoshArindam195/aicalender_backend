package com.holidays.alcalender_backend.service;

import com.holidays.alcalender_backend.dto.HolidayCalendarDto;
import com.holidays.alcalender_backend.dto.HolidayInstanceDto;
import com.holidays.alcalender_backend.dto.StateHolidaysDto;
import com.holidays.alcalender_backend.entity.Holiday;
import com.holidays.alcalender_backend.entity.HolidayInstance;
import com.holidays.alcalender_backend.entity.HolidayType;
import com.holidays.alcalender_backend.entity.State;
import com.holidays.alcalender_backend.mapper.HolidayInstanceMapper;
import com.holidays.alcalender_backend.repository.HolidayInstanceRepository;
import com.holidays.alcalender_backend.repository.HolidayRepository;
import com.holidays.alcalender_backend.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HolidayService {

    @Autowired
    private HolidayInstanceRepository holidayInstanceRepository;

    @Autowired
    private HolidayInstanceMapper holidayInstanceMapper;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private HolidayRepository holidayRepository;

    public List<HolidayInstanceDto> getHolidaysByStateAndYear(String stateCode, Integer year) {
        List<HolidayInstance> holidayInstances = holidayInstanceRepository.findByStateCodeAndYear(stateCode, year);
        return holidayInstances.stream()
                .map(holidayInstanceMapper::toDto)
                .collect(Collectors.toList());
    }

    public HolidayInstanceDto createHolidayInstance(HolidayInstanceDto holidayInstanceDto) {
        HolidayInstance holidayInstance = holidayInstanceMapper.toEntity(holidayInstanceDto);
        HolidayInstance savedHolidayInstance = holidayInstanceRepository.save(holidayInstance);
        return holidayInstanceMapper.toDto(savedHolidayInstance);
    }

    public void createHolidayCalendar(HolidayCalendarDto holidayCalendarDto) {
        for (StateHolidaysDto stateHolidays : holidayCalendarDto.getStates()) {
            State state = stateRepository.findByCode(stateHolidays.getCode()).orElse(null);
            if (state == null) {
                state = new State();
                state.setCode(stateHolidays.getCode());
                state.setName(stateHolidays.getName());
                state = stateRepository.save(state);
            }
            for (HolidayInstanceDto holidayDto : stateHolidays.getHolidays()) {
                // Map the holiday type enum from the DTO
                HolidayType holidayType = holidayDto.getHolidayType();
                if (holidayType == null) {
                    holidayType = HolidayType.REGIONAL; // Default to Regional
                }
                
                Holiday holiday = holidayRepository.findByName(holidayDto.getName());
                if (holiday == null) {
                    holiday = new Holiday();
                    holiday.setName(holidayDto.getName());
                    holiday.setHolidayType(holidayType);
                    holiday = holidayRepository.save(holiday);
                }
                HolidayInstance holidayInstance = new HolidayInstance();
                holidayInstance.setHoliday(holiday);
                holidayInstance.setState(state);
                holidayInstance.setDate(holidayDto.getDate());
                holidayInstance.setIsOptional(holidayDto.getIsOptional());
                holidayInstanceRepository.save(holidayInstance);
            }
        }
    }
}
