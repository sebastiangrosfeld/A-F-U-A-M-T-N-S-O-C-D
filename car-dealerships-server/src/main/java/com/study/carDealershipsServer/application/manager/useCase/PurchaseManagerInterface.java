package com.study.carDealershipsServer.application.manager.useCase;

import com.study.carDealershipsServer.domain.manager.dto.CreatePurchaseRequest;
import com.study.carDealershipsServer.domain.manager.dto.PurchaseResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PurchaseManagerInterface {

    Page<PurchaseResource> getPurchases(Pageable pageable);

    PurchaseResource getPurchase(UUID purchaseId);

    void createPurchase(CreatePurchaseRequest createPurchaseRequest);

    void editPurchase(String vinNumber);

    void deletePurchase(String vinNumber);
}
