package com.study.carDealershipsServer.application.manager.useCase;

import com.study.carDealershipsServer.domain.manager.dto.CreatePurchaseOfferRequest;
import com.study.carDealershipsServer.domain.manager.dto.PurchaseOfferResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PurchaseOfferManagerInterface {

    void createPurchaseOffer(CreatePurchaseOfferRequest request);

    Page<PurchaseOfferResource> getPurchaseOffers(Pageable pageable);

    PurchaseOfferResource getPurchaseOffer(String vinNumber);

    void deletePurchaseOffer(String vinNumber);
}
