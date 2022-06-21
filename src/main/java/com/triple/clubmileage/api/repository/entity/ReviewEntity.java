package com.triple.clubmileage.api.repository.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

@Getter
@Setter
@Table(name = "REVIEW")
@Entity
public class ReviewEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "UUID")
    private UUID id;
    @Column(name = "content", length = 200)
    private String content;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "reviewEntity")
    @Setter(AccessLevel.NONE)
    @Where(clause = "isDeleted = 0")
    private List<ImageEntity> imageEntities;
    @Column(name = "userId", columnDefinition = "UUID")
    private UUID userId;
    @Column(name = "placeId", columnDefinition = "UUID")
    private UUID placeId;
    @Column(name = "isDeleted")
    private boolean isDeleted;
    @CreationTimestamp
    @Column(name = "createdAt")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    public void addImageEntity(ImageEntity imageEntity) {
        if (imageEntities == null) {
            imageEntities = new ArrayList<>();
        }
        imageEntity.setReviewEntity(this);
        imageEntities.add(imageEntity);
    }
}
