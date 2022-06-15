package com.triple.clubmileage.api.service.strategy;

import com.triple.clubmileage.api.dto.MileageDto;
import com.triple.clubmileage.api.dto.ReviewEventRequest;

import java.util.List;

public interface ReviewStrategy {

    List<MileageDto> reviewProcessing(ReviewEventRequest request);
}
