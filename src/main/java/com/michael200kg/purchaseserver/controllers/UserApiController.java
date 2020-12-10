package com.michael200kg.purchaseserver.controllers;

import com.michael200kg.purchaseserver.converters.UserModelConverter;
import com.michael200kg.purchaseserver.jpa.model.UserEntity;
import com.michael200kg.purchaseserver.jpa.repository.UserRepository;
import com.michael200kg.purchaseserver.openapi.api.UserApi;
import com.michael200kg.purchaseserver.openapi.dto.User;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.michael200kg.purchaseserver.constants.ApplicationConstants.SERVICE_PATH_PREFIX;

@RestController
@RequestMapping(SERVICE_PATH_PREFIX)
public class UserApiController implements UserApi {

    private final UserRepository userRepository;
    private final UserModelConverter userModelConverter;

    public UserApiController(UserRepository userRepository,
                             UserModelConverter userModelConverter) {
        this.userRepository = userRepository;
        this.userModelConverter = userModelConverter;
    }

    @Override
    public ResponseEntity<User> updateUser(User user) {
        userRepository.saveAndFlush(userModelConverter.dtoToEntity(user));
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<List<User>> getUsers() {
        List<UserEntity> entities = userRepository.findAll(Sort.by("fio"));
        return ResponseEntity.ok(userModelConverter.entityListToDtoList(entities));
    }
}
