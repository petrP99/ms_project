package com.petr.industryservice.controller;

import com.petr.industryservice.dto.IndustryDto;
import com.petr.industryservice.service.IndustryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ins")
@RequiredArgsConstructor
public class IndustryController {

    private final IndustryService industryService;

    @GetMapping("/get/{id}")
    public IndustryDto getIndustry(@PathVariable Long id) {
        return industryService.getIndustryById(id);
    }

}
