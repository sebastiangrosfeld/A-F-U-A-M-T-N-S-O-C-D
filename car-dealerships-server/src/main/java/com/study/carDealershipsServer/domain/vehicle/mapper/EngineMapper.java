package com.study.carDealershipsServer.domain.vehicle.mapper;

import com.study.carDealershipsServer.domain.vehicle.dto.CreateEngineRequest;
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
}
