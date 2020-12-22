package com.michael200kg.purchaseserver.controllers;

import com.michael200kg.purchaseserver.converters.PurchaseTemplateModelConverter;
import com.michael200kg.purchaseserver.jpa.model.PurchaseTemplateEntity;
import com.michael200kg.purchaseserver.jpa.repository.PurchaseTemplateRepository;
import com.michael200kg.purchaseserver.openapi.api.PurchaseTemplateApi;
import com.michael200kg.purchaseserver.openapi.dto.PurchaseTemplate;
import com.michael200kg.purchaseserver.service.auth.UserAuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.michael200kg.purchaseserver.constants.ApplicationConstants.SERVICE_PATH_PREFIX;

@RestController
@RequestMapping(SERVICE_PATH_PREFIX)
public class PurchaseTemplateApiController implements PurchaseTemplateApi {

    private final UserAuthenticationService userAuthenticationService;
    private final PurchaseTemplateRepository purchaseTemplateRepository;
    private final PurchaseTemplateModelConverter purchaseTemplateModelConverter;

    public PurchaseTemplateApiController(UserAuthenticationService userAuthenticationService,
                                         PurchaseTemplateRepository purchaseTemplateRepository,
                                         PurchaseTemplateModelConverter purchaseTemplateModelConverter) {
        this.userAuthenticationService = userAuthenticationService;
        this.purchaseTemplateRepository = purchaseTemplateRepository;
        this.purchaseTemplateModelConverter = purchaseTemplateModelConverter;
    }

    @Override
    public ResponseEntity<PurchaseTemplate> createTemplate(PurchaseTemplate purchaseTemplate) {
        PurchaseTemplateEntity entity = purchaseTemplateModelConverter.dtoToEntity(purchaseTemplate);
        PurchaseTemplateEntity persisted = purchaseTemplateRepository.saveAndFlush(entity);
        return ResponseEntity.ok(purchaseTemplateModelConverter.entityToDto(persisted));
    }

    @Override
    public ResponseEntity<Void> deleteTemplate(Integer templateId) {
        purchaseTemplateRepository.deleteById(templateId);
        purchaseTemplateRepository.flush();
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<PurchaseTemplate> editTemplate(PurchaseTemplate purchaseTemplate) {
        PurchaseTemplateEntity entity = purchaseTemplateModelConverter.dtoToEntity(purchaseTemplate);
        PurchaseTemplateEntity persisted = purchaseTemplateRepository.saveAndFlush(entity);
        return ResponseEntity.ok(purchaseTemplateModelConverter.entityToDto(persisted));
    }

    @Override
    public ResponseEntity<PurchaseTemplate> getTemplateById(Integer templateId) {
        PurchaseTemplateEntity entity = purchaseTemplateRepository.getOne(templateId);
        return ResponseEntity.ok(purchaseTemplateModelConverter.entityToDto(entity));
    }

    @Override
    public ResponseEntity<List<PurchaseTemplate>> getTemplates() {
        List<PurchaseTemplateEntity> entities = purchaseTemplateRepository.findAllByUsernameOrderByIdDesc(userAuthenticationService.getCurrentUser().getUsername());
        return ResponseEntity.ok(purchaseTemplateModelConverter.entityListToDtoList(entities));
    }
}
