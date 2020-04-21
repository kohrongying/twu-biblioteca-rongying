package com.twu.biblioteca;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

public class StateMachineTest {
    private StateMachine fsm;

    @BeforeEach
    void setUp() {
        Book book = new Book("TDD for Dummies", "TWU", 2000, 1, 1);
        Book book2 = new Book("The Agile Brown Fox Jumped Over the Waterfall", "Marty Howler", 2010, 1, 1);
        Book book3 = new Book("Pear Programming", "Fruity Peeler", 2012, 1, 0);
        Library library = new Library(new Book[]{book, book2, book3});
        fsm = new StateMachine(library);
    }

    @Test
    void shouldGetCurrentState() {
        assertEquals(StateMachine.State.WELCOME, fsm.getCurrentState());
    }

    @Test
    void shouldSetCurrentStateToLibrary() {
        fsm.setCurrentState(StateMachine.State.LIBRARY);
        assertEquals(StateMachine.State.LIBRARY, fsm.getCurrentState());
    }

    @Nested
    class GivenWelcomeState {
        @Test
        void shouldMoveToLibraryStateGivenInput1() {
            fsm.nextState(1);
            assertEquals(StateMachine.State.LIBRARY, fsm.getCurrentState());
        }

        @Test
        void shouldStayAtWelcomeStateGivenInvalidInput() {
            fsm.nextState(-99);
            assertEquals(StateMachine.State.WELCOME, fsm.getCurrentState());
        }
    }

    @Nested
    class GivenLibraryState {
        @BeforeEach
        void setUp() {
            fsm.setCurrentState(StateMachine.State.LIBRARY);
        }

        @Test
        void shouldMoveToBookCheckoutStateGivenInput1() {
            fsm.nextState(1);
            assertEquals(StateMachine.State.BOOK_CHECKOUT, fsm.getCurrentState());
        }

        @Test
        void shouldMoveToBooKReturnStateGivenInput2() {
            fsm.nextState(2);
            assertEquals(StateMachine.State.BOOK_RETURN, fsm.getCurrentState());
        }

        @Test
        void shouldStayAtLibraryStateGivenInvalidInput() {
            fsm.nextState(-99);
            assertEquals(StateMachine.State.LIBRARY, fsm.getCurrentState());
        }

        @Test
        void shouldMoveToWelcomeStateGivenInput0() {
            fsm.nextState(0);
            assertEquals(StateMachine.State.WELCOME, fsm.getCurrentState());
        }
    }

    @Nested
    class GivenBooKCheckoutState {
        @BeforeEach
        void setUp() {
            fsm.setCurrentState(StateMachine.State.BOOK_CHECKOUT);
        }

        @Test
        void shouldMoveToLibraryStateGivenSuccessfulCheckout() {
            fsm.nextState(1);
            assertEquals(StateMachine.State.LIBRARY, fsm.getCurrentState());
        }

        @Test
        void shouldStayAtBookCheckoutGivenUnsuccessfulCheckout() {
            fsm.nextState(-99);
            assertEquals(StateMachine.State.BOOK_CHECKOUT, fsm.getCurrentState());
        }

        @Test
        void shouldMoveToLibraryStateGivenInput0() {
            //Input zero is to go back
            fsm.nextState(0);
            assertEquals(StateMachine.State.LIBRARY, fsm.getCurrentState());
        }

        @Test
            // End to end test
        void shouldCreateBookLoanAfterSuccessfulCheckout() {
            Loanable[] books = fsm.getLibrary().getAvailableResources();
            fsm.nextState(1);
            Loan[] loans = fsm.getLibrary().getOutstandingLoans();
            assertEquals(1, loans.length);
            assertEquals(books[0], loans[0].getResource());
        }
    }

    @Nested
    class GivenBookReturnState {
        @BeforeEach
        void setUp() {
            fsm.setCurrentState(StateMachine.State.BOOK_CHECKOUT);
            fsm.nextState(1); // Borrows Book
            fsm.setCurrentState(StateMachine.State.BOOK_RETURN);
        }

        @Test
        void shouldMoveToLibraryStateGivenSuccessfulReturn() {
            fsm.nextState(1);
            assertEquals(StateMachine.State.LIBRARY, fsm.getCurrentState());

        }

        @Test
        void shouldStayAtBookReturnstateGivenUnsuccessfulReturn() {
            fsm.nextState(-99);
            assertEquals(StateMachine.State.BOOK_RETURN, fsm.getCurrentState());
        }

        @Test
        void shouldMoveToLibraryStateGivenInput0() {
            //Input zero is to go back
            fsm.nextState(0);
            assertEquals(StateMachine.State.LIBRARY, fsm.getCurrentState());
        }
    }
}
