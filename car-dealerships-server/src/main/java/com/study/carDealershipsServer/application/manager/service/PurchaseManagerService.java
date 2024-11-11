package com.study.carDealershipsServer.application.manager.service;

import com.study.carDealershipsServer.application.manager.useCase.PurchaseManagerInterface;
import com.study.carDealershipsServer.domain.manager.dto.CreatePurchaseRequest;
import com.study.carDealershipsServer.domain.manager.dto.PurchaseResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PurchaseManagerService implements PurchaseManagerInterface {

    @Override
    public Page<PurchaseResource> getPurchases(Pageable pageable) {
        return null;
    }

    @Override
    public PurchaseResource getPurchase(UUID purchaseId) {
        return null;
    }

    public void createPurchase(CreatePurchaseRequest createPurchaseRequest) {

    }

    @Override
    public void editPurchase(String vinNumber) {

    }

    @Override
    public void deletePurchase(String vinNumber) {

    }
}
