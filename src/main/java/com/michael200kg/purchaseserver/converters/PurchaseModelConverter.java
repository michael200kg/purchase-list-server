package com.michael200kg.purchaseserver.converters;

import com.michael200kg.purchaseserver.jpa.model.PurchaseEntity;
import com.michael200kg.purchaseserver.openapi.dto.Purchase;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class PurchaseModelConverter implements ModelDtoBiConverter<Purchase, PurchaseEntity> {

    private final PurchaseItemModelConverter purchaseItemModelConverter;

    @Autowired
    public PurchaseModelConverter(PurchaseItemModelConverter purchaseItemModelConverter) {
        this.purchaseItemModelConverter = purchaseItemModelConverter;
    }

    @Override
    public PurchaseEntity dtoToEntity(Purchase dto) {
        PurchaseEntity entity = new PurchaseEntity();
        BeanUtils.copyProperties(dto,entity);
        if(nonNull(dto.getItems())) {
            entity.setItems(purchaseItemModelConverter.dtoListToEntitySet(dto.getItems()));
        }
        return entity;
    }

    @Override
    public Purchase entityToDto(PurchaseEntity entity) {
        Purchase dto = new Purchase();
        BeanUtils.copyProperties(entity,dto);
        if(nonNull(entity.getItems())) {
            dto.setItems(purchaseItemModelConverter.entitySetToDtoList(entity.getItems()));
        }
        return dto;
    }
}
