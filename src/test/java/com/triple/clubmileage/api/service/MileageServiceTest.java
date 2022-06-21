package com.triple.clubmileage.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

import com.triple.clubmileage.api.dto.MileageDto;
import com.triple.clubmileage.api.dto.MileageRetrieveResponse;
import com.triple.clubmileage.api.dto.mapper.MileageDtoMapper;
import com.triple.clubmileage.api.enumtype.MileageType;
import com.triple.clubmileage.api.repository.MileageRepository;
import com.triple.clubmileage.api.repository.entity.MileageEntity;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
public class MileageServiceTest {

    private MileageService mileageService;
    @MockBean
    private MileageRepository mileageRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        mileageService = new MileageService(MileageDtoMapper.INSTANCE, mileageRepository);
    }

    @Test
    void 마일리지처리요청시_정상처리_결과를_반환한다() {
        MileageDto mileageDto = MileageDto.builder()
            .mileageType(MileageType.REVIEW_TEXT_WRITE)
            .reviewId(UUID.randomUUID())
            .userId(UUID.randomUUID())
            .placeId(UUID.randomUUID())
            .build();

        MileageEntity mileageEntity = MileageDtoMapper.INSTANCE.fromMileageDto(mileageDto);

        given(mileageRepository.saveAll(Arrays.asList(mileageEntity))).willReturn(Arrays.asList(mileageEntity));

        boolean isSuccess = mileageService.mileageProcessing(Collections.singletonList(mileageDto));
        assertTrue(isSuccess);
    }

    @Test
    void 마일리지조회시_조회된값을_반환한다() {
        UUID userId = UUID.randomUUID();
        Long totalMileage = 5L;

        given(mileageRepository.selectTotalMileage(userId)).willReturn(totalMileage);

        Mono<MileageRetrieveResponse> mileageRetrieveResponseMono = mileageService.retrieveMileage(userId);
        mileageRetrieveResponseMono
            .subscribe(mileageRetrieveResponse -> assertThat(mileageRetrieveResponse.getTotalMileage()).isEqualTo(totalMileage));
    }
}
