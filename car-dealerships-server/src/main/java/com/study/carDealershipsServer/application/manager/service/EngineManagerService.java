package com.study.carDealershipsServer.application.manager.service;

import com.study.carDealershipsServer.application.manager.useCase.EngineManagerFacade;
import com.study.carDealershipsServer.common.errors.ServiceException;
import com.study.carDealershipsServer.domain.vehicle.dto.CreateEngineRequest;
import com.study.carDealershipsServer.domain.vehicle.dto.EditEngineRequest;
import com.study.carDealershipsServer.domain.vehicle.dto.EngineResource;
import com.study.carDealershipsServer.domain.vehicle.mapper.EngineMapper;
import com.study.carDealershipsServer.domain.vehicle.repository.EngineRepository;
import com.study.carDealershipsServer.domain.vehicle.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EngineManagerService implements EngineManagerFacade {

    private final EngineRepository engineRepository;
    private final EngineMapper engineMapper;
    private final VehicleRepository vehicleRepository;

    @Override
    public void createEngine(CreateEngineRequest createEngineRequest) {
        if (engineRepository.existsByName(createEngineRequest.engineName())) {
            throw new ServiceException("Engine with provided name already exists", HttpStatus.BAD_REQUEST);
        }
        var engine = engineMapper.createRequestToEngine(createEngineRequest);
        engineRepository.save(engine);
    }

    @Override
    public EngineResource getEngine(String engineName) {
        var engine = engineRepository.findByName(engineName)
                .orElseThrow(() -> new ServiceException("Engine with provided name does not exist", HttpStatus.NOT_FOUND));
        return engineMapper.entityToResource(engine);
    }

    @Override
    public List<EngineResource> getEngines() {
        return engineRepository.findAll().stream().map(engineMapper::entityToResource).toList();
    }

    @Override
    public void editEngine(EditEngineRequest editEngineRequest) {
        if (editEngineRequest.newEngineName() != null && engineRepository.existsByName(editEngineRequest.newEngineName())) {
            throw new ServiceException("Engine with provided new name already exists", HttpStatus.BAD_REQUEST);
        }
        var engine = engineRepository.findByName(editEngineRequest.oldEngineName())
                .orElseThrow(() -> new ServiceException("Engine with provided name does not exist", HttpStatus.NOT_FOUND));
        engine.setName(editEngineRequest.newEngineName());
        engine.setHorsePower(editEngineRequest.horsePower());
        engine.setTorque(editEngineRequest.torque());
        engine.setTypeOfFuel(editEngineRequest.typeOfFuel());
        engine.setNumberOfCylinders(editEngineRequest.numberOfCylinders());
        engineRepository.save(engine);
    }

    @Override
    public void deleteEngine(String engineName) {
        var engine = engineRepository.findByName(engineName)
                .orElseThrow(() -> new ServiceException("Engine with provided name does not exist", HttpStatus.NOT_FOUND));

        if (vehicleRepository.existsByEnginesName(engine.getName())) {
            throw new ServiceException("Engine with provided name already using", HttpStatus.BAD_REQUEST);
        }
        engineRepository.delete(engine);
    }

}
