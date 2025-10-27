package com.arcahub.arca.security.token;

import com.arcahub.arca.security.enums.TokenType;
import com.arcahub.arca.security.token.algorithm.AccessTokenStrategy;
import com.arcahub.arca.security.token.algorithm.RefreshTokenStrategy;
import com.arcahub.arca.security.token.algorithm.TokenStrategy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TokenStrategyFactory {

    private final Map<TokenType, TokenStrategy> strategyMap = new HashMap<>();

    public TokenStrategyFactory(List<TokenStrategy> strategies){
        for(TokenStrategy strategy: strategies){
            strategyMap.put(strategy.getTokenType(), strategy);
        }
    }

    public TokenStrategy getStrategy(TokenType tokenType){
        TokenStrategy strategy = strategyMap.get(tokenType);
        if(strategy == null){
            throw new IllegalArgumentException("No strategy found for token type: " + tokenType);
        }
        return strategy;
    }
}
