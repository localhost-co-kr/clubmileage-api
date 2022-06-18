package com.triple.clubmileage.api.controller;

import com.triple.clubmileage.api.dto.ReviewEventRequest;
import com.triple.clubmileage.api.dto.ReviewEventResponse;
import com.triple.clubmileage.api.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class ReviewRestController {

    private final ReviewService reviewService;

    @PostMapping("/events")
    public Mono<ReviewEventResponse> events(@Valid @RequestBody ReviewEventRequest reviewEventRequest) {
        String response = reviewService.events(reviewEventRequest);

        return Mono.just(ReviewEventResponse.of(response));
    }
}
