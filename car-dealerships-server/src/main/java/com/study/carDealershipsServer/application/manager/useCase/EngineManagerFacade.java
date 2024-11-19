package com.study.carDealershipsServer.application.manager.useCase;

import com.study.carDealershipsServer.domain.vehicle.dto.CreateEngineRequest;
import com.study.carDealershipsServer.domain.vehicle.dto.EditEngineRequest;
import com.study.carDealershipsServer.domain.vehicle.dto.EngineResource;

import java.util.List;

public interface EngineManagerFacade {

    void createEngine(CreateEngineRequest createEngineRequest);

    EngineResource getEngine(String engineName);

    List<EngineResource> getEngines();

    void editEngine(EditEngineRequest editEngineRequest);

    void deleteEngine(String engineName);
}
