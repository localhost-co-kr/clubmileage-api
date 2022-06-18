package com.triple.clubmileage.api.controller;

import com.triple.clubmileage.api.dto.MileageRetrieveResponse;
import com.triple.clubmileage.api.service.MileageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MileageRestController {

    private final MileageService mileageService;

    @GetMapping("/{userId}/mileage")
    public Mono<MileageRetrieveResponse> retrieveMileage(@PathVariable("userId") UUID userId) {
        return mileageService.retrieveMileage(userId);
    }
}
