package com.michael200kg.purchaseserver.converters;

import com.michael200kg.purchaseserver.jpa.model.PurchaseTemplateEntity;
import com.michael200kg.purchaseserver.openapi.dto.PurchaseTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class PurchaseTemplateModelConverter implements ModelDtoBiConverter<PurchaseTemplate, PurchaseTemplateEntity> {

    private final PurchaseTemplateItemModelConverter purchaseTemplateItemModelConverter;

    public PurchaseTemplateModelConverter(PurchaseTemplateItemModelConverter purchaseTemplateItemModelConverter) {
        this.purchaseTemplateItemModelConverter = purchaseTemplateItemModelConverter;
    }

    @Override
    public PurchaseTemplateEntity dtoToEntity(PurchaseTemplate dto) {
        PurchaseTemplateEntity entity = new PurchaseTemplateEntity();
        BeanUtils.copyProperties(dto, entity);
        if (nonNull(dto.getItems())) {
            entity.setItems(purchaseTemplateItemModelConverter.dtoListToEntitySet(dto.getItems()));
        }
        return entity;
    }

    @Override
    public PurchaseTemplate entityToDto(PurchaseTemplateEntity entity) {
        PurchaseTemplate dto = new PurchaseTemplate();
        BeanUtils.copyProperties(entity, dto);
        if (nonNull(entity.getItems())) {
            dto.setItems(purchaseTemplateItemModelConverter.entitySetToDtoList(entity.getItems()));
        }
        return dto;
    }
}
