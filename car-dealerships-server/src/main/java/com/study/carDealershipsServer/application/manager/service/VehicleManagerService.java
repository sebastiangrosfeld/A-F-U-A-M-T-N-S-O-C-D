package com.study.carDealershipsServer.application.manager.service;

import com.study.carDealershipsServer.application.manager.useCase.VehicleManagerInterface;
import com.study.carDealershipsServer.common.errors.ServiceException;
import com.study.carDealershipsServer.domain.vehicle.dto.CreateEngineRequest;
import com.study.carDealershipsServer.domain.vehicle.dto.CreateVehicleRequest;
import com.study.carDealershipsServer.domain.vehicle.dto.VehicleResource;
import com.study.carDealershipsServer.domain.vehicle.entity.Engine;
import com.study.carDealershipsServer.domain.vehicle.entity.VehicleModel;
import com.study.carDealershipsServer.domain.vehicle.mapper.EngineMapper;
import com.study.carDealershipsServer.domain.vehicle.mapper.VehicleMapper;
import com.study.carDealershipsServer.domain.vehicle.repository.EngineRepository;
import com.study.carDealershipsServer.domain.vehicle.repository.VehicleModelRepository;
import com.study.carDealershipsServer.domain.vehicle.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class VehicleManagerService implements VehicleManagerInterface {

    private final VehicleRepository vehicleRepository;
    private final EngineRepository engineRepository;
    private final VehicleModelRepository vehicleModelRepository;
    private final EngineMapper engineMapper;
    private final VehicleMapper vehicleMapper;

    @Override
    public void createVehicle(CreateVehicleRequest vehicle) {
        validateVehicle(vehicle);
        List<Engine> engines = engineRepository.findAllByNameIn(getEnginesNames(vehicle));
        VehicleModel model = vehicleModelRepository.findVehicleModelByModelName(vehicle.modelName())
                .orElseThrow();
        var vehicleEntity = vehicleMapper.createRequestToVehicle(vehicle, engines, model);
        vehicleRepository.save(vehicleEntity);
    }

    @Override
    public Page<VehicleResource> getVehicles(Pageable pageable) {
        var vehicles = vehicleRepository.findAll(pageable);
        return vehicles.map(vehicleMapper::entityToResource);
    }

    @Override
    public VehicleResource getVehicle(String vin) {
        var vehicle = vehicleRepository.findByVinNumber(vin)
                .orElseThrow(() -> new ServiceException("Vehicle with provided vin not exists.", NOT_FOUND));
        return vehicleMapper.entityToResource(vehicle);
    }

    @Override
    public void deleteVehicle(String vin) {
        if (!vehicleRepository.existsByVinNumber(vin)) {
            throw new ServiceException("Vehicle with provided vin not exists.", NOT_FOUND);
        }
        // TO DO add validation of existing purchases and rentals
        vehicleRepository.deleteByVinNumber(vin);
    }

    private void validateVehicle(CreateVehicleRequest vehicle) {
        if (vehicleRepository.existsByVinNumber(vehicle.vinNumber())) {
            throw new ServiceException("Vehicle with that VIN number already exists", BAD_REQUEST);
        }
        if (!vehicleModelRepository.existsVehicleModelByModelName(vehicle.modelName())) {
            throw new ServiceException("Vehicle model with provided model not exists", BAD_REQUEST);
        }
        if (vehicle.engines().isEmpty() && vehicle.newEngines().isEmpty()) {
            throw new ServiceException("Vehicle must have at least one engine", BAD_REQUEST);
        }
        if (vehicle.engines().size() + vehicle.newEngines().size() > 3) {
            throw new ServiceException("Vehicle must have at most three engines", BAD_REQUEST);
        }
        vehicle.engines().forEach(engine -> {
            if (!engineRepository.existsByName(engine)) {
                throw new ServiceException("Engine with provided name not exists", BAD_REQUEST);
            }
        });
        validateAndSaveNewEngines(vehicle.newEngines());
    }

    private void validateAndSaveNewEngines(List<CreateEngineRequest> engines) {
        engines.forEach(engine -> {
            if (engineRepository.existsByName(engine.engineName())) {
                throw new ServiceException("Engine with provided name already exists", BAD_REQUEST);
            }
            engineRepository.save(engineMapper.createRequestToEngine(engine));
        });
    }

    private List<String> getEnginesNames(CreateVehicleRequest vehicle) {
        var newNames = vehicle.newEngines().stream().map(CreateEngineRequest::engineName).toList();
        var names = new ArrayList<>(newNames);
        names.addAll(vehicle.engines());
        return names;
    }

}
