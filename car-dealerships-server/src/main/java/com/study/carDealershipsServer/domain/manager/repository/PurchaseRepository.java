package com.study.carDealershipsServer.domain.manager.repository;

import com.study.carDealershipsServer.domain.manager.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    boolean existsByVehicleVinNumber(String vehicleVinNumber);
}
