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

        switch (this.currentState) {
            case WELCOME:
                strBuilder.append("\n"
                        + "====== MENU ======\n"
                        + "1. List of Books"
                        + "\n\n"
                        + "What would you like to do today?");
                break;
            case LIBRARY:
                availableBooks = this.library.getAvailableBooks();
                if (availableBooks.length > 0) {
                    for (int i = 0; i < availableBooks.length; i += 1) {
                        strBuilder.append(i + 1);
                        strBuilder.append(". ");
                        strBuilder.append(availableBooks[i].toString());
                        strBuilder.append("\n");
                    }
                } else {
                    strBuilder.append("There are no available books at the moment");
                }
                strBuilder.append("\n"
                        + "What would you like to do?\n"
                        + "0 - GO BACK\n"
                        + "1 - CHECKOUT BOOK\n"
                        + "2 - RETURN BOOK");

                break;
            case BOOK_CHECKOUT:
                strBuilder.append("\n"
                        + "===== AVAILABLE BOOKS TO CHECK OUT ====="
                        + "\n");
                availableBooks = this.library.getAvailableBooks();
                if (availableBooks.length > 0) {
                    for (int i = 0; i < availableBooks.length; i += 1) {
                        strBuilder.append(i + 1);
                        strBuilder.append(". ");
                        strBuilder.append(availableBooks[i].toString());
                        strBuilder.append("\n");
                    }
                    strBuilder.append("\n"
                            + "Please input the number of the book you would like to borrow eg. 1 ");
                } else {
                    strBuilder.append("There are no available books at the moment");
                }
                break;
            case BOOK_RETURN:
                strBuilder.append("\n"
                        + "===== OUTSTANDING LOANS ====="
                        + "\n");
                BookLoan[] loans = this.library.getOutstandingLoans();
                if (loans.length > 0) {
                    for (int i = 0; i < loans.length; i += 1) {
                        strBuilder.append(i + 1);
                        strBuilder.append(". ");
                        strBuilder.append(loans[i].getBook().toString());
                        strBuilder.append("\n");
                    }
                    strBuilder.append("\n"
                            + "Please input the number of the book you would like to return eg. 1 ");
                } else {
                    strBuilder.append("There are no outstanding loans");
                }
                break;
        }
        System.out.println(strBuilder);
        System.out.print("\nPlease input option number: ");
    }
}
