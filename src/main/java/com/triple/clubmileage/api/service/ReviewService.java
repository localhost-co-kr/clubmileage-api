package com.triple.clubmileage.api.service;

import com.triple.clubmileage.api.dto.ReviewEventRequest;
import com.triple.clubmileage.api.repository.ReviewRepository;
import com.triple.clubmileage.api.service.factory.ReviewStrategyFactory;
import com.triple.clubmileage.api.service.strategy.EventAction;
import com.triple.clubmileage.api.service.strategy.ReviewStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public String events(@Valid ReviewEventRequest request) {
        ReviewStrategy reviewStrategy = new ReviewStrategyFactory(reviewRepository).create(request.getAction());
        EventAction eventAction = new EventAction(reviewStrategy);
        return eventAction.processing(request);
    }
}
