package com.michael200kg.purchaseserver.service.auth;

import com.michael200kg.purchaseserver.jpa.model.UserEntity;
import com.michael200kg.purchaseserver.jpa.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class TokenProvider {

    private final UserRepository userRepository;

    private String secretKey = "123456";

    public TokenProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String createToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        String username =
                Jwts.parser()
                        .setSigningKey(secretKey)
                        .parseClaimsJws(token)
                        .getBody()
                        .getSubject();

        UserEntity userEntity = userRepository.getOne(username);
        List<GrantedAuthority> roles = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        return new UsernamePasswordAuthenticationToken(userEntity, "", roles);
    }

    public UserEntity getUserFromToken(String token) {
        // Can have "Bearer " as a prefix
        token = token.replaceAll("Bearer ", "");
        String username =
                Jwts.parser()
                        .setSigningKey(secretKey)
                        .parseClaimsJws(token)
                        .getBody()
                        .getSubject();

        return userRepository.getOne(username);
    }
}
