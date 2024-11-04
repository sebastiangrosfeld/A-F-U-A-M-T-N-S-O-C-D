package com.study.carDealershipsServer.application.client.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.study.carDealershipsServer.common.Constants.MANAGER_PREFIX;
import static com.study.carDealershipsServer.common.Constants.VEHICLE_PREFIX;

@RestController
@RequestMapping(MANAGER_PREFIX + VEHICLE_PREFIX)
@RequiredArgsConstructor
public class VehicleClientController {

}
