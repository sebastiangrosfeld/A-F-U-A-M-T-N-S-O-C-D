package com.study.carDealershipsServer.domain.manager.repository;

import com.study.carDealershipsServer.domain.manager.entity.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    boolean existsByVehicleVinNumber(String vehicleVinNumber);

    boolean existsByPurchaseId(UUID purchaseId);

    Page<Purchase> findAllByClientId(UUID clientId, Pageable pageable);

    Optional<Purchase> findByPurchaseId(UUID purchaseId);

    Optional<Purchase> findByVehicleVinNumber(String vehicleVinNumber);

    void deleteByPurchaseId(UUID purchaseId);
}
