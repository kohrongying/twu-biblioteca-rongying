package com.twu.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutputStateTest {
    private OutputState state;

    @BeforeEach
    public void setUp() {
        state = new OutputState(BibliotecaState.Welcome, "This is Output");
    }

    @Test
    public void shouldGetMessage() {
        assertEquals(state.getMessage(), "This is Output");
    }

    @Test
    public void shouldGetBibliotecaState() {
        assertEquals(state.getState(), BibliotecaState.Welcome);
    }
}
