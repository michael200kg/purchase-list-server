package com.michael200kg.purchaseserver.converters;

import com.michael200kg.purchaseserver.jpa.model.UserEntity;
import com.michael200kg.purchaseserver.openapi.dto.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserModelConverter implements ModelDtoBiConverter<User, UserEntity> {

    @Override
    public UserEntity dtoToEntity(User dto) {
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    public User entityToDto(UserEntity entity) {
        User dto = new User();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
