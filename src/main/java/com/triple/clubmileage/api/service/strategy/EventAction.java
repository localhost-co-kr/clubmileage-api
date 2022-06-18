package com.triple.clubmileage.api.service.strategy;

import com.triple.clubmileage.api.dto.MileageDto;
import com.triple.clubmileage.api.dto.ReviewEventRequest;
import com.triple.clubmileage.api.service.MileageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@AllArgsConstructor
public class EventAction {

    private MileageService mileageService;
    private ReviewStrategy reviewStrategy;

    public String processing(ReviewEventRequest request) {
        List<MileageDto> mileageDtos = reviewStrategy.reviewProcessing(request);

        // 마일리지 처리 비동기 요청.
        // MSA 환경으로 통합 마일리지 시스템이 구축되어 있을거라는 가정으로
        // 비동기로 요청만 하고 리뷰 작성&수정에 집중하고 사용자에게 빠른 응답을 주고자 함
        CompletableFuture.supplyAsync(() -> mileageService.mileageProcessing(mileageDtos))
                .thenAccept(response -> log.info("response = [{}]", response));

        // TODO: response modeling
        return "ok";
    }
}
