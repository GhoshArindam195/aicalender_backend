package com.holidays.alcalender_backend.mapper;

import com.holidays.alcalender_backend.dto.HolidayDto;
import com.holidays.alcalender_backend.entity.Holiday;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = HolidayTypeMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface HolidayMapper {

    HolidayDto toDto(Holiday holiday);

    Holiday toEntity(HolidayDto holidayDto);
}
