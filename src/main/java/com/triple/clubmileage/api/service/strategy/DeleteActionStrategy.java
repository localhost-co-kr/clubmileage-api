package com.triple.clubmileage.api.service.strategy;

import com.triple.clubmileage.api.common.exception.ClubmilageApiException;
import com.triple.clubmileage.api.dto.MileageDto;
import com.triple.clubmileage.api.dto.ReviewEventRequest;
import com.triple.clubmileage.api.dto.mapper.MileageDtoMapper;
import com.triple.clubmileage.api.enumtype.MileageType;
import com.triple.clubmileage.api.enumtype.exception.ClubmileageExceptionCode;
import com.triple.clubmileage.api.repository.ReviewRepository;
import com.triple.clubmileage.api.repository.entity.ReviewEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@Slf4j
@RequiredArgsConstructor
public class DeleteActionStrategy implements ReviewStrategy {

    private final ReviewRepository reviewRepository;

    @Override
    public List<MileageDto> reviewProcessing(ReviewEventRequest request) {
        ReviewEntity reviewEntity = reviewRepository.findById(request.getReviewId()).get();
        validation(request, reviewEntity);

        reviewEntity.setDeleted(true);
        reviewRepository.save(reviewEntity);

        return calculatorMileage(reviewEntity);
    }

    private void validation(ReviewEventRequest request, ReviewEntity reviewEntity) {
        if (isEmpty(reviewEntity)) {
            log.error("존재하지않는 리뷰 삭제 요청 userId = [{}], reviewId = [{}]", request.getUserId(), request.getReviewId());
            throw new ClubmilageApiException(ClubmileageExceptionCode.INVALID_REVIEW_ID);
        }
        if (!reviewEntity.getUserId().equals(request.getUserId())) {
            log.error("타인의 리뷰 삭제 요청 userId = [{}], reviewId = [{}]", request.getUserId(), request.getReviewId());
            throw new ClubmilageApiException(ClubmileageExceptionCode.AUTHORIZATION_FAIL);
        }
        if (reviewEntity.isDeleted()) {
            log.error("중복된 리뷰 삭제 요청 userId = [{}], reviewId = [{}]", request.getUserId(), request.getReviewId());
            throw new ClubmilageApiException(ClubmileageExceptionCode.DUPLICATE_REQUEST);
        }
    }

    private List<MileageDto> calculatorMileage(ReviewEntity reviewEntity) {
        List<MileageDto> mileageDtos = new ArrayList<>();

        // 리뷰 텍스트 포인트 회수
        if (reviewEntity.isDeleted()) {
            MileageDto mileageDto = MileageDtoMapper.INSTANCE.reviewEntityToMileageDto(reviewEntity, MileageType.REVIEW_TEXT_DELETE);
            mileageDtos.add(mileageDto);
        }
        // 리뷰 이미지 포인트 회수
        if (reviewEntity.getImageEntities().size() >= 1) {
            MileageDto mileageDto = MileageDtoMapper.INSTANCE.reviewEntityToMileageDto(reviewEntity, MileageType.REVIEW_IMAGE_DELETE);
            mileageDtos.add(mileageDto);
        }
        // 특정 장소에 첫 리뷰 작성 포인트 회수
        ReviewEntity firstReviewForPlace = reviewRepository.findTop1ByPlaceId(reviewEntity.getPlaceId(), Sort.by(Sort.Direction.ASC, "createdAt"));
        if (reviewEntity.getId().equals(firstReviewForPlace.getId())) {
            MileageDto mileageDto = MileageDtoMapper.INSTANCE.reviewEntityToMileageDto(reviewEntity, MileageType.REVIEW_PLACE_FIRST_DELETE);
            mileageDtos.add(mileageDto);
        }

        return mileageDtos;
    }
}
