package com.petr.industryservice.controller;

import com.petr.industryservice.dto.IndustryRequestDto;
import com.petr.industryservice.model.Industry;
import com.petr.industryservice.service.IndustryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ins")
@RequiredArgsConstructor
public class IndustryController {

    private final IndustryService industryService;

    @GetMapping("/get/{name}")
    public Long getIndustry(@PathVariable String name) {
        return industryService.getIndustryByName(name);
    }

    @PostMapping("/add")
    public String add(@Valid @RequestBody IndustryRequestDto dto) {
        industryService.addCompany(dto);
        return "Успешно добавлено";
    }


}
