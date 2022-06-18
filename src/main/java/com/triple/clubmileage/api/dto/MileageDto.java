package com.triple.clubmileage.api.dto;

import com.triple.clubmileage.api.enumtype.MileageType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class MileageDto {

    private MileageType mileageType;
    private UUID reviewId;
    private UUID userId;
    private UUID placeId;
}
