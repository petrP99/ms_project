package com.petr.experience_service.client;

import com.petr.experience_service.dto.IndustryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//@FeignClient(name = "industry-service")
public interface IndustryClient {

    @GetMapping("/api/v1/ins")
    List<IndustryDto> getAllIndustry();

}
