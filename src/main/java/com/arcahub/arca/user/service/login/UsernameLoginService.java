package com.arcahub.arca.user.service.login;

import com.arcahub.arca.security.enums.TokenType;
import com.arcahub.arca.security.service.AuthService;
import com.arcahub.arca.user.dto.JwtResponse;
import com.arcahub.arca.user.dto.LoginRequest;
import com.arcahub.arca.user.dto.login.UsernameLoginRequest;
import com.arcahub.arca.user.model.User;
import com.arcahub.arca.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsernameLoginService implements LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    public UsernameLoginService(UserRepository userRepository,
                                PasswordEncoder passwordEncoder,
                                AuthService authService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
    }

    @Override
    public boolean supports(LoginRequest request) {
        // Clean type check
        return request instanceof UsernameLoginRequest;
    }

    @Override
    public JwtResponse login(LoginRequest request) {
        UsernameLoginRequest usernameRequest = (UsernameLoginRequest) request;

        User user = userRepository.findByUsername(usernameRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(usernameRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = authService.generateToken(user.getUsername(), TokenType.ACCESS);
        return new JwtResponse(token);
    }
}
