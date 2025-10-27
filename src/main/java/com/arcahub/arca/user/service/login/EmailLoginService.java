package com.arcahub.arca.user.service.login;

import com.arcahub.arca.security.enums.TokenType;
import com.arcahub.arca.security.service.AuthService;
import com.arcahub.arca.user.dto.JwtResponse;
import com.arcahub.arca.user.dto.LoginRequest;
import com.arcahub.arca.user.dto.login.EmailLoginRequest;
import com.arcahub.arca.user.model.User;
import com.arcahub.arca.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmailLoginService implements LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    public EmailLoginService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthService authService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
    }

    @Override
    public boolean supports(LoginRequest request) {
        return request instanceof EmailLoginRequest;
    }

    @Override
    public JwtResponse login(LoginRequest request) {
        EmailLoginRequest emailRequest = (EmailLoginRequest) request;

        User user = userRepository.findByEmail(emailRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(emailRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = authService.generateToken(user.getEmail(), TokenType.ACCESS);
        return new JwtResponse(token);
    }
}
