package com.petr.industryservice.controller;

import com.petr.industryservice.dto.IndustryDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ins")
public class ExController {

    @GetMapping
    public List<IndustryDto> getAll() {
        return List.of();
    }

}
