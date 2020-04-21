package com.twu.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private User user;

    @BeforeEach
    void setUp() {
        user = new User("Rong Ying", "rongying@biblioteca.in", "+91-123-456-789", "123-456789", "f04adb8ac810fd3ed1ba3c1869c97a13cd7889544bff0746ac6b14e7ee76282cd1296d8fae235c3ff3f38e7ea3d78a195b2c2e1b5e1a1b4b14ce08ad5a60d88d");
    }

    @Test
    void getLibraryID() {
        assertEquals("123-456789", user.getLibraryID());
    }

    @Test
    void returnTrueWhenPasswordIsCorrect() {
        assertTrue(user.verifyPassword("password"));
    }

    @Test
    void returnFalseWhenPasswordIsIncorrect() {
        assertFalse(user.verifyPassword("wrongPassword"));
    }
}
