package com.study.carDealershipsServer.domain.vehicle.repository;

import com.study.carDealershipsServer.domain.vehicle.entity.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleModelRepository extends JpaRepository<VehicleModel, Long> {

    boolean existsVehicleModelByModelName(String modelName);

    Optional<VehicleModel> findVehicleModelByModelName(String ModelName);
}
