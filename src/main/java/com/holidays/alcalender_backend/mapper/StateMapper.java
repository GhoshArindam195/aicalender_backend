package com.holidays.alcalender_backend.mapper;

import com.holidays.alcalender_backend.dto.StateDto;
import com.holidays.alcalender_backend.entity.State;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface StateMapper {

    StateDto toDto(State state);

    State toEntity(StateDto stateDto);
}
