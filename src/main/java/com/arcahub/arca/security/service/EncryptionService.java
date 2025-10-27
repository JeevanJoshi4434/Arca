package com.arcahub.arca.security.service;

import com.arcahub.arca.security.encryption.algorithm.EncryptionAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EncryptionService {
    private final Map<String, EncryptionAlgorithm> algorithms;

    public EncryptionService(Map<String, EncryptionAlgorithm> algorithms){
        this.algorithms = algorithms;
    }

    public EncryptionAlgorithm getAlgorithm(String name){
        EncryptionAlgorithm algo = algorithms.get(name);
        if(algo==null){
            throw new IllegalArgumentException("Algorithm not found: " + name);
        }
        return algo;
    }
}
