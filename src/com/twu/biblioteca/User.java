package com.twu.biblioteca;

public class User {
    private String name;
    private String email;
    private String phoneNumber;
    private String libraryID;
    private String passwordHash;

    public User(String name, String email, String phoneNumber, String libraryID, String passwordHash) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.libraryID = libraryID;
        this.passwordHash = passwordHash;
    }

    public String getLibraryID() {
        return this.libraryID;
    }

    public Boolean verifyPassword(String password) {
        String generatedHash = new PasswordHash().generateHash(password);
        return this.passwordHash.equals(generatedHash);
    }

    @Override
    public String toString() {
        return String.format("%-" + 10 + "s", "Name:") + this.name + "\n"
                + String.format("%-" + 10 + "s", "Email:") + this.email + "\n"
                + String.format("%-" + 10 + "s", "Phone:") + this.phoneNumber;
    }
}
