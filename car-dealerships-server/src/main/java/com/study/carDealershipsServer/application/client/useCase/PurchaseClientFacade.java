package com.study.carDealershipsServer.application.client.useCase;

import com.study.carDealershipsServer.domain.purchase.dto.PurchaseResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PurchaseClientFacade {

    Page<PurchaseResource> getClientPurchases(Pageable pageable);
}
