package com.arcahub.arca.user.controller;

import com.arcahub.arca.user.dto.*;
import com.arcahub.arca.user.model.User;
import com.arcahub.arca.user.service.LoginManager;
import com.arcahub.arca.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private LoginManager loginManager;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public UserResponse signup(@RequestBody UserSignupRequest request) {
        return userService.signup(request);
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest request) {
        return loginManager.login(request);
    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER')")
    public UserResponse profile(@AuthenticationPrincipal User userDetails) {
        return userService.getProfile(userDetails.getUsername());
    }
}
