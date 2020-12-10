package com.michael200kg.purchaseserver.service.auth;

import com.michael200kg.purchaseserver.jpa.model.UserEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService {

    public UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)
                && authentication.isAuthenticated()) {
            return (UserEntity) authentication.getPrincipal();
        } else {
            return null;
        }
    }

}
