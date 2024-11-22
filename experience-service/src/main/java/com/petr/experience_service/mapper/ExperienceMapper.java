package com.petr.experience_service.mapper;

import com.petr.experience_service.dto.ExperienceRequestDto;
import com.petr.experience_service.dto.ExperienceResponseDto;
import com.petr.experience_service.dto.IndustryDto;
import com.petr.experience_service.model.Experience;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
public interface ExperienceMapper {

    @Mapping(target = "id", source = "experience.id")
    ExperienceResponseDto fromEntityToDto(Experience experience, IndustryDto company);

    @Mapping(target = "periodFrom", source = "periodFrom")
    @Mapping(target = "periodTo", source = "periodTo")
    @Mapping(target = "industryId", source = "industryId")
    @Mapping(target = "id", ignore = true)
    Experience fromDtoToEntity(ExperienceRequestDto dto);
}
