package com.petr.industryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class IndustryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(IndustryServiceApplication.class, args);
    }

}
