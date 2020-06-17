package com.michael200kg.purchaseserver.controllers;

import com.michael200kg.purchaseserver.jpa.repository.PurchaseItemRepository;
import com.michael200kg.purchaseserver.openapi.api.PurchaseItemApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

import static com.michael200kg.purchaseserver.constants.ApplicationConstants.SERVICE_PATH_PREFIX;
import static java.time.OffsetDateTime.now;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(SERVICE_PATH_PREFIX)
public class PurchaseItemApiController implements PurchaseItemApi {

    private final PurchaseItemRepository purchaseItemRepository;

    @Autowired
    public PurchaseItemApiController(PurchaseItemRepository purchaseItemRepository) {
        this.purchaseItemRepository = purchaseItemRepository;
    }

    @Override
    public ResponseEntity<Void> togglePurchaseItem(Integer purchaseItemId) {
        this.purchaseItemRepository.findById(purchaseItemId).ifPresent(itemEntity -> {
            boolean checked =  itemEntity.getChecked();
            itemEntity.setChecked(!checked);
            itemEntity.setCheckedDate(checked ? null : now());
            purchaseItemRepository.saveAndFlush(itemEntity);
        });
        return new ResponseEntity<>(OK);
    }
}
