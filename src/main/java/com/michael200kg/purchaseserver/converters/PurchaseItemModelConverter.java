package com.michael200kg.purchaseserver.converters;

import com.michael200kg.purchaseserver.jpa.model.PurchaseItemEntity;
import com.michael200kg.purchaseserver.openapi.dto.PurchaseItem;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class PurchaseItemModelConverter implements ModelDtoBiConverter<PurchaseItem, PurchaseItemEntity> {

    @Override
    public PurchaseItemEntity dtoToEntity(PurchaseItem dto) {
        PurchaseItemEntity entity = new PurchaseItemEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    public PurchaseItem entityToDto(PurchaseItemEntity entity) {
        PurchaseItem dto = new PurchaseItem();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
