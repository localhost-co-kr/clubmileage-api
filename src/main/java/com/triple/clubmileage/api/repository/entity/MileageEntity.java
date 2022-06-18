package com.triple.clubmileage.api.repository.entity;

import com.triple.clubmileage.api.enumtype.MileageType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Table(name = "MILEAGE")
@Entity
public class MileageEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Enumerated(EnumType.STRING)
    private MileageType mileageType;

    private long mileage;

    @Column(columnDefinition = "BINARY(16)")
    private UUID userId;
}
