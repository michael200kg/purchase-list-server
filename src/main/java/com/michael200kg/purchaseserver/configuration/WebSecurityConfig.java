package com.michael200kg.purchaseserver.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    private final TokenProvider tokenProvider;
//    private final JwtAuthenticationEntryPoint jwtAuthEntryPoint;
//    private final UserDetailsServiceImpl userDetailsService;

//    @Value(value = "${app.locations.ui}")
//    private String uiLocation;
//
//    @Value(value = "${app.locations.docs}")
//    private String docsLocation;

    /*
        @Autowired
        public WebSecurityConfig(
                TokenProvider tokenProvider,
                JwtAuthenticationEntryPoint jwtAuthEntryPoint,
                UserDetailsServiceImpl userDetailsService) {
            this.tokenProvider = tokenProvider;
            this.jwtAuthEntryPoint = jwtAuthEntryPoint;
            this.userDetailsService = userDetailsService;
        }
    */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .cors().configurationSource(corsConfigurationSource()).and()
                .authorizeRequests()
                .antMatchers("/actuator/**").permitAll() // DEBUG ONLY REMOVE IN PRODUCTION
                .antMatchers("/service/v1/**").permitAll();
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

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    AuthenticationManager jwtAuthenticationManager() throws Exception {
//        return authenticationManager();
//    }
}
