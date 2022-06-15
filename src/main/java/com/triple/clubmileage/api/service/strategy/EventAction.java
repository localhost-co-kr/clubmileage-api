package com.triple.clubmileage.api.service.strategy;

import com.triple.clubmileage.api.dto.MileageDto;
import com.triple.clubmileage.api.dto.ReviewEventRequest;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class EventAction {

    private ReviewStrategy reviewStrategy;

    public void setReviewStrategy(ReviewStrategy reviewStrategy) {
        this.reviewStrategy = reviewStrategy;
    }

    public String processing(ReviewEventRequest request) {
        List<MileageDto> mileageDtos = reviewStrategy.reviewProcessing(request);

        // TODO: mileage history 적재
        return null;
    }
}
