package com.study.carDealershipsServer.application.manager.controller;

import com.study.carDealershipsServer.application.manager.useCase.PurchaseManagerInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.study.carDealershipsServer.common.Constants.*;

@RestController
@RequestMapping(MANAGER_PREFIX + PURCHASES_PREFIX)
@RequiredArgsConstructor
public class PurchaseManagerController {

    private final PurchaseManagerInterface purchaseManagerInterface;
}
