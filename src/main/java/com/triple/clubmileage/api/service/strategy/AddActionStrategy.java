package com.triple.clubmileage.api.service.strategy;

import com.triple.clubmileage.api.common.exception.ClubmilageApiException;
import com.triple.clubmileage.api.dto.MileageDto;
import com.triple.clubmileage.api.dto.ReviewEventRequest;
import com.triple.clubmileage.api.dto.mapper.MileageDtoMapper;
import com.triple.clubmileage.api.dto.mapper.ReviewEventRequestMapper;
import com.triple.clubmileage.api.enumtype.MileageType;
import com.triple.clubmileage.api.enumtype.exception.ClubmileageExceptionCode;
import com.triple.clubmileage.api.repository.PlaceRepository;
import com.triple.clubmileage.api.repository.ReviewRepository;
import com.triple.clubmileage.api.repository.entity.PlaceEntity;
import com.triple.clubmileage.api.repository.entity.ReviewEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.ObjectUtils.isEmpty;

@Slf4j
@RequiredArgsConstructor
public class AddActionStrategy implements ReviewStrategy {

    private final ReviewRepository reviewRepository;
    private final PlaceRepository placeRepository;

    @Override
    public List<MileageDto> reviewProcessing(ReviewEventRequest request) {
        validation(request);

        ReviewEntity reviewEntity = ReviewEventRequestMapper.INSTANCE.reviewEventRequestToReviewEntity(request);
        reviewRepository.save(reviewEntity);

        return calculatorMileage(reviewEntity);
    }

    private void validation(ReviewEventRequest request) {
        Optional<PlaceEntity> optionalPlaceEntity = placeRepository.findById(request.getPlaceId());
        if (optionalPlaceEntity.isEmpty()) {
            log.error("잘못된 장소에 리뷰 작성 userId = [{}], placeId = [{}]", request.getUserId(), request.getPlaceId());
            throw new ClubmilageApiException(ClubmileageExceptionCode.INVALID_PLACE_ID);
        }

        ReviewEntity reviewEntity = reviewRepository.findByUserIdAndPlaceId(request.getUserId(), request.getPlaceId());
        if (!isEmpty(reviewEntity)) {
            log.error("중복된 장소에 리뷰 작성 userId = [{}], placeId = [{}]", request.getUserId(), request.getPlaceId());
            throw new ClubmilageApiException(ClubmileageExceptionCode.DUPLICATE_PLACE_REVIEW);
        }
    }

    private List<MileageDto> calculatorMileage(ReviewEntity reviewEntity) {
        List<MileageDto> mileageDtos = new ArrayList<>();
        if (!isEmpty(reviewEntity.getId())) {
            // 리뷰 텍스트 작성 포인트
            if (!isEmpty(reviewEntity.getContent())) {
                MileageDto mileageDto = MileageDtoMapper.INSTANCE.reviewEntityToMileageDto(reviewEntity, MileageType.REVIEW_TEXT_WRITE);
                mileageDtos.add(mileageDto);
            }
            // 리뷰 이미지 첨부 포인트
            if (reviewEntity.getImageEntities().size() > 0) {
                MileageDto mileageDto = MileageDtoMapper.INSTANCE.reviewEntityToMileageDto(reviewEntity, MileageType.REVIEW_IMAGE_ATTACH);
                mileageDtos.add(mileageDto);
            }
            // 특정 장소에 첫 리뷰 작성 포인트
            ReviewEntity firstReviewForPlace = reviewRepository.findTop1ByPlaceId(reviewEntity.getPlaceId(), Sort.by(Sort.Direction.ASC, "createdAt"));
            if (reviewEntity.getId().equals(firstReviewForPlace.getId())) {
                MileageDto mileageDto = MileageDtoMapper.INSTANCE.reviewEntityToMileageDto(reviewEntity, MileageType.REVIEW_PLACE_FIRST_WRITE);
                mileageDtos.add(mileageDto);
            }
        }

        return mileageDtos;
    }
}
