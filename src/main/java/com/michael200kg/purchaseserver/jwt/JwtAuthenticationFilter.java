package com.michael200kg.purchaseserver.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.michael200kg.purchaseserver.jpa.model.UserEntity;
import com.michael200kg.purchaseserver.openapi.dto.UserAuth;
import com.michael200kg.purchaseserver.service.auth.TokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static com.michael200kg.purchaseserver.constants.JwtConstants.AUTHORIZATION_HEADER;
import static com.michael200kg.purchaseserver.constants.JwtConstants.TOKEN_PREFIX;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private TokenProvider tokenProvider;

    public JwtAuthenticationFilter(final AuthenticationManager authenticationManager, final TokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest httpRequest,
            HttpServletResponse response)
            throws AuthenticationException {
        try {
            UserAuth creds = new ObjectMapper()
                    .readValue(httpRequest.getInputStream(),
                            UserAuth.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void successfulAuthentication(
            HttpServletRequest httpRequest, HttpServletResponse response,
            FilterChain cain, Authentication authentication) throws IOException {
        String token = tokenProvider.createToken(((UserEntity) authentication.getPrincipal()).getUsername());
        response.setHeader(AUTHORIZATION_HEADER, TOKEN_PREFIX + token);
    }

}
