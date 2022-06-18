package com.triple.clubmileage.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor(staticName = "of")
public class MileageRetrieveResponse {

    private UUID userId;
    private Long totalMileage;
}
