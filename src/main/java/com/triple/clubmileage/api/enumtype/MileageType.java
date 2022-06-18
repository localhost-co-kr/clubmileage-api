package com.triple.clubmileage.api.enumtype;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MileageType implements CommonCode {

    REVIEW_TEXT_WRITE("리뷰 작성", 1),
    REVIEW_TEXT_DELETE("리뷰 삭제", -1),
    REVIEW_IMAGE_ATTACH("리뷰 이미지 첨부", 1),
    REVIEW_IMAGE_DELETE("리뷰 이미지 삭제", -1),
    REVIEW_PLACE_FIRST_WRITE("장소 첫 리뷰 작성", 1),
    REVIEW_PLACE_FIRST_DELETE("장소 첫 리뷰 삭제", -1);

    private String description;
    private int mileage;

}
