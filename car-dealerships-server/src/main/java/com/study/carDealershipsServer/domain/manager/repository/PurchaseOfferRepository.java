package com.study.carDealershipsServer.domain.manager.repository;

import com.study.carDealershipsServer.domain.manager.entity.PurchaseOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOfferRepository extends JpaRepository <PurchaseOffer, Long> {

    boolean existsByVehicleVinNumber(String vehicleVinNumber);
}
