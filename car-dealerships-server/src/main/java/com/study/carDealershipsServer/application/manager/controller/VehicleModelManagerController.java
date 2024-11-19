package com.study.carDealershipsServer.application.manager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.study.carDealershipsServer.common.Constants.*;

@RestController
@RequestMapping(MANAGER_PREFIX + VEHICLE_MODEL_PREFIX)
@RequiredArgsConstructor
public class VehicleModelManagerController {
}
