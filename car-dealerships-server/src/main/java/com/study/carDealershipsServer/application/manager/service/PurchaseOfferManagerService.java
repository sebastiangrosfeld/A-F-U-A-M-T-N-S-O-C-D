package com.study.carDealershipsServer.application.manager.service;

import com.study.carDealershipsServer.application.manager.useCase.PurchaseOfferManagerFacade;
import com.study.carDealershipsServer.common.errors.ServiceException;
import com.study.carDealershipsServer.domain.purchase.dto.CreatePurchaseOfferRequest;
import com.study.carDealershipsServer.domain.purchase.dto.PurchaseOfferResource;
import com.study.carDealershipsServer.domain.purchase.mapper.PurchaseOfferMapper;
import com.study.carDealershipsServer.domain.purchase.repository.PurchaseOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseOfferManagerService implements PurchaseOfferManagerFacade {

    private final PurchaseOfferRepository purchaseOfferRepository;
    private final PurchaseOfferMapper purchaseOfferMapper;

    @Override
    public void createPurchaseOffer(CreatePurchaseOfferRequest request, UUID managerId) {
        if (purchaseOfferRepository.existsByVehicleVinNumber(request.vehicleVin())) {
            throw new ServiceException("Offer with that vehicle already exists", HttpStatus.BAD_REQUEST);
        }
        var purchaseOffer = purchaseOfferMapper.createRequestToPurchaseOffer(request, managerId);
        purchaseOfferRepository.save(purchaseOffer);
    }

    @Override
    public Page<PurchaseOfferResource> getPurchaseOffers(Pageable pageable) {
        var purchaseOffers = purchaseOfferRepository.findAll(pageable);
        return purchaseOffers.map(purchaseOfferMapper::entityToResource);
    }

    @Override
    public PurchaseOfferResource getPurchaseOffer(String vinNumber) {
        var purchaseOffer = purchaseOfferRepository.findByVehicleVinNumber(vinNumber)
                .orElseThrow(() -> new ServiceException("Offer with that vehicle does not exist", HttpStatus.NOT_FOUND));
        return purchaseOfferMapper.entityToResource(purchaseOffer);
    }

    @Override
    public void deletePurchaseOffer(String vinNumber) {
        if (!purchaseOfferRepository.existsByVehicleVinNumber(vinNumber)) {
            throw new ServiceException("Offer with that vehicle does not exist", HttpStatus.NOT_FOUND);
        }
        purchaseOfferRepository.deleteByVehicleVinNumber(vinNumber);
    }
}
