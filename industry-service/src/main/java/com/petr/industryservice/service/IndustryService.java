package com.petr.industryservice.service;

import com.petr.industryservice.dto.IndustryRequestDto;
import com.petr.industryservice.model.Industry;
import com.petr.industryservice.repository.IndustryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IndustryService {

    private final IndustryRepository repository;

    public Long getIndustryByName(String name) {
        return repository.findByName(name).getId();
    }

    public void addCompany(IndustryRequestDto dto) {
        Industry industry = Industry.builder()
                .withName(dto.name())
                .build();
        repository.save(industry);
    }
}


