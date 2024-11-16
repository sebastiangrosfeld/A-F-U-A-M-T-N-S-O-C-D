package com.study.carDealershipsServer.application.manager.controller;

import com.study.carDealershipsServer.application.manager.useCase.PurchaseOfferManagerInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.study.carDealershipsServer.common.Constants.MANAGER_PREFIX;
import static com.study.carDealershipsServer.common.Constants.PURCHASES_OFFER_PREFIX;

@RestController
@RequestMapping(MANAGER_PREFIX + PURCHASES_OFFER_PREFIX)
@RequiredArgsConstructor
public class PurchaseOfferManagerController {

    private final PurchaseOfferManagerInterface purchaseOfferManager;
}
