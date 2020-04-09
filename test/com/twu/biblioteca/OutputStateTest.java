package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class OutputStateTest {
    private OutputState state;

    @Before
    public void setUp() {
        state = new OutputState(BibliotecaState.Welcome, "This is Output");
    }

    @Test
    public void shouldGetMessage() {
        assertThat(state.getMessage(), is("This is Output"));
    }

    @Test
    public void shouldGetBibliotecaState() {
        assertThat(state.getState(), is(BibliotecaState.Welcome));
    }
}
