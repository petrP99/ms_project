package com.petr.experience_service.service;

import com.petr.experience_service.dto.ExperienceRequestDto;
import com.petr.experience_service.dto.ExperienceResponseDto;
import com.petr.experience_service.mapper.ExperienceMapper;
import com.petr.experience_service.model.Experience;
import com.petr.experience_service.repository.ExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExperienceService {

    private final ExperienceRepository repository;
    private final ExperienceMapper mapper;

    public ExperienceResponseDto getExperience(Long id) {
        return repository.getExperienceById(id);
    }

    public ExperienceResponseDto saveExperience(ExperienceRequestDto requestDto) {
        Experience experience = mapper.fromDtoToEntity(requestDto);
        Experience savedExperience = repository.save(experience);
        return mapper.fromEntityToDto(savedExperience);
    }
}
