package com.study.carDealershipsServer.application.manager.controller;

import com.study.carDealershipsServer.application.manager.useCase.PurchaseOfferManagerInterface;
import com.study.carDealershipsServer.domain.manager.dto.CreatePurchaseOfferRequest;
import com.study.carDealershipsServer.domain.manager.dto.PurchaseOfferResource;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.study.carDealershipsServer.application.authentication.service.AuthService.getAuthUserId;
import static com.study.carDealershipsServer.common.Constants.MANAGER_PREFIX;
import static com.study.carDealershipsServer.common.Constants.PURCHASES_OFFER_PREFIX;

@RestController
@RequestMapping(MANAGER_PREFIX + PURCHASES_OFFER_PREFIX)
@RequiredArgsConstructor
public class PurchaseOfferManagerController {

    private final PurchaseOfferManagerInterface purchaseOfferManager;

    @PostMapping
    public ResponseEntity<Void> createPurchaseOffer(CreatePurchaseOfferRequest createPurchaseOfferRequest) {
        var managerId = getAuthUserId();
        purchaseOfferManager.createPurchaseOffer(createPurchaseOfferRequest, managerId);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{vin}")
    public ResponseEntity<PurchaseOfferResource> getPurchaseOffer(@PathVariable String vin) {
        var purchaseOffer = purchaseOfferManager.getPurchaseOffer(vin);
        return ResponseEntity.ok(purchaseOffer);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<PurchaseOfferResource>> getAllPurchaseOffers(Pageable pageable) {
        var purchaseOffers = purchaseOfferManager.getPurchaseOffers(pageable);
        return ResponseEntity.ok(purchaseOffers);
    }

    @DeleteMapping("/{vin}")
    public ResponseEntity<Void> deletePurchaseOffer(@PathVariable String vin) {
        purchaseOfferManager.deletePurchaseOffer(vin);
        return ResponseEntity.noContent().build();
    }
}
