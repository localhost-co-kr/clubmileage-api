package com.triple.clubmileage.api.service;

import static com.triple.clubmileage.api.enumtype.exception.ClubmileageExceptionCode.INVALID_PLACE_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import com.triple.clubmileage.api.common.exception.ClubmilageApiException;
import com.triple.clubmileage.api.dto.ReviewEventRequest;
import com.triple.clubmileage.api.enumtype.ActionType;
import com.triple.clubmileage.api.enumtype.EventType;
import com.triple.clubmileage.api.repository.PlaceRepository;
import com.triple.clubmileage.api.repository.ReviewRepository;
import com.triple.clubmileage.api.repository.entity.PlaceEntity;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ReviewServiceTest {

    private ReviewService reviewService;
    @MockBean
    private MileageService mileageService;
    @MockBean
    private ReviewRepository reviewRepository;
    @MockBean
    private PlaceRepository placeRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        reviewService = new ReviewService(mileageService, reviewRepository, placeRepository);
    }

    @Test
    void 리뷰작성시_유효한_요청이면_정상처리를_반환한다() {
        ReviewEventRequest request = generateReviewWriteRequest();

        PlaceEntity placeEntity = generatePlaceEntity(request.getPlaceId());

        given(placeRepository.findById(request.getPlaceId())).willReturn(Optional.of(placeEntity));

        String response = reviewService.events(request);
        assertEquals(response, "ok");
    }

    @Test
    void 리뷰작성시_장소정보가_올바르지않으면_예외가_발생한다() {
        ReviewEventRequest request = generateReviewWriteRequest();

        given(placeRepository.findById(request.getPlaceId())).willReturn(Optional.empty());

        ClubmilageApiException exception = assertThrows(ClubmilageApiException.class, () -> {
            reviewService.events(request);
        });

        assertEquals(exception.exceptionCode, INVALID_PLACE_ID);
    }

    private ReviewEventRequest generateReviewWriteRequest() {
        return ReviewEventRequest.builder()
            .type(EventType.REVIEW)
            .action(ActionType.ADD)
            .reviewId(UUID.randomUUID())
            .content("좋아요!")
            .userId(UUID.randomUUID())
            .placeId(UUID.randomUUID())
            .build();
    }

    private PlaceEntity generatePlaceEntity(UUID placeId) {
        PlaceEntity placeEntity = new PlaceEntity();
        placeEntity.setId(placeId);
        placeEntity.setName("동문 재래 시장");
        placeEntity.setIntroduction("먹거리와 생활용품을 판매하는, 제주에서 가장 큰 시장");
        placeEntity.setCreatedAt(LocalDateTime.now());
        placeEntity.setUpdatedAt(LocalDateTime.now());
        return placeEntity;
    }
}
