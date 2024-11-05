package com.study.carDealershipsServer.application.manager.useCase;

import com.study.carDealershipsServer.domain.vehicle.dto.CreateVehicleRequest;
import com.study.carDealershipsServer.domain.vehicle.entity.Vehicle;

public interface VehicleManagerInterface {

    void createVehicle(CreateVehicleRequest vehicle);
}
