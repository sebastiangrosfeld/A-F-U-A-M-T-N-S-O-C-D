package com.study.carDealershipsServer.application.manager.useCase;

import com.study.carDealershipsServer.domain.purchase.dto.CreatePurchaseOfferRequest;
import com.study.carDealershipsServer.domain.purchase.dto.PurchaseOfferResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PurchaseOfferManagerFacade {

    void createPurchaseOffer(CreatePurchaseOfferRequest request, UUID managerId);

    Page<PurchaseOfferResource> getPurchaseOffers(Pageable pageable);

    PurchaseOfferResource getPurchaseOffer(String vinNumber);

    void deletePurchaseOffer(String vinNumber);
}
