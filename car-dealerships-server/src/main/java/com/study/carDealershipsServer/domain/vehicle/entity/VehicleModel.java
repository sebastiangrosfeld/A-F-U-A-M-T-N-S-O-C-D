package com.study.carDealershipsServer.domain.vehicle.entity;

import com.study.carDealershipsServer.common.VehicleBrand;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleModel {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private VehicleBrand vehicleBrand;

    @Column(nullable = false, unique = true)
    private String modelName;

    @Column(nullable = false)
    private Date startProduction;

    @Column(nullable = false)
    private Date endProduction;

}
