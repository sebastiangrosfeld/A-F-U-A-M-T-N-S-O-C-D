package com.study.carDealershipsServer.domain.manager.entity;

import com.study.carDealershipsServer.common.enums.RentalStatus;
import com.study.carDealershipsServer.domain.client.entity.Client;
import com.study.carDealershipsServer.domain.vehicle.entity.Vehicle;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rental {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private RentalStatus status;

    @Column
    private LocalDateTime startTime;

    @Column
    private LocalDateTime endTime;

    @Column
    private LocalDateTime bookedStartTime;

    @Column
    private LocalDateTime bookedEndTime;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Manager manager;

    @OneToOne
    private Vehicle vehicle;
}
