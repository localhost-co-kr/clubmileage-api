package com.triple.clubmileage.api.repository;

import com.triple.clubmileage.api.repository.entity.PlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlaceRepository extends JpaRepository<PlaceEntity, UUID> {
}
