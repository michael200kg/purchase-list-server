package com.michael200kg.purchaseserver.jpa.repository;

import com.michael200kg.purchaseserver.jpa.model.PurchaseTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseTemplateRepository extends JpaRepository<PurchaseTemplateEntity, Integer> {
    List<PurchaseTemplateEntity> findAllByUsernameOrderByIdDesc(String username);
}
