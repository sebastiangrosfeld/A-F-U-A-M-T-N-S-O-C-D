package com.study.carDealershipsServer.domain.manager.entity;

import com.study.carDealershipsServer.domain.vehicle.entity.Vehicle;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentalOffer {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private BigDecimal pricePerHour;

    @OneToOne
    private Vehicle vehicle;
}
