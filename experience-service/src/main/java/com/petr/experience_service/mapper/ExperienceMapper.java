package com.petr.experience_service.mapper;

import com.petr.experience_service.dto.ExperienceRequestDto;
import com.petr.experience_service.dto.ExperienceResponseDto;
import com.petr.experience_service.model.Experience;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ExperienceMapper {

    @Mapping(target = "id", ignore = true)
    ExperienceResponseDto fromEntityToDto(Experience experience);

    Experience fromDtoToEntity(ExperienceRequestDto dto);
}
