package com.study.carDealershipsServer.application.manager.controller;

import com.study.carDealershipsServer.application.manager.useCase.PurchaseManagerInterface;
import com.study.carDealershipsServer.domain.manager.dto.CreatePurchaseRequest;
import com.study.carDealershipsServer.domain.manager.dto.EditPurchaseStatusRequest;
import com.study.carDealershipsServer.domain.manager.dto.PurchaseResource;
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

    private final PurchaseManagerInterface purchaseManagerInterface;

    @PostMapping
    public ResponseEntity<Void> createPurchase(@RequestBody CreatePurchaseRequest createPurchaseRequest) {
        purchaseManagerInterface.createPurchase(createPurchaseRequest);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/all")
    public ResponseEntity<Page<PurchaseResource>> getPurchases(Pageable pageable) {
        return ResponseEntity.ok(purchaseManagerInterface.getPurchases(pageable));
    }

    @GetMapping("/{clientId}/all")
    public ResponseEntity<Page<PurchaseResource>> getPurchases(@PathVariable UUID clientId, Pageable pageable) {
        return ResponseEntity.ok(purchaseManagerInterface.getClientPurchases(pageable, clientId));
    }

    @GetMapping("/{purchaseId}")
    public ResponseEntity<PurchaseResource> getPurchase(@PathVariable UUID purchaseId) {
        return ResponseEntity.ok(purchaseManagerInterface.getPurchase(purchaseId));
    }

    @GetMapping("/vehicle/{vin}")
    public ResponseEntity<PurchaseResource> getPurchase(@PathVariable String vin) {
        return ResponseEntity.ok(purchaseManagerInterface.getVehiclePurchase(vin));
    }

    @PutMapping
    public ResponseEntity<Void> changePurchaseStatus(@RequestBody EditPurchaseStatusRequest editPurchaseStatusRequest) {
        purchaseManagerInterface.editPurchaseStatus(editPurchaseStatusRequest);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePurchase(@PathVariable UUID purchaseId) {
        purchaseManagerInterface.deletePurchase(purchaseId);
        return ResponseEntity.noContent().build();
    }



}
