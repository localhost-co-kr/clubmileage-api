package com.triple.clubmileage.api.dto;

import com.triple.clubmileage.api.enumtype.EventAction;
import com.triple.clubmileage.api.enumtype.EventType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@Getter
@Setter
public class ReviewEventRequest {

    @NotNull(message = "type 값이 올바르지 않습니다.")
    private EventType type;
    @NotNull(message = "action 값이 올바르지 않습니다.")
    private EventAction action;
    private String reviewId;
    @NotBlank(message = "리뷰 내용은 빈값 일 수 없습니다.")
    private String content;
    private List<String> attachedPhotoIds;
    @NotBlank(message = "작성자는 빈값 일 수 없습니다.")
    @Length(min = 36, max = 36, message = "작성자 값의 형식이 올바르지 않습니다.")
    private String userId;
    @NotBlank(message = "장소는 빈값 일 수 없습니다.")
    @Length(min = 36, max = 36, message = "장소 값의 형식이 올바르지 않습니다.")
    private String placeId;

    @AssertTrue(message = "리뷰 수정&삭제 시 리뷰ID 값이 빈값 일 수 없습니다.")
    public boolean isExistReviewId() {
        if (!EventAction.ADD.equals(action)) {
            return !isEmpty(reviewId);
        }
        return true;
    }
}
