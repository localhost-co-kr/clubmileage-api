package com.triple.clubmileage.api.dto.mapper;

import com.triple.clubmileage.api.dto.ReviewEventRequest;
import com.triple.clubmileage.api.repository.entity.ImageEntity;
import com.triple.clubmileage.api.repository.entity.ReviewEntity;

public abstract class ReviewEventRequestMapperDecorator implements ReviewEventRequestMapper {

    private final ReviewEventRequestMapper delegate;

    public ReviewEventRequestMapperDecorator(ReviewEventRequestMapper delegate) {
        this.delegate = delegate;
    }

    @Override
    public ReviewEntity reviewEventRequestToReviewEntity(ReviewEventRequest reviewEventRequest) {
        ReviewEntity reviewEntity = delegate.reviewEventRequestToReviewEntity(reviewEventRequest);

        reviewEventRequest.getAttachedPhotoIds()
                .stream().map(s -> {
                    ImageEntity imageEntity = new ImageEntity();
                    imageEntity.setId(s);
                    return imageEntity;
                }).forEach(reviewEntity::addImageEntity);

        return reviewEntity;
    }
}
