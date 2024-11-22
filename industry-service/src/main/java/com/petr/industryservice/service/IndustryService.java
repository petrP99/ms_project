package com.petr.industryservice.service;

import com.petr.industryservice.dto.IndustryDto;
import com.petr.industryservice.repository.IndustryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IndustryService {

    private final IndustryRepository repository;

    public IndustryDto getIndustryById(Long id) {
        return repository.getIndustryById(id);
    }

}


