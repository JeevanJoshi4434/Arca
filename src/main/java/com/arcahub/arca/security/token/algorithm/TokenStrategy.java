package com.arcahub.arca.security.token.algorithm;

import com.arcahub.arca.security.enums.TokenType;

public interface TokenStrategy {
    TokenType getTokenType();
    String generateToken(String username);
    String getUsernameFromToken(String token);
    boolean validateToken(String token);
}
