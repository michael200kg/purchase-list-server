package com.michael200kg.purchaseserver.controllers;

import com.michael200kg.purchaseserver.converters.PurchaseModelConverter;
import com.michael200kg.purchaseserver.jpa.model.PurchaseEntity;
import com.michael200kg.purchaseserver.jpa.repository.PurchaseRepository;
import com.michael200kg.purchaseserver.openapi.api.PurchaseApi;
import com.michael200kg.purchaseserver.openapi.dto.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static com.michael200kg.purchaseserver.constants.ApplicationConstants.SERVICE_PATH_PREFIX;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(SERVICE_PATH_PREFIX)
public class PurchaseApiController implements PurchaseApi {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseModelConverter purchaseModelConverter;

    @Autowired
    public PurchaseApiController(PurchaseRepository purchaseRepository,
                                 PurchaseModelConverter purchaseModelConverter) {
        this.purchaseRepository = purchaseRepository;
        this.purchaseModelConverter = purchaseModelConverter;
    }

    @Override
    public ResponseEntity<List<Purchase>> getPurchases() {
        List<PurchaseEntity> purchaseEntities = purchaseRepository.findAll();
        return new ResponseEntity<>(purchaseModelConverter.entityListToDtoList(purchaseEntities), OK);
    }

    @Override
    public ResponseEntity<Purchase> createPurchase(Purchase purchase) {
        PurchaseEntity entity = purchaseModelConverter.dtoToEntity(purchase);
        purchaseRepository.saveAndFlush(entity);
        return new ResponseEntity<>(purchaseModelConverter.entityToDto(entity), OK);
    }

    @Override
    public ResponseEntity<Void> deletePurchase(Integer purchaseId) {
        purchaseRepository.deleteById(purchaseId);
        purchaseRepository.flush();
        return new ResponseEntity<>(OK);
    }

    @Override
    public ResponseEntity<Void> editPurchase(Purchase purchase) {
        PurchaseEntity entity = purchaseModelConverter.dtoToEntity(purchase);
        purchaseRepository.saveAndFlush(entity);
        return new ResponseEntity<>(OK);
    }

    @Override
    public ResponseEntity<Purchase> getPurchaseById(Integer purchaseId) {
        PurchaseEntity entity = purchaseRepository.getOne(purchaseId);
        return new ResponseEntity<>(purchaseModelConverter.entityToDto(entity), OK);
    }
}
