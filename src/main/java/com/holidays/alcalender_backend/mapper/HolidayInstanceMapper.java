package com.holidays.alcalender_backend.mapper;

import com.holidays.alcalender_backend.dto.HolidayInstanceDto;
import com.holidays.alcalender_backend.entity.HolidayInstance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {HolidayMapper.class, StateMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface HolidayInstanceMapper {

    @Mapping(target = "name", source = "holiday.name")
    @Mapping(target = "holidayType", source = "holiday.holidayType.name")
    @Mapping(target = "stateCode", source = "state.code")
    @Mapping(target = "stateName", source = "state.name")
    HolidayInstanceDto toDto(HolidayInstance holidayInstance);

    HolidayInstance toEntity(HolidayInstanceDto holidayInstanceDto);
}
