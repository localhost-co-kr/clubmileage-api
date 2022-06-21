package com.triple.clubmileage.api.service;

import com.triple.clubmileage.api.dto.MileageDto;
import com.triple.clubmileage.api.dto.MileageRetrieveResponse;
import com.triple.clubmileage.api.dto.mapper.MileageDtoMapper;
import com.triple.clubmileage.api.repository.MileageRepository;
import com.triple.clubmileage.api.repository.entity.MileageEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class MileageService {

    private final MileageDtoMapper mileageDtoMapper;
    private final MileageRepository mileageRepository;

    public boolean mileageProcessing(List<MileageDto> mileageDtos) {
        List<MileageEntity> mileageEntities = mileageDtos.stream()
                .map(mileageDtoMapper::fromMileageDto)
                .collect(Collectors.toList());

        mileageRepository.saveAll(mileageEntities);
        return true;
    }

    public Mono<MileageRetrieveResponse> retrieveMileage(UUID userId) {
        Long totalMileage = mileageRepository.selectTotalMileage(userId);
        return Mono.just(MileageRetrieveResponse.of(userId, totalMileage));
    }
}
