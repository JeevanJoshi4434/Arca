package com.arcahub.arca.security;

import com.arcahub.arca.security.encryption.algorithm.AESGCMAlgorithm;
import com.arcahub.arca.security.encryption.algorithm.EncryptionAlgorithm;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;

public class CryptoUtils {

    private final Map<String, EncryptionAlgorithm> algorithms = new HashMap<>();

    public CryptoUtils() {
        algorithms.put("AES_GCM", new AESGCMAlgorithm());
    }

    public EncryptionAlgorithm getAlgorithm(String name) {
        EncryptionAlgorithm algo = algorithms.get(name);
        if (algo == null) throw new IllegalArgumentException("Algorithm not found: " + name);
        return algo;
    }

    public String encrypt(String algorithmName, byte[] data, SecretKey key, byte[] iv) throws Exception {
        return getAlgorithm(algorithmName).encrypt(data, key, iv);
    }

    public byte[] decrypt(String algorithmName, String encryptedData, SecretKey key, byte[] iv) throws Exception {
        return getAlgorithm(algorithmName).decrypt(encryptedData, key, iv);
    }

    public SecretKey generateKey(String algorithmName) throws Exception {
        return getAlgorithm(algorithmName).generateKey();
    }
}
