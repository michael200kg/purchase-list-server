package com.michael200kg.purchaseserver.converters;

import com.michael200kg.purchaseserver.jpa.model.PurchaseTemplateItemEntity;
import com.michael200kg.purchaseserver.openapi.dto.PurchaseTemplateItem;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PurchaseTemplateItemModelConverter implements ModelDtoBiConverter<PurchaseTemplateItem, PurchaseTemplateItemEntity> {

    @Override
    public PurchaseTemplateItemEntity dtoToEntity(PurchaseTemplateItem dto) {
        PurchaseTemplateItemEntity entity = new PurchaseTemplateItemEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    public PurchaseTemplateItem entityToDto(PurchaseTemplateItemEntity entity) {
        PurchaseTemplateItem dto = new PurchaseTemplateItem();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
