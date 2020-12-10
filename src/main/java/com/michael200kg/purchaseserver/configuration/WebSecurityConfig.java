package com.michael200kg.purchaseserver.configuration;

import com.michael200kg.purchaseserver.jwt.JwtAuthenticationEntryPoint;
import com.michael200kg.purchaseserver.jwt.JwtAuthenticationFilter;
import com.michael200kg.purchaseserver.jwt.JwtAuthorizationFilter;
import com.michael200kg.purchaseserver.jwt.UserDetailsServiceImpl;
import com.michael200kg.purchaseserver.service.auth.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthEntryPoint;
    private final UserDetailsServiceImpl userDetailsService;


    @Autowired
    public WebSecurityConfig(
            TokenProvider tokenProvider,
            JwtAuthenticationEntryPoint jwtAuthEntryPoint,
            UserDetailsServiceImpl userDetailsService) {
        this.tokenProvider = tokenProvider;
        this.jwtAuthEntryPoint = jwtAuthEntryPoint;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .cors().configurationSource(corsConfigurationSource()).and()
                .authorizeRequests()
                .antMatchers("/purchases/auth/login").permitAll()
                .anyRequest().authenticated().and()
                .logout().invalidateHttpSession(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/service/v2/user/logout")).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint).and()
                .addFilterBefore(new JwtAuthorizationFilter(authenticationManager(), tokenProvider),
                        BasicAuthenticationFilter.class)
                .addFilterBefore(new JwtAuthenticationFilter(authenticationManager(), tokenProvider),
                        UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().maximumSessions(1000).expiredUrl("/service/v2/auth/login");

    }

    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
        corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager jwtAuthenticationManager() throws Exception {
        return authenticationManager();
    }
}
