package com.petr.experience_service.client;

import com.petr.experience_service.dto.IndustryDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "industry-service")
public interface IndustryClient {

    @GetMapping("/api/v1/ins/get/{id}")
    @Retry(name = "industry-service", fallbackMethod = "getIndustryIdFallback")
    @CircuitBreaker(name = "industry-service", fallbackMethod = "getIndustryIdFallback")
    IndustryDto getIndustryId(@PathVariable Long id);

    default IndustryDto getIndustryIdFallback(Long id, Throwable throwable) {
        System.out.println("Method getIndustryId, with args: " + id + throwable.getMessage());
        return IndustryDto.builder().build();
    }

}
