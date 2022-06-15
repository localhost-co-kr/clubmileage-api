package com.triple.clubmileage.api.service.strategy;

import com.triple.clubmileage.api.dto.MileageDto;
import com.triple.clubmileage.api.dto.ReviewEventRequest;
import com.triple.clubmileage.api.repository.ReviewRepository;
import com.triple.clubmileage.api.repository.entity.ReviewEntity;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.util.ObjectUtils.isEmpty;

@Slf4j
public class AddActionStrategy implements ReviewStrategy {

    private final ReviewRepository reviewRepository;

    public AddActionStrategy(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<MileageDto> reviewProcessing(ReviewEventRequest request) {
        String userId = request.getUserId();
        String placeId = request.getPlaceId();

        ReviewEntity reviewEntity = reviewRepository.findByUserIdAndPlaceId(UUID.fromString(userId), UUID.fromString(placeId));
        if (!isEmpty(reviewEntity)) {
            log.error("한 사용자는 장소마다 리뷰를 1개만 작성할 수 있음");
        }

        // TODO: mapstruct
        reviewEntity = new ReviewEntity();
        reviewEntity.setUserId(UUID.fromString(userId));
        reviewEntity.setPlaceId(UUID.fromString(placeId));
        reviewRepository.save(reviewEntity);
        log.debug("reviewEntity.getId() = [{}]", reviewEntity.getId());

        return new ArrayList<>();
    }
}
