package com.study.carDealershipsServer.application.client.useCase;

import com.study.carDealershipsServer.domain.manager.dto.PurchaseResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PurchaseClientInterface {

    Page<PurchaseResource> getClientPurchases(Pageable pageable);
}
