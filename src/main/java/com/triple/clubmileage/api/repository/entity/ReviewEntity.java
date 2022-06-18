package com.triple.clubmileage.api.repository.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Table(name = "REVIEW")
@Entity
public class ReviewEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    private String content;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "reviewEntity")
    @Setter(AccessLevel.NONE)
    private List<ImageEntity> imageEntities;
    @Column(columnDefinition = "BINARY(16)")
    private UUID userId;
    @Column(columnDefinition = "BINARY(16)")
    private UUID placeId;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public void addImageEntity(ImageEntity imageEntity) {
        if (imageEntities == null) {
            imageEntities = new ArrayList<>();
        }
        imageEntity.setReviewEntity(this);
        imageEntities.add(imageEntity);
    }
}
