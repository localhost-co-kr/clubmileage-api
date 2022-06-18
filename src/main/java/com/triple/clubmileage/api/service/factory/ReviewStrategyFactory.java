package com.triple.clubmileage.api.service.factory;

import com.triple.clubmileage.api.enumtype.ActionType;
import com.triple.clubmileage.api.repository.PlaceRepository;
import com.triple.clubmileage.api.repository.ReviewRepository;
import com.triple.clubmileage.api.service.strategy.AddActionStrategy;
import com.triple.clubmileage.api.service.strategy.DeleteActionStrategy;
import com.triple.clubmileage.api.service.strategy.ModActionStrategy;
import com.triple.clubmileage.api.service.strategy.ReviewStrategy;
import lombok.RequiredArgsConstructor;

/**
 * 요구사항이 REST API 설계였지만, POST METHOD 하나만 사용하는것으로 스펙 정의가 되어있어서
 * ActionType 기준으로 분기 처리하자니 Service Layer 로직이 지저분해지는 것 같아서
 * Strategy Pattern 으로 처리함
 */
@RequiredArgsConstructor
public class ReviewStrategyFactory {

    private final ReviewRepository reviewRepository;
    private final PlaceRepository placeRepository;

    public ReviewStrategy create(ActionType action) {
        switch (action) {
            case ADD:
                return new AddActionStrategy(reviewRepository, placeRepository);
            case MOD:
                return new ModActionStrategy(reviewRepository);
            case DELETE:
                return new DeleteActionStrategy(reviewRepository);
            default:
                throw new IllegalStateException("Unexpected value: " + action);
        }
    }
}
