package com.triple.clubmileage.api.service;

import com.triple.clubmileage.api.dto.ReviewEventRequest;
import com.triple.clubmileage.api.repository.PlaceRepository;
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

    private final MileageService mileageService;
    private final ReviewRepository reviewRepository;
    private final PlaceRepository placeRepository;

    public String events(@Valid ReviewEventRequest request) {
        ReviewStrategy reviewStrategy = new ReviewStrategyFactory(reviewRepository, placeRepository).create(request.getAction());
        EventAction eventAction = new EventAction(mileageService, reviewStrategy);
        return eventAction.processing(request);
    }
}
