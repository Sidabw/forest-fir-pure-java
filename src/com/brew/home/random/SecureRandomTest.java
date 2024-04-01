package com.brew.home.random;

import java.security.SecureRandom;

/**
 * @author shaogz
 * @since 2024/4/1 15:38
 */
public class SecureRandomTest {

    public static void main(String[] args) {
        SecureRandom secureRandom = new SecureRandom();
        int i = 100;
        while (i-- > 0) {
            System.out.println(secureRandom.nextInt(1000));
        }
    }
}
