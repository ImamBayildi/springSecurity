package com.labreport.backendlabrep.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.labreport.backendlabrep.service.SecurityService;

import jakarta.servlet.Filter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecureConfig {

    // @Autowired
    private final JwtAuthFiltre myJwtAuthFiltre;
    // @Autowired
    private final SecurityService mySecurityService;
    // @Autowired
    private final PasswordEncoder myPasswordEncoder;

    public SecureConfig(JwtAuthFiltre myJwtAuthFiltre, SecurityService mySecurityService, PasswordEncoder myPasswordEncoder) {
        this.myJwtAuthFiltre = myJwtAuthFiltre;
        this.mySecurityService = mySecurityService;
        this.myPasswordEncoder = myPasswordEncoder;
    }

    @Bean
    public SecurityFilterChain myFilterChain(HttpSecurity httpSec) throws Exception {
        return httpSec
                    .csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(req -> req.requestMatchers("/auth/welcome/**","/auth/addNewUser/**","/auth/merhaba/**").permitAll()
                                                     .requestMatchers("auth/user").authenticated()
                                                     .requestMatchers("/auth/admin").hasRole("ADMIN"))
                    .sessionManagement(req -> req.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authenticationProvider(myAuthenticationProvider())
                    .addFilterBefore(myJwtAuthFiltre, UsernamePasswordAuthenticationFilter.class)
                    .build();
                }

                @Bean
                public AuthenticationProvider myAuthenticationProvider(){
                    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
                    authenticationProvider.setUserDetailsService(mySecurityService);
                    authenticationProvider.setPasswordEncoder(myPasswordEncoder);
                    return authenticationProvider;
                }


                @Bean
                public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
                    return configuration.getAuthenticationManager();
                }

                //Dostum bu epey karmaşık olmaya başladı.
}
