package com.study.carDealershipsServer.application.manager.service;

import com.study.carDealershipsServer.application.manager.useCase.PurchaseOfferManagerInterface;
import com.study.carDealershipsServer.domain.manager.dto.CreatePurchaseOfferRequest;
import com.study.carDealershipsServer.domain.manager.dto.PurchaseOfferResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOfferManagerService implements PurchaseOfferManagerInterface {
    @Override
    public void createPurchaseOffer(CreatePurchaseOfferRequest request) {

    }

    @Override
    public Page<PurchaseOfferResource> getPurchaseOffers(Pageable pageable) {
        return null;
    }

    @Override
    public PurchaseOfferResource getPurchaseOffer(String vinNumber) {
        return null;
    }

    @Override
    public void deletePurchaseOffer(String vinNumber) {

    }
}
