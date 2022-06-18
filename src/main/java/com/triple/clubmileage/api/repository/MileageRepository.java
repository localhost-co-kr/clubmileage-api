package com.triple.clubmileage.api.repository;

import com.triple.clubmileage.api.repository.entity.MileageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MileageRepository extends JpaRepository<MileageEntity, UUID> {
}
