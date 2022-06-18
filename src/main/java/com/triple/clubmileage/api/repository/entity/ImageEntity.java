package com.triple.clubmileage.api.repository.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Table(name = "IMAGE")
@Entity
public class ImageEntity {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "reviewEntity.id")
    private ReviewEntity reviewEntity;
    private boolean isDeleted;
}
