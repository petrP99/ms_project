package com.petr.experience_service.service;

import com.petr.experience_service.client.IndustryClient;
import com.petr.experience_service.dto.ExperienceRequestDto;
import com.petr.experience_service.dto.ExperienceResponseDto;
import com.petr.experience_service.mapper.ExperienceMapper;
import com.petr.experience_service.model.Experience;
import com.petr.experience_service.repository.ExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.lang.math.RandomUtils.nextInt;

@Service
@RequiredArgsConstructor
public class ExperienceService {

    private final ExperienceRepository repository;
    private final ExperienceMapper mapper;
    private final IndustryClient industryClient;

    public ExperienceResponseDto getExperience(Long id) {
        return repository.getExperienceById(id);
    }

    public List<ExperienceResponseDto> getAllExperience() {
        return repository.getExperiencesBy();
    }

    public ExperienceResponseDto saveExperience(ExperienceRequestDto requestDto) {
        Experience experience = mapper.fromDtoToEntity(requestDto);
        Long industryId = getIndustry(experience.getCompany());
        experience.setSequenceNumber(nextInt());
        experience.setPresentTime(experience.getPeriodTo() == null);
        experience.setIndustryId(industryId);
        Experience savedExperience = repository.save(experience);
        return mapper.fromEntityToDto(savedExperience);
    }

    public Long getIndustry(String name) {
        return industryClient.getIndustry(name);
    }
}
