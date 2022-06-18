package com.triple.clubmileage.api.repository;

import com.triple.clubmileage.api.repository.entity.ReviewEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<ReviewEntity, UUID> {

    ReviewEntity findByUserIdAndPlaceId(UUID userId, UUID placeId);

    ReviewEntity findTop1ByPlaceId(UUID placeId, Sort createdAt);
}
