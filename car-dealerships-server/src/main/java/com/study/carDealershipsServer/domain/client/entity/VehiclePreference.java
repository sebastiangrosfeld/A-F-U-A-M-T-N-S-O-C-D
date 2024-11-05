package com.study.carDealershipsServer.domain.client.entity;

import com.study.carDealershipsServer.common.VehicleBrand;
import com.study.carDealershipsServer.common.VehicleType;
import com.study.carDealershipsServer.domain.vehicle.entity.VehicleModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehiclePreference {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne
    private Client client;

    @Column
    private VehicleType vehicleType;

    @OneToOne
    private VehicleModel vehicleModel;

    @Column
    private VehicleBrand vehicleBrand;

    @Column
    private Integer minimalPower;

    @Column
    private Integer maximalPower;

    @Column
    private Integer minimalMileage;

    @Column
    private Integer maximalMileage;

    @Column
    private Integer startProduction;

    @Column
    private Integer endProduction;

    @Column
    private String color;

    @Column
    private String bodyLine;
}
