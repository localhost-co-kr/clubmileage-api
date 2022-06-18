package com.triple.clubmileage.api.dto.mapper;

import com.triple.clubmileage.api.dto.ReviewEventRequest;
import com.triple.clubmileage.api.repository.entity.ReviewEntity;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
@DecoratedWith(ReviewEventRequestMapperDecorator.class)
public interface ReviewEventRequestMapper {

    ReviewEventRequestMapper INSTANCE = Mappers.getMapper(ReviewEventRequestMapper.class);

    ReviewEntity reviewEventRequestToReviewEntity(ReviewEventRequest reviewEventRequest);
}
