package com.passwordmanager.passwordmanagerlauncher.util;
import java.security.SecureRandom;

import org.springframework.stereotype.Service;

@Service
public class GeneratePassword {

	private static String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static String UPPER_CASE_LETTERS = LOWER_CASE_LETTERS.toUpperCase();
    private static String NUMBERS = "0123456789";
    private static String SPECIAL_CHARS = "!@#$%&*()?+^=-~";
    private static SecureRandom RANDOM_GENERATOR = new SecureRandom();
    private static int PASSWORD_LENGTH = 12;   
    
    public String generateRandomPassword(boolean containsLetters, boolean containsNumbers, boolean containSpecialChars) {
    	String start = LOWER_CASE_LETTERS;
    	if (containsLetters) {
    		start += UPPER_CASE_LETTERS;
    	}
    	if (containsNumbers) {
    		start += NUMBERS;
    	}
    	if (containSpecialChars) {
    		start += SPECIAL_CHARS;
    	}
    	String result = start;
    	StringBuilder generatedPass = new StringBuilder();
    	for (int i = 0; i < PASSWORD_LENGTH; i++) {
    		int index = RANDOM_GENERATOR.nextInt(result.length());
    		char charIndex = result.charAt(index);
    		generatedPass.append(charIndex);
    		
    	}
    	return generatedPass.toString();
    }
}

