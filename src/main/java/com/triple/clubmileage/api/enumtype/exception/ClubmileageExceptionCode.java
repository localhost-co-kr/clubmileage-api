package com.triple.clubmileage.api.enumtype.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClubmileageExceptionCode {

    INVALID_REVIEW_ID("요청한 리뷰정보를 확인 할 수 없습니다."),
    INVALID_PLACE_ID("요청한 장소정보를 확인 할 수 없습니다."),
    DUPLICATE_PLACE_REVIEW("이미 해당 장소에 리뷰를 작성하셨습니다."),
    DUPLICATE_REQUEST("중복된 요청 입니다."),
    AUTHORIZATION_FAIL("권한이 없습니다."),
    ALREADY_DELETED_REVIEW("삭제된 리뷰입니다.");

    public String message;
}
