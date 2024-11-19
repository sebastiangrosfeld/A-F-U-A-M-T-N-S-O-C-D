package com.study.carDealershipsServer.application.client.controller;

import com.study.carDealershipsServer.application.client.useCase.PurchaseClientFacade;
import com.study.carDealershipsServer.domain.purchase.dto.PurchaseResource;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.study.carDealershipsServer.common.Constants.CLIENT_PREFIX;
import static com.study.carDealershipsServer.common.Constants.PURCHASES_PREFIX;

@RestController
@RequestMapping(CLIENT_PREFIX + PURCHASES_PREFIX)
@RequiredArgsConstructor
public class PurchaseClientController {

    private final PurchaseClientFacade purchaseClientFacade;

    @GetMapping
    public ResponseEntity<Page<PurchaseResource>> getAllPurchases(Pageable pageable) {
        var purchases = purchaseClientFacade.getClientPurchases(pageable);
        return ResponseEntity.ok(purchases);
    }
}
