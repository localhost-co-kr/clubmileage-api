package com.triple.clubmileage.api.controller;

import com.triple.clubmileage.api.dto.ReviewEventRequest;
import com.triple.clubmileage.api.enumtype.ActionType;
import com.triple.clubmileage.api.enumtype.EventType;
import com.triple.clubmileage.api.service.ReviewService;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;
import org.springframework.web.reactive.function.BodyInserters;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = ReviewRestController.class)
public class ReviewRestControllerTest {

    @MockBean
    private ReviewService reviewService;

    @Autowired
    private WebTestClient webClient;

    @Test
    void 리뷰작성시_유효한_요청이면_200_응답_반환한다() {
        ReviewEventRequest request = generateReviewEventRequest(EventType.REVIEW, ActionType.ADD, UUID.randomUUID(), "좋아요!",
            UUID.randomUUID(), UUID.randomUUID());

        ResponseSpec responseSpec = getExchange(request);
        responseSpec.expectStatus().isOk();
    }

    @Test
    void 리뷰작성시_유효하지않은_요청이면_400_응답을_반환한다() {
        ReviewEventRequest request = generateReviewEventRequest(EventType.REVIEW, ActionType.ADD, UUID.randomUUID(), null,
            UUID.randomUUID(), UUID.randomUUID());

        ResponseSpec responseSpec = getExchange(request);
        responseSpec.expectStatus().isBadRequest();
    }

    private ReviewEventRequest generateReviewEventRequest(EventType eventType, ActionType actionType, UUID reviewId, String content,
        UUID placeId, UUID userId) {
        return ReviewEventRequest.builder()
            .type(eventType)
            .action(actionType)
            .reviewId(reviewId)
            .content(content)
            .userId(userId)
            .placeId(placeId)
            .build();
    }

    private ResponseSpec getExchange(ReviewEventRequest reviewEventRequest) {
        return webClient.post()
            .uri("/events")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromObject(reviewEventRequest))
            .exchange();
    }
}
