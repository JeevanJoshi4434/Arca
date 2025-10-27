package com.arcahub.arca.security.service;

import com.arcahub.arca.security.enums.TokenType;
import com.arcahub.arca.security.token.TokenStrategyFactory;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final TokenStrategyFactory tokenStrategyFactory;

    public AuthService(TokenStrategyFactory tokenStrategyFactory){
        this.tokenStrategyFactory = tokenStrategyFactory;
    }
    
    public String generateToken(String username, TokenType tokenType){
        return tokenStrategyFactory.getStrategy(tokenType).generateToken(username);
    }
    
    public String getUsernameFromToken(String token, TokenType tokenType){
        return tokenStrategyFactory.getStrategy(tokenType).getUsernameFromToken(token);
    }
    
    public boolean validateToken(String token, TokenType tokenType){
        return tokenStrategyFactory.getStrategy(tokenType).validateToken(token);
    }
}
