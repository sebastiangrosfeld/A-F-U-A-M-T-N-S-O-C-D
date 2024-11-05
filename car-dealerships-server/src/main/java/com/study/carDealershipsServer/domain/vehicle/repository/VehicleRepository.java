package com.study.carDealershipsServer.domain.vehicle.repository;

import com.study.carDealershipsServer.domain.vehicle.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository <Vehicle, Long> {

    boolean existsByVinNumber(String vin);
}
