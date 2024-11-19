package com.study.carDealershipsServer.application.manager.controller;


import com.study.carDealershipsServer.application.manager.useCase.VehicleManagerFacade;
import com.study.carDealershipsServer.domain.vehicle.dto.CreateVehicleRequest;
import com.study.carDealershipsServer.domain.vehicle.dto.VehicleResource;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.study.carDealershipsServer.common.Constants.*;

@RestController
@RequestMapping(MANAGER_PREFIX + VEHICLE_PREFIX)
@RequiredArgsConstructor
public class VehicleManagerController {

    private final VehicleManagerFacade vehicleManagerFacade;


    @PostMapping
    public ResponseEntity<Void> addVehicle(@RequestBody CreateVehicleRequest createVehicleRequest) {
        vehicleManagerFacade.createVehicle(createVehicleRequest);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<Page<VehicleResource>> getAllVehicles(Pageable pageable) {
        var vehicles = vehicleManagerFacade.getVehicles(pageable);
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/{vin}")
    public ResponseEntity<VehicleResource> getVehicle(@PathVariable String vin) {
        var vehicle = vehicleManagerFacade.getVehicle(vin);
        return ResponseEntity.ok(vehicle);
    }

    @DeleteMapping("/{vin}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable String vin) {
        vehicleManagerFacade.deleteVehicle(vin);
        return ResponseEntity.noContent().build();
    }
}
