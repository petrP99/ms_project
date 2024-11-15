package com.petr.experience_service.repository;

import com.petr.experience_service.dto.ExperienceResponseDto;
import com.petr.experience_service.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {

    ExperienceResponseDto getExperienceById(Long id);
}
