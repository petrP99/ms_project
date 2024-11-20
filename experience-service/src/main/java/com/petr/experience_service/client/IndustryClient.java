package com.petr.experience_service.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "industry-service")
public interface IndustryClient {

    @GetMapping("/api/v1/ins/get/{name}")
    @Retry(name="industry-service", fallbackMethod = "getIndustryIdFallback")
    @CircuitBreaker(name = "industry-service", fallbackMethod = "getIndustryIdFallback")
    Long getIndustryId(@PathVariable("name") String name);

}
