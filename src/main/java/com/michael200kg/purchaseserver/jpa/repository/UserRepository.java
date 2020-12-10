package com.michael200kg.purchaseserver.jpa.repository;

import com.michael200kg.purchaseserver.jpa.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
