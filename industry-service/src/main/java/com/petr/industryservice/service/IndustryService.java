package com.petr.industryservice.service;

import com.petr.industryservice.model.Industry;
import com.petr.industryservice.repository.IndustryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IndustryService {

    private final IndustryRepository repository;

    public Industry getIndustryById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("No such industry"));
    }
}


