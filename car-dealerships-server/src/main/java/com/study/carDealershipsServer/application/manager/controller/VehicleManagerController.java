package com.study.carDealershipsServer.application.manager.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.study.carDealershipsServer.common.Constants.*;

@RestController
@RequestMapping(MANAGER_PREFIX + VEHICLE_PREFIX)
@RequiredArgsConstructor
public class VehicleManagerController {


    @PostMapping
    public ResponseEntity<Void> addVehicle()  {
        return ResponseEntity.ok().build();
    }
}
