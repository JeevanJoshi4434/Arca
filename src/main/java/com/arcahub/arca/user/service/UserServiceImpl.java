package com.arcahub.arca.user.service;

import com.arcahub.arca.security.enums.TokenType;
import com.arcahub.arca.security.service.AuthService;
import com.arcahub.arca.user.dto.JwtResponse;
import com.arcahub.arca.user.dto.UserLoginRequest;
import com.arcahub.arca.user.dto.UserResponse;
import com.arcahub.arca.user.dto.UserSignupRequest;
import com.arcahub.arca.user.model.User;
import com.arcahub.arca.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           AuthService authService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
    }

    @Override
    public UserResponse signup(UserSignupRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Set.of("USER"));

        userRepository.save(user);
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getRoles());
    }

    @Override
    public JwtResponse login(UserLoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = authService.generateToken(user.getUsername(), TokenType.ACCESS);
        return new JwtResponse(token);
    }

    @Override
    public UserResponse getProfile(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getRoles());
    }
}
