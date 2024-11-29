package com.study.carDealershipsServer.domain.vehicle.entity;

import com.study.carDealershipsServer.common.enums.VehicleType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String vinNumber;

    @Column(nullable = false)
    private VehicleType type;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String bodyLine;

    @Column(nullable = false)
    private Integer yearOfProduction;

    @Column(nullable = false)
    private Date registrationDate;

    @OneToOne
    private VehicleModel model;

    @OneToMany
    private List<Engine> engines;

}
