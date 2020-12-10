package com.michael200kg.purchaseserver.jwt;


import com.michael200kg.purchaseserver.jpa.model.UserEntity;
import com.michael200kg.purchaseserver.jpa.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final List<AuthService> authenticationProviders;
    private final UserRepository userRepository;


    public UserDetailsServiceImpl(
            List<AuthService> authenticationProviders,
            UserRepository userRepository
    ) {
        this.authenticationProviders = authenticationProviders;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password;
        for (AuthService authService : authenticationProviders) {
            password = authService.getPassword(username);
            if (Objects.nonNull(password)) {
                return constructUser(username, password);
            }
        }
        throw new UsernameNotFoundException(username);
    }

    private User constructUser(String username, String password) {
        UserEntity user = userRepository
                .findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return new User(username, password, new ArrayList<>());
    }
}
