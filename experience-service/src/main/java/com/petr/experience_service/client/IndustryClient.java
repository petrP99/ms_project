package com.petr.experience_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "industry-service")
public interface IndustryClient {

    @GetMapping("/api/v1/ins/get/{id}")
    Long getIndustryById(@PathVariable("id") Long id);

}
