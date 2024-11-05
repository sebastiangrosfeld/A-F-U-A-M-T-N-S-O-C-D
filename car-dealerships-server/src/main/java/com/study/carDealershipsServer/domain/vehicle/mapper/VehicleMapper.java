package com.study.carDealershipsServer.domain.vehicle.mapper;

import com.study.carDealershipsServer.domain.vehicle.dto.CreateVehicleRequest;
import com.study.carDealershipsServer.domain.vehicle.entity.Engine;
import com.study.carDealershipsServer.domain.vehicle.entity.Vehicle;
import com.study.carDealershipsServer.domain.vehicle.entity.VehicleModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleMapper {

    public Vehicle createRequestToVehicle(CreateVehicleRequest createVehicleRequest, List<Engine> engines, VehicleModel vehicleModel) {
        return Vehicle.builder()
                .model(vehicleModel)
                .engines(engines)
                .vinNumber(createVehicleRequest.vinNumber())
                .type(createVehicleRequest.vehicleType())
                .color(createVehicleRequest.color())
                .bodyLine(createVehicleRequest.bodyLine())
                .yearOfProduction(createVehicleRequest.yearOfProduction())
                .registrationDate(createVehicleRequest.registrationDate())
                .build();
    }
}
