package com.petr.industryservice.controller;

import com.petr.industryservice.dto.IndustryDto;
import com.petr.industryservice.service.IndustryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ins")
@RequiredArgsConstructor
@Slf4j
public class IndustryController {

    private final IndustryService industryService;

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN_INDS')")
    public IndustryDto getIndustry(@PathVariable Long id) {
        return industryService.getIndustryById(id);
    }

}
