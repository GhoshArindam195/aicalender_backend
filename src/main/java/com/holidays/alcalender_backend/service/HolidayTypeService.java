package com.holidays.alcalender_backend.service;

import com.holidays.alcalender_backend.dto.HolidayTypeDto;
import com.holidays.alcalender_backend.entity.HolidayType;
import com.holidays.alcalender_backend.mapper.HolidayTypeMapper;
import com.holidays.alcalender_backend.repository.HolidayTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HolidayTypeService {

    @Autowired
    private HolidayTypeRepository holidayTypeRepository;

    @Autowired
    private HolidayTypeMapper holidayTypeMapper;

    public List<HolidayTypeDto> getAllHolidayTypes() {
        List<HolidayType> holidayTypes = holidayTypeRepository.findAll();
        return holidayTypes.stream()
                .map(holidayTypeMapper::toDto)
                .collect(Collectors.toList());
    }
}
