package com.study.carDealershipsServer.domain.vehicle;

import com.study.carDealershipsServer.common.CarBrands;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String vinNumber;

    @Column(nullable = false)
    private boolean isCar;

    @Column
    private CarBrands carBrand;
}
