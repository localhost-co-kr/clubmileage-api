package com.triple.clubmileage.api.repository.entity;

import com.triple.clubmileage.api.enumtype.MileageType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Table(name = "MILEAGE")
@Entity
public class MileageEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "UUID")
    private UUID id;
    @Enumerated(EnumType.STRING)
    @Column(name = "mileageType")
    private MileageType mileageType;
    @Column(name = "mileage")
    private long mileage;
    @Column(name = "userId", columnDefinition = "UUID")
    private UUID userId;
    @CreationTimestamp
    @Column(name = "createdAt")
    private LocalDateTime createdAt;

}
