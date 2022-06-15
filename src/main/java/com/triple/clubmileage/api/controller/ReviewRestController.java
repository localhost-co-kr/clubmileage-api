package com.triple.clubmileage.api.controller;

import com.triple.clubmileage.api.dto.ReviewEventRequest;
import com.triple.clubmileage.api.dto.ReviewEventResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class ReviewRestController {

    @PostMapping("/events")
    public Mono<ReviewEventResponse> events(@Valid @RequestBody ReviewEventRequest reviewEventRequest) {
        return Mono.just(new ReviewEventResponse());
    }
}
