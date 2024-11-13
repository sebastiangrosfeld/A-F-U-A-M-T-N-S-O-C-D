package com.study.carDealershipsServer.domain.vehicle.repository;

import com.study.carDealershipsServer.domain.vehicle.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository <Vehicle, Long> {

    boolean existsByVinNumber(String vin);

    Optional<Vehicle> findByVinNumber(String vin);

    void deleteByVinNumber(String vin);

    Long getIdByVinNumber(String vin);
}
