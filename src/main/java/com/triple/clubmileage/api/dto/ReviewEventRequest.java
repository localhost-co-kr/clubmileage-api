package com.triple.clubmileage.api.dto;

import static org.springframework.util.ObjectUtils.isEmpty;

import com.triple.clubmileage.api.enumtype.ActionType;
import com.triple.clubmileage.api.enumtype.EventType;
import java.util.List;
import java.util.UUID;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewEventRequest {

    @NotNull(message = "type 값이 올바르지 않습니다.")
    private EventType type;
    @NotNull(message = "action 값이 올바르지 않습니다.")
    private ActionType action;
    private UUID reviewId;
    private String content;
    private List<UUID> attachedPhotoIds;
    @NotNull(message = "작성자는 빈값 일 수 없습니다.")
    private UUID userId;
    @NotNull(message = "장소는 빈값 일 수 없습니다.")
    private UUID placeId;

    @AssertTrue(message = "리뷰 수정&삭제 시 리뷰ID 값이 빈값 일 수 없습니다.")
    private boolean isExistReviewId() {
        if (ActionType.MOD.equals(action) || ActionType.DELETE.equals(action)) {
            return !isEmpty(reviewId);
        }
        return true;
    }

    @AssertTrue(message = "리뷰 작성&수정 시 리뷰 내용은 빈값 일 수 없습니다.")
    private boolean isValidContent() {
        if (ActionType.ADD.equals(action) || ActionType.MOD.equals(action)) {
            return !isEmpty(content);
        }
        return true;
    }
}
