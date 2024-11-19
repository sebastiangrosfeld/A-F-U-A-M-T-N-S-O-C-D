package com.study.carDealershipsServer.application.manager.controller;

import com.study.carDealershipsServer.application.manager.useCase.PurchaseManagerFacade;
import com.study.carDealershipsServer.domain.purchase.dto.CreatePurchaseRequest;
import com.study.carDealershipsServer.domain.purchase.dto.EditPurchaseStatusRequest;
import com.study.carDealershipsServer.domain.purchase.dto.PurchaseResource;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.study.carDealershipsServer.common.Constants.*;

@RestController
@RequestMapping(MANAGER_PREFIX + PURCHASES_PREFIX)
@RequiredArgsConstructor
public class PurchaseManagerController {

    private final PurchaseManagerFacade purchaseManagerFacade;

    @PostMapping
    public ResponseEntity<Void> createPurchase(@RequestBody CreatePurchaseRequest createPurchaseRequest) {
        purchaseManagerFacade.createPurchase(createPurchaseRequest);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/all")
    public ResponseEntity<Page<PurchaseResource>> getPurchases(Pageable pageable) {
        return ResponseEntity.ok(purchaseManagerFacade.getPurchases(pageable));
    }

    @GetMapping("/{clientId}/all")
    public ResponseEntity<Page<PurchaseResource>> getPurchases(@PathVariable UUID clientId, Pageable pageable) {
        return ResponseEntity.ok(purchaseManagerFacade.getClientPurchases(pageable, clientId));
    }

    @GetMapping("/{purchaseId}")
    public ResponseEntity<PurchaseResource> getPurchase(@PathVariable UUID purchaseId) {
        return ResponseEntity.ok(purchaseManagerFacade.getPurchase(purchaseId));
    }

    @GetMapping("/vehicle/{vin}")
    public ResponseEntity<PurchaseResource> getPurchase(@PathVariable String vin) {
        return ResponseEntity.ok(purchaseManagerFacade.getVehiclePurchase(vin));
    }

    @PutMapping
    public ResponseEntity<Void> changePurchaseStatus(@RequestBody EditPurchaseStatusRequest editPurchaseStatusRequest) {
        purchaseManagerFacade.editPurchaseStatus(editPurchaseStatusRequest);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePurchase(@PathVariable UUID purchaseId) {
        purchaseManagerFacade.deletePurchase(purchaseId);
        return ResponseEntity.noContent().build();
    }



}
