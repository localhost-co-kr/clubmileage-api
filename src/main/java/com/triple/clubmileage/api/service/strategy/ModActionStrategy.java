package com.triple.clubmileage.api.service.strategy;

import com.triple.clubmileage.api.common.exception.ClubmilageApiException;
import com.triple.clubmileage.api.dto.MileageDto;
import com.triple.clubmileage.api.dto.ReviewEventRequest;
import com.triple.clubmileage.api.dto.mapper.MileageDtoMapper;
import com.triple.clubmileage.api.enumtype.MileageType;
import com.triple.clubmileage.api.enumtype.exception.ClubmileageExceptionCode;
import com.triple.clubmileage.api.repository.ReviewRepository;
import com.triple.clubmileage.api.repository.entity.ImageEntity;
import com.triple.clubmileage.api.repository.entity.ReviewEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Slf4j
@RequiredArgsConstructor
public class ModActionStrategy implements ReviewStrategy {

    private final ReviewRepository reviewRepository;

    @Override
    public List<MileageDto> reviewProcessing(ReviewEventRequest request) {
        ReviewEntity reviewEntity = reviewRepository.findById(request.getReviewId()).get();
        validation(request, reviewEntity);

        int imageCountBeforeModified = reviewEntity.getImageEntities().size();

        reviewEntity.setContent(request.getContent());
        reviewEntity.setUpdatedAt(LocalDateTime.now());
        reviewEntity.getImageEntities().stream()
                .filter(imageEntity -> !request.getAttachedPhotoIds().contains(imageEntity.getId()))
                .forEach(imageEntity -> imageEntity.setDeleted(true));

        request.getAttachedPhotoIds().stream()
                .filter(attachedPhotoId -> {
                    List<UUID> alreadyAttachedImages = reviewEntity.getImageEntities().stream()
                            .map(ImageEntity::getId)
                            .collect(Collectors.toList());
                    return !alreadyAttachedImages.contains(attachedPhotoId);
                }).forEach(attachedPhotoId -> {
                    ImageEntity newImageEntity = new ImageEntity();
                    newImageEntity.setId(attachedPhotoId);
                    reviewEntity.addImageEntity(newImageEntity);
                });

        reviewRepository.save(reviewEntity);

        return calculatorMileage(reviewEntity, imageCountBeforeModified);
    }

    private void validation(ReviewEventRequest request, ReviewEntity reviewEntity) {
        if (isEmpty(reviewEntity)) {
            log.error("존재하지않는 리뷰 수정 요청 userId = [{}], reviewId = [{}]", request.getUserId(), request.getReviewId());
            throw new ClubmilageApiException(ClubmileageExceptionCode.INVALID_REVIEW_ID);
        }
        if (!reviewEntity.getUserId().equals(request.getUserId())) {
            log.error("타인의 리뷰 수정 요청 userId = [{}], reviewId = [{}]", request.getUserId(), request.getReviewId());
            throw new ClubmilageApiException(ClubmileageExceptionCode.AUTHORIZATION_FAIL);
        }
    }

    private List<MileageDto> calculatorMileage(ReviewEntity reviewEntity, int imageCountBeforeModified) {
        List<MileageDto> mileageDtos = new ArrayList<>();

        int imageCountAfterModified = (int) reviewEntity.getImageEntities().stream()
                .filter(imageEntity -> !imageEntity.isDeleted())
                .count();

        // 리뷰 이미지 포인트 적립 및 회수
        if (imageCountBeforeModified > 0 && imageCountAfterModified == 0) {
            MileageDto mileageDto = MileageDtoMapper.INSTANCE.reviewEntityToMileageDto(reviewEntity, MileageType.REVIEW_IMAGE_DELETE);
            mileageDtos.add(mileageDto);
        } else if (imageCountBeforeModified == 0 && imageCountAfterModified > 0) {
            MileageDto mileageDto = MileageDtoMapper.INSTANCE.reviewEntityToMileageDto(reviewEntity, MileageType.REVIEW_IMAGE_ATTACH);
            mileageDtos.add(mileageDto);
        }

        return mileageDtos;
    }
}
