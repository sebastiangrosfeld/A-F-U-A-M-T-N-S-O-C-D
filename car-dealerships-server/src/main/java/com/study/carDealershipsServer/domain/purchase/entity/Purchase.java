package com.study.carDealershipsServer.domain.purchase.entity;

import com.study.carDealershipsServer.common.enums.PurchaseStatus;
import com.study.carDealershipsServer.domain.client.entity.Client;
import com.study.carDealershipsServer.domain.manager.entity.Manager;
import com.study.carDealershipsServer.domain.vehicle.entity.Vehicle;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Purchase {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID purchaseId;

    @Column(nullable = false)
    private LocalDateTime purchaseDate;

    @Column(nullable = false)
    private PurchaseStatus purchaseStatus;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Manager manager;

    @OneToOne
    private Vehicle vehicle;
}
