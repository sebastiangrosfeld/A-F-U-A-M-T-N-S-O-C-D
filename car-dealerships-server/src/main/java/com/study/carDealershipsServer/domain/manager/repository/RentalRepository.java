package com.study.carDealershipsServer.domain.manager.repository;

import com.study.carDealershipsServer.domain.manager.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    boolean existsByVehicleVinNumber(String vehicleVinNumber);

}
