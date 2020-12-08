package com.michael200kg.purchaseserver.jpa.repository;

import com.michael200kg.purchaseserver.jpa.model.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Integer> {
   List<PurchaseEntity> findAllByOrderByIdDesc();
}
