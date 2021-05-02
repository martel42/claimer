package com.feeldip.spring.claimer.controller;

import com.feeldip.spring.claimer.config.jwt.JwtProvider;
import com.feeldip.spring.claimer.dto.AuthRequest;
import com.feeldip.spring.claimer.dto.AuthResponse;
import com.feeldip.spring.claimer.entity.AdminEntity;
import com.feeldip.spring.claimer.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        AdminEntity adminEntity = adminService.getAdminByUsernameAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(adminEntity.getUsernameAdmin());
        return new AuthResponse(token);
    }
}
