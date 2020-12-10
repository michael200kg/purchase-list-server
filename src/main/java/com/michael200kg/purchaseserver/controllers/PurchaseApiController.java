package com.michael200kg.purchaseserver.controllers;

import com.michael200kg.purchaseserver.converters.PurchaseItemModelConverter;
import com.michael200kg.purchaseserver.converters.PurchaseModelConverter;
import com.michael200kg.purchaseserver.jpa.model.PurchaseEntity;
import com.michael200kg.purchaseserver.jpa.model.UserEntity;
import com.michael200kg.purchaseserver.jpa.repository.PurchaseItemRepository;
import com.michael200kg.purchaseserver.jpa.repository.PurchaseRepository;
import com.michael200kg.purchaseserver.openapi.api.PurchaseApi;
import com.michael200kg.purchaseserver.openapi.dto.Purchase;
import com.michael200kg.purchaseserver.service.auth.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static com.michael200kg.purchaseserver.constants.ApplicationConstants.SERVICE_PATH_PREFIX;
import static java.time.OffsetDateTime.now;
import static java.util.Objects.isNull;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(SERVICE_PATH_PREFIX)
public class PurchaseApiController implements PurchaseApi {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseModelConverter purchaseModelConverter;
    private final PurchaseItemRepository purchaseItemRepository;
    private final PurchaseItemModelConverter purchaseItemModelConverter;
    private final UserAuthenticationService userAuthenticationService;

    @Autowired
    public PurchaseApiController(PurchaseRepository purchaseRepository,
                                 PurchaseModelConverter purchaseModelConverter,
                                 PurchaseItemRepository purchaseItemRepository,
                                 PurchaseItemModelConverter purchaseItemModelConverter, UserAuthenticationService userAuthenticationService) {
        this.purchaseRepository = purchaseRepository;
        this.purchaseModelConverter = purchaseModelConverter;
        this.purchaseItemRepository = purchaseItemRepository;
        this.purchaseItemModelConverter = purchaseItemModelConverter;
        this.userAuthenticationService = userAuthenticationService;
    }

    @Override
    public ResponseEntity<List<Purchase>> getPurchases() {
        UserEntity userEntity = userAuthenticationService.getCurrentUser();
        if(isNull(userEntity)) {
            throw new HttpClientErrorException(
                    NOT_FOUND,
                    "Вы не авторизованы!"
            );
        }
        List<PurchaseEntity> purchaseEntities = purchaseRepository
                .findAllByUsernameOrSharedForUsernameOrderByIdDesc(userEntity.getUsername(), userEntity.getUsername());
        return new ResponseEntity<>(purchaseModelConverter.entityListToDtoList(purchaseEntities), OK);
    }

    @Override
    public ResponseEntity<Purchase> createPurchase(Purchase purchase) {
        PurchaseEntity entity = purchaseModelConverter.dtoToEntity(purchase);
        entity.setCreatedDate(now());
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
