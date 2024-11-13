package com.study.carDealershipsServer.application.manager.useCase;

import com.study.carDealershipsServer.domain.manager.dto.CreatePurchaseRequest;
import com.study.carDealershipsServer.domain.manager.dto.EditPurchaseStatusRequest;
import com.study.carDealershipsServer.domain.manager.dto.PurchaseResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PurchaseManagerInterface {

    Page<PurchaseResource> getPurchases(Pageable pageable);

    Page<PurchaseResource> getClientPurchases(Pageable pageable, UUID clientId);

    PurchaseResource getPurchase(UUID purchaseId);

    PurchaseResource getVehiclePurchase(String vinNumber);

    void createPurchase(CreatePurchaseRequest createPurchaseRequest);

    void editPurchaseStatus(EditPurchaseStatusRequest editPurchaseStatusRequest);

    void deletePurchase(UUID purchaseId);
}
