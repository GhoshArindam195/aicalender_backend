package com.holidays.alcalender_backend.mapper;

import com.holidays.alcalender_backend.dto.HolidayTypeDto;
import com.holidays.alcalender_backend.entity.HolidayType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface HolidayTypeMapper {

    HolidayTypeDto toDto(HolidayType holidayType);

    @Mapping(target = "holidays", ignore = true)
    HolidayType toEntity(HolidayTypeDto holidayTypeDto);
}
