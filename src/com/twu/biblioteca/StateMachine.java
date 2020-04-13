package com.twu.biblioteca;
import java.util.Arrays;

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
                } else if (userInput == 0) {
                    this.currentState = State.WELCOME;
                } else {
                    System.out.println(Messages.INVALID_OPTION.getMessage());
                    this.currentState = State.LIBRARY;
                }
                break;
            case BOOK_CHECKOUT:
                if (userInput == 0) {
                    this.currentState = State.LIBRARY;
                    break;
                }
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
                if (userInput == 0) {
                    this.currentState = State.LIBRARY;
                    break;
                }
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
        StringBuilder strBuilder = new StringBuilder("");
        Book[] availableBooks;
        PromptBuilder pbuilder;

        switch (this.currentState) {
            case WELCOME:
                pbuilder = new PromptBuilder("Menu");
                pbuilder.setMenu(new String[]{"1. List Of Books"});
                pbuilder.setActionMenu(new String[]{"Please input the menu option number"});
                break;
            case LIBRARY:
                pbuilder = new PromptBuilder("List of Books");
                pbuilder.setBooks(this.library.getAvailableBooks());
                pbuilder.setActionMenu(new String[]{
                        "0 - GO BACK",
                        "1 - CHECKOUT BOOK",
                        "2 - RETURN BOOK"
                });
                break;
            case BOOK_CHECKOUT:
                pbuilder = new PromptBuilder("Available books to check out");
                pbuilder.setBooks(this.library.getAvailableBooks());
                pbuilder.setActionMenu(new String[]{
                        "0 - GO BACK",
                        "Please input the number of the book you would like to borrow eg. 1"
                });
                break;
            case BOOK_RETURN:
                pbuilder = new PromptBuilder("Outstanding Loans");
                BookLoan[] loans = this.library.getOutstandingLoans();
                Book[] booksOnLoan = Arrays.stream(loans)
                        .map(loan -> loan.getBook()).toArray(Book[]::new);
                pbuilder.setBooks(booksOnLoan);
                pbuilder.setActionMenu(new String[]{
                        "0 - GO BACK",
                        "Please input the number of the book you would like to return eg. 1"
                });
                break;
            default:
                pbuilder = new PromptBuilder("Goodbye");
        }
        System.out.println(pbuilder.toString());
    }
}
