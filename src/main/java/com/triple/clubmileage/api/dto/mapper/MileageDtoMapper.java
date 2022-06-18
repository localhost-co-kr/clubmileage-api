package com.triple.clubmileage.api.dto.mapper;

import com.triple.clubmileage.api.dto.MileageDto;
import com.triple.clubmileage.api.enumtype.MileageType;
import com.triple.clubmileage.api.repository.entity.MileageEntity;
import com.triple.clubmileage.api.repository.entity.ReviewEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MileageDtoMapper {

    MileageDtoMapper INSTANCE = Mappers.getMapper(MileageDtoMapper.class);

    @Mapping(target = "mileage", expression = "java(mileageDto.getMileageType().getMileage())")
    MileageEntity fromMileageDto(MileageDto mileageDto);

    @Mapping(source = "reviewEntity.id", target = "reviewId")
    MileageDto reviewEntityToMileageDto(ReviewEntity reviewEntity, MileageType mileageType);
}
