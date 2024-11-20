package com.study.carDealershipsServer.application.manager.useCase;

import com.study.carDealershipsServer.domain.vehicle.dto.CreateVehicleModelRequest;
import com.study.carDealershipsServer.domain.vehicle.dto.VehicleModelResource;

import java.util.List;

public interface VehicleModelManagerFacade {

    void createVehicleModel(CreateVehicleModelRequest request);

    VehicleModelResource getVehicleModel(String vehicleModelId);

    List<VehicleModelResource> getAllVehicleModels();

    void deleteVehicleModel(String vehicleModelId);
}
