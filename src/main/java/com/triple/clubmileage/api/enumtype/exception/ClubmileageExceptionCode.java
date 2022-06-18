package com.triple.clubmileage.api.enumtype.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClubmileageExceptionCode {

    INVALID_PLACE_ID("잘못된 장소 입니다."),
    DUPLICATE_PLACE_REVIEW("이미 해당 장소에 리뷰를 작성하셨습니다.");

    public String message;
}
