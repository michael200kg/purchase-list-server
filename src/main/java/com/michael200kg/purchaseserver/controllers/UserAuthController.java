package com.michael200kg.purchaseserver.controllers;

import com.michael200kg.purchaseserver.converters.UserModelConverter;
import com.michael200kg.purchaseserver.jpa.model.UserEntity;
import com.michael200kg.purchaseserver.jpa.repository.UserRepository;
import com.michael200kg.purchaseserver.openapi.api.AuthApi;
import com.michael200kg.purchaseserver.openapi.dto.User;
import com.michael200kg.purchaseserver.openapi.dto.UserAuth;
import com.michael200kg.purchaseserver.service.auth.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.michael200kg.purchaseserver.constants.ApplicationConstants.SERVICE_PATH_PREFIX;
import static com.michael200kg.purchaseserver.constants.JwtConstants.TOKEN_PREFIX;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(SERVICE_PATH_PREFIX)
public class UserAuthController implements AuthApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthController.class);

    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserModelConverter userModelConverter;
    private final UserRepository userRepository;

    @Autowired
    public UserAuthController(
            TokenProvider tokenProvider,
            AuthenticationManager authenticationManager,
            UserModelConverter userModelConverter,
            UserRepository userRepository) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.userModelConverter = userModelConverter;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<User> loginUser(@RequestBody UserAuth user) {
        String username = user.getUsername();
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(username, user.getPassword());
        authenticationManager.authenticate(authToken);
        String token = TOKEN_PREFIX + tokenProvider.createToken(username);
        UserEntity userEntity = userRepository.getOne(username);
        User responseUser = userModelConverter.entityToDto(userEntity);
        responseUser.setApiKey(token);
        return ResponseEntity.ok(responseUser);
    }

    @Override
    public ResponseEntity<Void> logoutUser() {
        SecurityContextHolder.clearContext();
        return new ResponseEntity<>(OK);
    }
    
}
