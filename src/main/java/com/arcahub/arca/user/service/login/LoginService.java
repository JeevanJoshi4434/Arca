package com.arcahub.arca.user.service.login;

import com.arcahub.arca.user.dto.JwtResponse;
import com.arcahub.arca.user.dto.LoginRequest;

public interface LoginService {
    boolean supports(LoginRequest request);
    JwtResponse login(LoginRequest request);
}