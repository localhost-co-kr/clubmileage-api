package com.triple.clubmileage.api.service;

import com.triple.clubmileage.api.dto.MileageDto;
import com.triple.clubmileage.api.dto.mapper.MileageDtoMapper;
import com.triple.clubmileage.api.repository.MileageRepository;
import com.triple.clubmileage.api.repository.entity.MileageEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class MileageService {

    private final MileageDtoMapper mileageDtoMapper;
    private final MileageRepository mileageRepository;

    public String mileageProcessing(List<MileageDto> mileageDtos) {
        List<MileageEntity> mileageEntities = mileageDtos.stream()
                .map(mileageDtoMapper::fromMileageDto)
                .collect(Collectors.toList());

        mileageRepository.saveAll(mileageEntities);
        // TODO: response modeling
        return "ok";
    }
}
