package com.twu.biblioteca;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.Before;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class BibliotecaStateTest {

    @Test
    public void shouldDisplayWelcomeMessageGivenWelcomeState() {
        BibliotecaState state = BibliotecaState.Welcome;
        assertThat(state.message(), containsString("MENU"));
    }

    @Test
    public void shouldMoveToLibraryStateGivenWelcomeStateAndInput1() {
        BibliotecaState state = BibliotecaState.Welcome;
        state = state.nextState(1).getState();
        assertThat(state, is(BibliotecaState.Library));
    }

    @Test
    public void shouldStayAtWelcomeStateGivenWelcomeStateAndInvalidInput() {
        BibliotecaState state = BibliotecaState.Welcome;
        OutputState outState = state.nextState(3);
        assertThat(outState.getState(), is(BibliotecaState.Welcome));
        assertThat(outState.getMessage(), containsString(Messages.INVALID_OPTION.getMessage()));
    }

    @Test
    @Ignore
    public void shouldDisplayListOfBooksGivenLibraryState() {

    }

    @Test
    public void shouldMoveToBookCheckoutStateGivenLibraryStateAndInput1() {
        BibliotecaState state = BibliotecaState.Library;
        OutputState outState = state.nextState(1);
        assertThat(outState.getState(), is(BibliotecaState.BookCheckout));
    }

    @Test
    public void shouldMoveToBookReturnStateGivenLibraryStateAndInput2() {
        BibliotecaState state = BibliotecaState.Library;
        OutputState outState = state.nextState(2);
        assertThat(outState.getState(), is(BibliotecaState.BookReturn));
    }

    @Test
    public void shouldStayAtLibraryStateGivenLibraryStateAndInvalidInput() {
        BibliotecaState state = BibliotecaState.Library;
        OutputState outState = state.nextState(99);
        assertThat(outState.getState(), is(BibliotecaState.Library));
        assertThat(outState.getMessage(), containsString(Messages.INVALID_OPTION.getMessage()));
    }

    @Test
    @Ignore
    public void shouldMoveToLibraryStateGivenCheckoutStateAndSuccessfulCheckout() {
        BibliotecaState state = BibliotecaState.BookCheckout;
//        OutputState outState = state.nextState()

    }
}
