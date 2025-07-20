package com.passwordmanager.passwordmanagerlauncher.service;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Encryption {

    private static final String ALGORITHM = "AES";
    private static final String CIPHER_TRANSFORM = "AES/CBC/PKCS5Padding";
    private static final int IV_SIZE = 16;
    private static final String SECRET_KEY = generateRandomKey();

    public static String encryptPassword(String password) throws Exception {
        byte[] iv = new byte[IV_SIZE];
        new SecureRandom().nextBytes(iv);

        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);

        byte[] encryptedBytes = cipher.doFinal(password.getBytes(StandardCharsets.UTF_8));
        byte[] ivAndEncryptedPassword = new byte[IV_SIZE + encryptedBytes.length];
        
        System.arraycopy(iv, 0, ivAndEncryptedPassword, 0, IV_SIZE);
        System.arraycopy(encryptedBytes, 0, ivAndEncryptedPassword, IV_SIZE, encryptedBytes.length);

        return Base64.getEncoder().encodeToString(ivAndEncryptedPassword);
    }

    public static String decryptPassword(String encryptedPassword) throws Exception {
        byte[] ivAndEncryptedPassword = Base64.getDecoder().decode(encryptedPassword);

        byte[] iv = new byte[IV_SIZE];
        byte[] encryptedBytes = new byte[ivAndEncryptedPassword.length - IV_SIZE];

        System.arraycopy(ivAndEncryptedPassword, 0, iv, 0, IV_SIZE);
        System.arraycopy(ivAndEncryptedPassword, IV_SIZE, encryptedBytes, 0, encryptedBytes.length);

        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    public static String generateRandomKey(){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder builder = new StringBuilder();
        Random rand = new Random();
        int length = 16;
        for (int i = 0; i < length; i++){
            int index = rand.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            
            builder.append(randomChar);
        }
        return builder.toString();
    }
}