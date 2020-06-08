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

import java.util.List;

import static com.michael200kg.purchaseserver.constants.ApplicationConstants.SERVICE_PATH_PREFIX;

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
        return new ResponseEntity<>(purchaseModelConverter.entityListToDtoList(purchaseEntities), HttpStatus.OK);
    }
}
