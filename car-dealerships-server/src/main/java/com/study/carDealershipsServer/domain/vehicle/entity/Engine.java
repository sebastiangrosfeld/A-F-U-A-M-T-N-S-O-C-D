package com.study.carDealershipsServer.domain.vehicle.entity;

import com.study.carDealershipsServer.common.enums.FuelType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Engine {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private FuelType typeOfFuel;

    @Column(nullable = false)
    private Integer numberOfCylinders;

    @Column(nullable = false)
    private Integer horsePower;

    @Column(nullable = false)
    private Integer torque;

}
