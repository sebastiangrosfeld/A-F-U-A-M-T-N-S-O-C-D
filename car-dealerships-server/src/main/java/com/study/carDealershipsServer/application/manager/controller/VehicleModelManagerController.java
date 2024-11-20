package com.study.carDealershipsServer.application.manager.controller;

import com.study.carDealershipsServer.application.manager.useCase.VehicleModelManagerFacade;
import com.study.carDealershipsServer.domain.vehicle.dto.CreateVehicleModelRequest;
import com.study.carDealershipsServer.domain.vehicle.dto.VehicleModelResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.study.carDealershipsServer.common.Constants.*;

@RestController
@RequestMapping(MANAGER_PREFIX + VEHICLE_MODEL_PREFIX)
@RequiredArgsConstructor
public class VehicleModelManagerController {

    private final VehicleModelManagerFacade vehicleModelManagerFacade;

    @PostMapping
    public ResponseEntity<Void> createVehicleModel(CreateVehicleModelRequest request) {
        vehicleModelManagerFacade.createVehicleModel(request);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<VehicleModelResource>> getAllVehicleModels() {
        return ResponseEntity.ok(vehicleModelManagerFacade.getAllVehicleModels());
    }

    @GetMapping("/{name}")
    public ResponseEntity<VehicleModelResource> getVehicleModel(@PathVariable String name) {
        return ResponseEntity.ok(vehicleModelManagerFacade.getVehicleModel(name));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteVehicleModel(@PathVariable String name) {
        vehicleModelManagerFacade.deleteVehicleModel(name);
        return ResponseEntity.noContent().build();
    }
}
