package com.michael200kg.purchaseserver.controllers;

import com.michael200kg.purchaseserver.converters.UserModelConverter;
import com.michael200kg.purchaseserver.jpa.model.UserEntity;
import com.michael200kg.purchaseserver.jpa.repository.UserRepository;
import com.michael200kg.purchaseserver.jwt.AuthService;
import com.michael200kg.purchaseserver.openapi.api.UserApi;
import com.michael200kg.purchaseserver.openapi.dto.PasswordUpdateHolder;
import com.michael200kg.purchaseserver.openapi.dto.User;
import com.michael200kg.purchaseserver.service.auth.UserAuthenticationService;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static com.michael200kg.purchaseserver.constants.ApplicationConstants.SERVICE_PATH_PREFIX;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(SERVICE_PATH_PREFIX)
public class UserApiController implements UserApi {

    private final UserRepository userRepository;
    private final UserModelConverter userModelConverter;
    private final UserAuthenticationService userAuthenticationService;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    public UserApiController(UserRepository userRepository,
                             UserModelConverter userModelConverter,
                             UserAuthenticationService userAuthenticationService,
                             PasswordEncoder passwordEncoder,
                             AuthService authService) {
        this.userRepository = userRepository;
        this.userModelConverter = userModelConverter;
        this.userAuthenticationService = userAuthenticationService;
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
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

    @Override
    public ResponseEntity<Void> updatePassword(PasswordUpdateHolder passwordUpdateHolder) {
        String newPassword = passwordUpdateHolder.getNewPassword();
//        if (newPassword.length() < 8) {
//            throw new HttpClientErrorException(
//                    BAD_REQUEST,
//                    "Минимальная длина нового пароля 8 символов"
//            );
//        }
        String username = userAuthenticationService
                .getCurrentUser()
                .getUsername();
        boolean equals = passwordEncoder.matches(
                passwordUpdateHolder.getOldPassword(),
                authService.getPassword(username)
        );
        if (equals) {
            this.authService.updatePassword(
                    username,
                    passwordEncoder.encode(newPassword)
            );
        } else {
            throw new HttpClientErrorException(
                    BAD_REQUEST,
                    "Текущий пароль неверен"
            );
        }
        return new ResponseEntity<>(OK);
    }


}
