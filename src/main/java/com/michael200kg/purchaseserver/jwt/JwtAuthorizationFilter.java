package com.michael200kg.purchaseserver.jwt;

import com.michael200kg.purchaseserver.service.auth.TokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.michael200kg.purchaseserver.constants.JwtConstants.AUTHORIZATION_HEADER;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final TokenProvider tokenProvider;

    public JwtAuthorizationFilter(
            final AuthenticationManager jwtAuthenticationManager,
            final TokenProvider tokenProvider) {
        super(jwtAuthenticationManager);
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilterInternal(
            HttpServletRequest httpRequest,
            HttpServletResponse httpResponse,
            FilterChain chain)
            throws IOException, ServletException {
        String jwt = resolveToken(httpRequest);
        if (jwt != null) {
            Authentication authentication;
            authentication = tokenProvider.getAuthentication(jwt);
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(httpRequest, httpResponse);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
