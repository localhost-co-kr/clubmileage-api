package com.triple.clubmileage.api.repository;

import com.triple.clubmileage.api.repository.entity.MileageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface MileageRepository extends JpaRepository<MileageEntity, UUID> {

    @Query("SELECT SUM(m.mileage) FROM MileageEntity m")
    Long selectTotalMileage(UUID userId);
}
