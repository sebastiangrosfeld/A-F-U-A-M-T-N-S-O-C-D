package com.study.carDealershipsServer.application.manager.useCase;

import com.study.carDealershipsServer.domain.vehicle.dto.CreateVehicleRequest;
import com.study.carDealershipsServer.domain.vehicle.dto.VehicleResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VehicleManagerInterface {

    void createVehicle(CreateVehicleRequest vehicle);

    Page<VehicleResource> getVehicles(Pageable pageable);

    VehicleResource getVehicle(String vin);

    void deleteVehicle(String vin);
}
