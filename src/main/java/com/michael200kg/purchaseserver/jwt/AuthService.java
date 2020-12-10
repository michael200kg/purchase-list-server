package com.michael200kg.purchaseserver.jwt;


import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AuthService {

    String getPassword(String username) throws UsernameNotFoundException;

    void createUser(String username, String password) throws UserAlreadyExistsException;

    void updatePassword(String username, String newPassword);

    boolean isUserExists(String username);
}
