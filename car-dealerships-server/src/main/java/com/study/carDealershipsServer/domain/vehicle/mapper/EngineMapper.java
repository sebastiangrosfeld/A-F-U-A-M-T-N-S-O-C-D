package com.study.carDealershipsServer.domain.vehicle.mapper;

import com.study.carDealershipsServer.domain.vehicle.dto.CreateEngineRequest;
import com.study.carDealershipsServer.domain.vehicle.dto.EngineResource;
import com.study.carDealershipsServer.domain.vehicle.entity.Engine;
import org.springframework.stereotype.Service;

@Service
public class EngineMapper {

    public Engine createRequestToEngine(CreateEngineRequest engine) {
        return Engine.builder()
                .name(engine.engineName())
                .typeOfFuel(engine.typeOfFuel())
                .numberOfCylinders(engine.numberOfCylinders())
                .horsePower(engine.horsePower())
                .torque(engine.torque())
                .build();
    }

    public EngineResource entityToResource(Engine engine) {
        return EngineResource.builder()
                .name(engine.getName())
                .fuelType(engine.getTypeOfFuel())
                .numberOfCylinders(engine.getNumberOfCylinders())
                .horsePower(engine.getHorsePower())
                .torque(engine.getTorque())
                .build();
    }
}
