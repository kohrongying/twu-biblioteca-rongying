package com.twu.biblioteca;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHash {
    public String generateHash(String password) {
        String saltStr = "twurongying";
        byte[] salt = saltStr.getBytes();
        MessageDigest md = null;
        String hashText = "";
        try {
            md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < hashedPassword.length; i++){
                sb.append(Integer.toString((hashedPassword[i] & 0xff) + 0x100, 16).substring(1));
            }
            hashText = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashText;
    }
}
