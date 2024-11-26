package com.petr.experience_service.repository;

import com.petr.experience_service.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {

    Experience getExperienceById(Long id);

    List<Experience> getExperiencesBy();
}
