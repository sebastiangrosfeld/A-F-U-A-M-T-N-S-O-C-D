package com.study.carDealershipsServer.domain.manager.repository;

import com.study.carDealershipsServer.domain.manager.entity.PurchaseOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PurchaseOfferRepository extends JpaRepository <PurchaseOffer, Long> {

    boolean existsByVehicleVinNumber(String vehicleVinNumber);

    Optional<PurchaseOffer> findByVehicleVinNumber(String vehicleVinNumber);

    void deleteByVehicleVinNumber(String vehicleVinNumber);
}
