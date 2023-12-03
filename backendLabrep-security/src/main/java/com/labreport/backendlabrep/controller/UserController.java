package com.labreport.backendlabrep.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labreport.backendlabrep.dto.CreateUserRequest;
import com.labreport.backendlabrep.entity.User;
import com.labreport.backendlabrep.service.JwtService;
import com.labreport.backendlabrep.service.SecurityService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@Slf4j
public class UserController {

    private final SecurityService securityService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserController(SecurityService securityService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.securityService = securityService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/merhaba")
    public String merhaba() {
        return "merhaba";
    }

    @PostMapping("/addNewUser")
    public User addNewUser(@RequestBody CreateUserRequest request) {
        return  null;//securityService.addNewUser(request);
        }
    
    
}
