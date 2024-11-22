package com.petr.industryservice.repository;

import com.petr.industryservice.dto.IndustryDto;
import com.petr.industryservice.model.Industry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndustryRepository extends JpaRepository<Industry, Long> {

    IndustryDto getIndustryById(Long id);
}
