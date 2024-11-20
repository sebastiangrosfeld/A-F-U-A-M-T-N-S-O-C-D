package com.study.carDealershipsServer.application.manager.service;

import com.study.carDealershipsServer.application.manager.useCase.VehicleModelManagerFacade;
import com.study.carDealershipsServer.domain.vehicle.dto.CreateVehicleModelRequest;
import com.study.carDealershipsServer.domain.vehicle.dto.VehicleModelResource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleModelManagerService implements VehicleModelManagerFacade {
    @Override
    public void createVehicleModel(CreateVehicleModelRequest request) {

    }

    @Override
    public VehicleModelResource getVehicleModel(String vehicleModelId) {
        return null;
    }

    @Override
    public List<VehicleModelResource> getAllVehicleModels() {
        return List.of();
    }

    @Override
    public void deleteVehicleModel(String vehicleModelId) {

    }
}
