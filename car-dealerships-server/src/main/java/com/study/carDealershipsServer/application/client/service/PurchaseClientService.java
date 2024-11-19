package com.study.carDealershipsServer.application.client.service;

import com.study.carDealershipsServer.application.client.useCase.PurchaseClientFacade;
import com.study.carDealershipsServer.domain.purchase.dto.PurchaseResource;
import com.study.carDealershipsServer.domain.purchase.mapper.PurchaseMapper;
import com.study.carDealershipsServer.domain.purchase.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.study.carDealershipsServer.application.authentication.service.AuthService.getAuthUserId;

@Service
@RequiredArgsConstructor
public class PurchaseClientService implements PurchaseClientFacade {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseMapper purchaseMapper;

    @Override
    public Page<PurchaseResource> getClientPurchases(Pageable pageable) {
        // change to UUID from authorization
        UUID clientId = getAuthUserId();
        return purchaseRepository.findAllByClientId(clientId, pageable)
                .map(purchaseMapper::purchaseToResource);
    }
}
