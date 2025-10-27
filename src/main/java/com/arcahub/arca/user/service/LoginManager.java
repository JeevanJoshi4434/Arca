package com.arcahub.arca.user.service;

import com.arcahub.arca.user.dto.JwtResponse;
import com.arcahub.arca.user.dto.LoginRequest;
import com.arcahub.arca.user.dto.UserLoginRequest;
import com.arcahub.arca.user.service.login.LoginService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginManager {

    private final List<LoginService> loginServices;

    public LoginManager(List<LoginService> loginServices) {
        this.loginServices = loginServices;
    }

    public JwtResponse login(LoginRequest request) {
        return loginServices.stream()
                .filter(service -> service.supports(request))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No suitable login service found"))
                .login(request);
    }
}
