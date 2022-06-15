package com.triple.clubmileage.api.service.strategy;

import com.triple.clubmileage.api.dto.MileageDto;
import com.triple.clubmileage.api.dto.ReviewEventRequest;
import com.triple.clubmileage.api.repository.ReviewRepository;

import java.util.ArrayList;
import java.util.List;

public class ModActionStrategy implements ReviewStrategy {

    private final ReviewRepository reviewRepository;

    public ModActionStrategy(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<MileageDto> reviewProcessing(ReviewEventRequest request) {
        // TODO: implements
        return new ArrayList<>();
    }
}
