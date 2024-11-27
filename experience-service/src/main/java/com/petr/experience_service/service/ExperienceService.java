package com.petr.experience_service.service;

import com.petr.experience_service.client.IndustryClient;
import com.petr.experience_service.dto.ExperienceRequestDto;
import com.petr.experience_service.dto.ExperienceResponseDto;
import com.petr.experience_service.dto.IndustryDto;
import com.petr.experience_service.mapper.ExperienceMapper;
import com.petr.experience_service.model.Experience;
import com.petr.experience_service.repository.ExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class ExperienceService {

    private final ExperienceRepository repository;
    private final ExperienceMapper mapper;
    private final IndustryClient industryClient;

    public Optional<ExperienceResponseDto> getExperience(Long id) {
        Experience experience = repository.getExperienceById(id);
        IndustryDto industryDto = getIndustry(experience.getIndustryId());
        return Optional.of(experience).stream()
                .map(it -> mapper.fromEntityToDto(it, industryDto))
                .findFirst();
    }

    public List<ExperienceResponseDto> getAllExperience() {
        List<Experience> experiencesBy = repository.getExperiencesBy();
        return experiencesBy.stream()
                .map(experience -> mapper.fromEntityToDto(experience, getIndustry(experience.getIndustryId())))
                .toList();
    }

    public ExperienceResponseDto saveExperience(ExperienceRequestDto requestDto) {
        Experience experience = mapper.fromDtoToEntity(requestDto);
        IndustryDto industry = getIndustry(experience.getIndustryId());
        experience.setSequenceNumber(RandomUtils.nextInt());
        experience.setPresentTime(isNull(experience.getPeriodTo()));
        Experience savedExperience = repository.save(experience);
        return mapper.fromEntityToDto(savedExperience, industry);
    }

    public IndustryDto getIndustry(Long id) {
        return industryClient.getIndustryId(id);
    }
}
