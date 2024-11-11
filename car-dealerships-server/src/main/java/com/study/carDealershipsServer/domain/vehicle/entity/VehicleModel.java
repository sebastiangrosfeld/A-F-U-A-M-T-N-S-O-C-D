package com.study.carDealershipsServer.domain.vehicle.entity;

import com.study.carDealershipsServer.common.enums.VehicleBrand;
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
public class VehicleModel {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private VehicleBrand vehicleBrand;

    @Column(nullable = false, unique = true)
    private String modelName;

    @Column(nullable = false)
    private Integer startProduction;

    @Column(nullable = false)
    private Integer endProduction;

}
