package com.triple.clubmileage.api.repository;

import com.triple.clubmileage.api.repository.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    ReviewEntity findByUserIdAndPlaceId(UUID userId, UUID placeId);
}
