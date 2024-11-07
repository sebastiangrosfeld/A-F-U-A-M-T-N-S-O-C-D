package com.study.carDealershipsServer.domain.vehicle.mapper;

import com.study.carDealershipsServer.domain.vehicle.dto.CreateVehicleRequest;
import com.study.carDealershipsServer.domain.vehicle.dto.VehicleResource;
import com.study.carDealershipsServer.domain.vehicle.entity.Engine;
import com.study.carDealershipsServer.domain.vehicle.entity.Vehicle;
import com.study.carDealershipsServer.domain.vehicle.entity.VehicleModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleMapper {

    private final EngineMapper engineMapper;
    private final VehicleModelMapper vehicleModelMapper;

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

    public VehicleResource entityToResource(Vehicle vehicle) {
        var engines = vehicle.getEngines().stream().map(engineMapper::entityToResource).toList();
        return VehicleResource.builder()
                .vinNumber(vehicle.getVinNumber())
                .type(vehicle.getType())
                .color(vehicle.getColor())
                .bodyLine(vehicle.getBodyLine())
                .yearOfProduction(vehicle.getYearOfProduction())
                .registrationDate(vehicle.getRegistrationDate())
                .vehicleModel(vehicleModelMapper.entityToResource(vehicle.getModel()))
                .engines(engines)
                .build();
    }
}
