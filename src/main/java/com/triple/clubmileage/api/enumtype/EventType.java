package com.triple.clubmileage.api.enumtype;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EventType implements CommonCode {

    REVIEW("리뷰");

    private String description;
}
