package com.petr.industryservice.controller;

import com.petr.industryservice.dto.IndustryDto;
import com.petr.industryservice.service.IndustryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/v1/ins")
@RequiredArgsConstructor
@Slf4j
public class IndustryController {

    private final IndustryService industryService;
    private static final AtomicInteger counter = new AtomicInteger(0);

    @GetMapping("/get/{id}")
    public IndustryDto getIndustry(@PathVariable Long id) {
        int incrementedAndGet = counter.incrementAndGet();
        if (incrementedAndGet < 6) {
            log.info("incrementedAndGet = {}", incrementedAndGet);
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Incremented industry упал нахуй");
        }
        if (incrementedAndGet >= 8) {
            counter.set(0);
        }
        return industryService.getIndustryById(id);
    }
}
