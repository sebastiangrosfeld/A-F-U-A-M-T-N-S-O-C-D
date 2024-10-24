package com.study.carDealershipsServer.domain.client;

import com.study.carDealershipsServer.common.VehicleBrand;
import com.study.carDealershipsServer.common.VehicleType;
import com.study.carDealershipsServer.domain.vehicle.VehicleModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleReference {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private VehicleType vehicleType;

    @OneToOne
    private VehicleModel vehicleModel;

    @Column
    private VehicleBrand vehicleBrand;

    @Column
    private String modelName;

    @Column
    private Integer minimalPower;

    @Column
    private Integer maximalPower;

    @Column
    private Integer minimalMileage;

    @Column
    private Integer maximalMileage;

    @Column
    private String color;

    @Column
    private String bodyLine;
}
