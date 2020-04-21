package com.twu.biblioteca;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordHashTest {

    @Test
    void generateHash() {
        PasswordHash hash = new PasswordHash();
        assertEquals("f04adb8ac810fd3ed1ba3c1869c97a13cd7889544bff0746ac6b14e7ee76282cd1296d8fae235c3ff3f38e7ea3d78a195b2c2e1b5e1a1b4b14ce08ad5a60d88d", hash.generateHash("password"));
    }
}
