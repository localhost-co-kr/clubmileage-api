package com.triple.clubmileage.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MileageDto {

    private long mileage;
    private long userId;
    private long placeId;
}
