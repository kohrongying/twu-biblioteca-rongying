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

        fsm = new StateMachine(library, Factory.createMovieLibrary(), Factory.createUsers());
    }

    @Test
    void shouldGetCurrentState() {
        assertEquals(StateMachine.State.LOGIN, fsm.getCurrentState());
    }

    @Test
    void shouldSetCurrentStateToLibrary() {
        fsm.setCurrentState(StateMachine.State.LIBRARY);
        assertEquals(StateMachine.State.LIBRARY, fsm.getCurrentState());
    }

    @Test
    void shouldFindUserByLibraryID() {
        assertNotNull(fsm.getUserByLibraryID("123-456789"));
        assertTrue(fsm.getUserByLibraryID("123-456789") instanceof User);
    }

    @Test
    void shouldReturnNullIfUnableToFindUser() {
        assertNull(fsm.getUserByLibraryID("456-789123"));
    }

    @Nested
    class GivenWelcomeState {
        @BeforeEach
        void setUp() {
            fsm.setCurrentState(StateMachine.State.WELCOME);
        }

        @Test
        void shouldMoveToLibraryStateGivenInput1() {
            fsm.nextState("1");
            assertEquals(StateMachine.State.LIBRARY, fsm.getCurrentState());
        }

        @Test
        void shouldSetCurrentResourceToBookGivenInput1() {
            fsm.nextState("1");
            assertEquals(StateMachine.ResourceType.BOOK, fsm.getCurrentResourceType());
        }

        @Test
        void shouldMoveToLibraryStateGivenInput2() {
            fsm.nextState("2");
            assertEquals(StateMachine.State.LIBRARY, fsm.getCurrentState());
        }

        @Test
        void shouldSetCurrentResourceToMovieGivenInput2() {
            fsm.nextState("2");
            assertEquals(StateMachine.ResourceType.MOVIE, fsm.getCurrentResourceType());
        }

        @Test
        void shouldStayAtWelcomeStateGivenInvalidInput() {
            fsm.nextState("-99");
            assertEquals(StateMachine.State.WELCOME, fsm.getCurrentState());
        }

        @Test
        void shouldMoveToUserInfoStateGivenInput3() {
            fsm.nextState("3");
            assertEquals(StateMachine.State.USER_INFORMATION, fsm.getCurrentState());
        }
    }

    @Nested
    class GivenLibraryState {
        @BeforeEach
        void setUp() {
            fsm.setCurrentState(StateMachine.State.LIBRARY);
        }

        @Test
        void shouldStayAtLibraryStateGivenInvalidInput() {
            fsm.nextState("-99");
            assertEquals(StateMachine.State.LIBRARY, fsm.getCurrentState());
        }

        @Test
        void shouldMoveToWelcomeStateGivenInput0() {
            fsm.nextState("0");
            assertEquals(StateMachine.State.WELCOME, fsm.getCurrentState());
        }
    }

    @Nested
    class GivenBookCheckoutState {
        @BeforeEach
        void setUp() {
            fsm.nextState("123-456789"); // login
            fsm.nextState("password"); // authenticate user
            fsm.setCurrentResourceType(StateMachine.ResourceType.BOOK);
            fsm.setCurrentState(StateMachine.State.RESOURCE_CHECKOUT);
        }

        @Test
        void shouldMoveToLibraryStateGivenSuccessfulCheckout() {
            fsm.nextState("1");
            assertEquals(StateMachine.State.LIBRARY, fsm.getCurrentState());
        }

        @Test
        void shouldStayAtBookCheckoutGivenUnsuccessfulCheckout() {
            fsm.nextState("-99");
            assertEquals(StateMachine.State.RESOURCE_CHECKOUT, fsm.getCurrentState());
        }

        @Test
        void shouldMoveToLibraryStateGivenInput0() {
            //Input zero is to go back
            fsm.nextState("0");
            assertEquals(StateMachine.State.LIBRARY, fsm.getCurrentState());
        }

        @Test
            // End to end test
        void shouldCreateBookLoanAfterSuccessfulCheckout() {
            Loanable[] books = fsm.getLibrary().getAvailableResources();
            fsm.nextState("1");
            Loan[] loans = fsm.getLibrary().getOutstandingLoans();
            assertEquals(1, loans.length);
            assertEquals(books[0], loans[0].getResource());
        }
    }

    @Nested
    class GivenBookReturnState {
        @BeforeEach
        void setUp() {
            fsm.nextState("123-456789"); // login
            fsm.nextState("password"); // authenticate user
            fsm.setCurrentState(StateMachine.State.RESOURCE_CHECKOUT);
            fsm.nextState("1"); // Borrows Book
            fsm.setCurrentState(StateMachine.State.RESOURCE_RETURN);
        }

        @Test
        void shouldMoveToLibraryStateGivenSuccessfulReturn() {
            fsm.nextState("1");
            assertEquals(StateMachine.State.LIBRARY, fsm.getCurrentState());

        }

        @Test
        void shouldStayAtBookReturnstateGivenUnsuccessfulReturn() {
            fsm.nextState("-99");
            assertEquals(StateMachine.State.RESOURCE_RETURN, fsm.getCurrentState());
        }

        @Test
        void shouldMoveToLibraryStateGivenInput0() {
            //Input zero is to go back
            fsm.nextState("0");
            assertEquals(StateMachine.State.LIBRARY, fsm.getCurrentState());
        }
    }
    
    @Nested
    class GivenUserLoggingIn {
        @BeforeEach
        void setUp() {
            fsm.setCurrentState(StateMachine.State.LOGIN);
        }

        @Test
        void shouldMoveToPasswordStateGivenLibraryID() {
            fsm.nextState("123-456789");
            assertEquals(StateMachine.State.PASSWORD_INPUT, fsm.getCurrentState());
        }

        @Test
        void shouldMoveToWelcomeStateGivenCorrectPassword() {
            fsm.nextState("123-456789");
            fsm.nextState("password");
            assertEquals(StateMachine.State.WELCOME, fsm.getCurrentState());
        }

        @Test
        void shouldSetLoggedInUserGivenCorrectPassword() {
            fsm.nextState("123-456789");
            fsm.nextState("password");
            assertNotNull(fsm.getLoggedInUser());
        }

        @Test
        void shouldMoveToLoginStateGivenIncorrectPassword() {
            fsm.nextState("123-456789");
            fsm.nextState("incorrectPassword");
            assertEquals(StateMachine.State.LOGIN, fsm.getCurrentState());
        }

        @Test
        void shouldRemoveCurrentUserGivenIncorrectPassword() {
            fsm.nextState("123-456789");
            fsm.nextState("incorrectPassword");
            assertNull(fsm.getLoggedInUser());
        }
    }

    @Nested
    class GivenUserInformationState {
        @BeforeEach
        void setUp() {
            fsm.setCurrentState(StateMachine.State.USER_INFORMATION);
        }

        @Test
        void shouldMoveToWelcomeGivenValidOption() {
            fsm.nextState("0");
            assertEquals(StateMachine.State.WELCOME, fsm.getCurrentState());
        }

        @Test
        void shouldMoveToWelcomeGivenAnyOption() {
            fsm.nextState("-99");
            assertEquals(StateMachine.State.WELCOME, fsm.getCurrentState());
        }
    }
}
