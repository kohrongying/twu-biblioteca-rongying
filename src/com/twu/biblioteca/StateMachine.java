package com.twu.biblioteca;

public class StateMachine {
    private Library library;
    private State currentState;

    public StateMachine(Library library) {
        this.library = library;
        this.currentState = State.WELCOME;
    }

    public enum State {
        WELCOME, LIBRARY, BOOK_CHECKOUT, BOOK_RETURN, QUIT
    }

    public State getCurrentState() {
        return this.currentState;
    }

    public void setCurrentState(State newState) {
        this.currentState = newState;
    }

    public Library getLibrary() {
        return this.library;
    }

    public void nextState(int userInput) {
        switch(this.currentState) {
            case WELCOME:
                if (userInput == 1) {
                    this.currentState = State.LIBRARY;
                } else {
                    System.out.println(Messages.INVALID_OPTION.getMessage());
                    this.currentState  = State.WELCOME;
                }
                break;
            case LIBRARY:
                if (userInput == 1) {
                    this.currentState = State.BOOK_CHECKOUT;
                } else if (userInput == 2) {
                    this.currentState = State.BOOK_RETURN;
                } else {
                    System.out.println(Messages.INVALID_OPTION.getMessage());
                    this.currentState = State.LIBRARY;
                }
                break;
            case BOOK_CHECKOUT:
                Book[] books = this.library.getAvailableBooks();
                int bookIndex = userInput - 1;
                if (bookIndex >= 0 && bookIndex < books.length) {
                    Book book = books[bookIndex];
                    this.library.checkoutBook(book);
                    this.currentState = State.LIBRARY;
                } else {
                    System.out.println(Messages.BOOK_CHECKOUT_FAIL.getMessage());
                    this.currentState = State.BOOK_CHECKOUT;
                }
                break;
            case BOOK_RETURN:
                BookLoan[] loans = this.library.getOutstandingLoans();
                int loanIndex = userInput - 1;
                if (loanIndex >= 0 && loanIndex < loans.length) {
                    BookLoan loan = loans[loanIndex];
                    this.library.returnLoan(loan);
                    this.currentState = State.LIBRARY;
                } else {
                    System.out.println(Messages.BOOK_RETURN_FAIL.getMessage());
                    this.currentState = State.BOOK_RETURN;
                }
                break;
        }
    }

    public void getNextPrompt() {
        switch (this.currentState) {
            case WELCOME:
                System.out.println("\n"
                        + "====== MENU ======"
                        + "1. List of Books"
                        + "\n"
                        + "What would you like to do today? Please input the number eg. 1. ");
            case LIBRARY:
                System.out.println("\n"
                        + "===== LIST OF BOOKS ====="
                        + "//TODO"
                        + "What would you like to do? Please input the number eg. 1. "
                        + "\n"
                        + "1. Checkout Book"
                        + "2. Return Book");
            case BOOK_CHECKOUT:
                System.out.println("\n"
                        + "===== AVAILABLE BOOKS TO CHECK OUT ====="
                        + "//TODO"
                        + "Please input the number of the book you would like to borrow eg. 1. ");
            case BOOK_RETURN:
                System.out.println("\n"
                        + "===== OUTSTANDING LOANS ====="
                        + "//TODO"
                        + "Please input the number of the book you would like to return eg. 1. ");
        }
    }

}
