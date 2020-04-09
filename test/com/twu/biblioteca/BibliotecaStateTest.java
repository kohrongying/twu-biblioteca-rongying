package com.twu.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

public class BibliotecaStateTest {

    @Nested
    class GivenWelcomeState {
        private BibliotecaState state;

        @BeforeEach
        void setUp() {
            state = BibliotecaState.Welcome;
        }

        @Test
        public void shouldDisplayWelcomeMessage() {
            assertTrue(state.message().contains("MENU"));
        }

        @Test
        public void shouldMoveToLibraryStateGivenInput1() {
            state = state.nextState(1).getState();
            assertEquals(state, BibliotecaState.Library);
        }

        @Test
        public void shouldStayAtWelcomeStateGivenInvalidInput() {
            OutputState outState = state.nextState(3);
            assertEquals(outState.getState(), BibliotecaState.Welcome);
            assertTrue(outState.getMessage().contains(Messages.INVALID_OPTION.getMessage()));
        }
    }

    @Nested
    class GivenLibraryState {
        private BibliotecaState state;

        @BeforeEach
        void setUp() {
            state = BibliotecaState.Library;
        }

        @Test
        @Disabled
        public void shouldDisplayListOfBooks() {

        }

        @Test
        public void shouldMoveToBookCheckoutStateGivenInput1() {
            OutputState outState = state.nextState(1);
            assertEquals(outState.getState(), BibliotecaState.BookCheckout);
        }

        @Test
        public void shouldMoveToBookReturnStateGivenInput2() {
            OutputState outState = state.nextState(2);
            assertEquals(outState.getState(), BibliotecaState.BookReturn);
        }

        @Test
        void shouldStayAtLibraryStateGivenInvalidInput() {
            OutputState outState = state.nextState(99);
            assertEquals(outState.getState(), BibliotecaState.Library);
            assertTrue(outState.getMessage().contains(Messages.INVALID_OPTION.getMessage()));
        }
    }


    @Test
    public void shouldStayAtLibraryStateGivenLibraryStateAndInvalidInput() {
        BibliotecaState state = BibliotecaState.Library;
    }

    @Test
    @Disabled
    public void shouldMoveToLibraryStateGivenCheckoutStateAndSuccessfulCheckout() {
        BibliotecaState state = BibliotecaState.BookCheckout;
//        OutputState outState = state.nextState()

    }
}
