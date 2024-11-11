package com.study.carDealershipsServer.domain.manager.repository;

import com.study.carDealershipsServer.domain.manager.entity.RentalOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalOfferRepository extends JpaRepository<RentalOffer, Long> {

    boolean existsByVehicleVinNumber(String vehicleVinNumber);
}
