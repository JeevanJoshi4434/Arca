package com.arcahub.arca.user.service;

import com.arcahub.arca.user.dto.JwtResponse;
import com.arcahub.arca.user.dto.UserLoginRequest;
import com.arcahub.arca.user.dto.UserResponse;
import com.arcahub.arca.user.dto.UserSignupRequest;

public interface UserService {
    UserResponse signup(UserSignupRequest request);
    JwtResponse login(UserLoginRequest request);
    UserResponse getProfile(String username);
}

