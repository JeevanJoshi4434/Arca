package com.arcahub.arca.security.encryption.algorithm;

import javax.crypto.SecretKey;

public interface EncryptionAlgorithm {
    String encrypt(byte[] data, SecretKey key, byte[] iv) throws Exception;
    byte[] decrypt(String encryptedData, SecretKey key, byte[] iv) throws Exception;

    SecretKey generateKey() throws Exception;
}
