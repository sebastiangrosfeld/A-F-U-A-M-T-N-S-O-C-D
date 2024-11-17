package com.study.carDealershipsServer.application.manager.service;

import com.study.carDealershipsServer.application.manager.useCase.PurchaseManagerInterface;
import com.study.carDealershipsServer.common.errors.ServiceException;
import com.study.carDealershipsServer.domain.client.repository.ClientRepository;
import com.study.carDealershipsServer.domain.manager.dto.CreatePurchaseRequest;
import com.study.carDealershipsServer.domain.manager.dto.EditPurchaseStatusRequest;
import com.study.carDealershipsServer.domain.manager.dto.PurchaseResource;
import com.study.carDealershipsServer.domain.manager.mapper.PurchaseMapper;
import com.study.carDealershipsServer.domain.manager.repository.PurchaseRepository;
import com.study.carDealershipsServer.domain.vehicle.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseManagerService implements PurchaseManagerInterface {

    private final PurchaseRepository purchaseRepository;
    private final VehicleRepository vehicleRepository;
    private final ClientRepository clientRepository;
    private final PurchaseMapper purchaseMapper;

    @Override
    public Page<PurchaseResource> getPurchases(Pageable pageable) {
        var purchases = purchaseRepository.findAll(pageable);
        return purchases.map(purchaseMapper::purchaseToResource);
    }

    @Override
    public Page<PurchaseResource> getClientPurchases(Pageable pageable, UUID clientId) {
        var purchases = purchaseRepository.findAllByClientId(clientId, pageable);
        return purchases.map(purchaseMapper::purchaseToResource);
    }

    @Override
    public PurchaseResource getPurchase(UUID purchaseId) {
        var purchase = purchaseRepository.findByPurchaseId(purchaseId)
                .orElseThrow(() -> new ServiceException("Fetched purchase not exists", HttpStatus.NOT_FOUND));
        return purchaseMapper.purchaseToResource(purchase);
    }

    @Override
    public PurchaseResource getVehiclePurchase(String vinNumber) {
        var purchase = purchaseRepository.findByVehicleVinNumber(vinNumber)
                .orElseThrow(() -> new ServiceException("Fetched vehicle purchase not exists", HttpStatus.NOT_FOUND));
        return purchaseMapper.purchaseToResource(purchase);
    }

    @Override
    public void createPurchase(CreatePurchaseRequest createPurchaseRequest) {
        validatePurchase(createPurchaseRequest);
        var purchase = purchaseMapper.createRequestToPurchase(createPurchaseRequest);
        purchaseRepository.save(purchase);
    }

    @Override
    public void editPurchaseStatus(EditPurchaseStatusRequest editPurchaseStatusRequest) {
        var purchase = purchaseRepository.findByPurchaseId(editPurchaseStatusRequest.purchaseId())
                .orElseThrow(() -> new ServiceException("Fetched purchase not exists", HttpStatus.BAD_REQUEST));
        purchase.setPurchaseStatus(editPurchaseStatusRequest.purchaseStatus());
        purchaseRepository.save(purchase);
    }

    @Override
    public void deletePurchase(UUID purchaseId) {
        if (!purchaseRepository.existsByPurchaseId(purchaseId)) {
            throw new ServiceException("Purchase not exists", HttpStatus.NOT_FOUND);
        }
        purchaseRepository.deleteByPurchaseId(purchaseId);
    }

    private void validatePurchase(CreatePurchaseRequest createPurchaseRequest) {
        if (!vehicleRepository.existsByVinNumber(createPurchaseRequest.vinNumber())) {
            throw new ServiceException("Vehicle to purchase not exists", HttpStatus.NOT_FOUND);
        }
        if (!clientRepository.existsByEmail(createPurchaseRequest.email())) {
            throw new ServiceException("Client to purchase not exists", HttpStatus.NOT_FOUND);
        }
    }
}
