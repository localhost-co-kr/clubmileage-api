package com.triple.clubmileage.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@OpenAPIDefinition(info = @Info(title = "트리플여행자 클럽 마일리지 서비스", version = "0.1", description = "API v0.1"))
@EnableAsync
@SpringBootApplication
public class ClubmileageApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClubmileageApiApplication.class, args);
    }
}
