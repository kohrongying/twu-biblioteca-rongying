package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Library {
    private Book[] books;
    private List<BookLoan> loans = new ArrayList<BookLoan>();

    public Library(Book[] books) {
        this.books = books;
    }

    public Book[] getAvailableBooks() {
        return Arrays.stream(this.books)
                .filter(s -> s.isAvailable())
                .toArray(Book[]::new);
    }

    public BookLoan[] getOutstandingLoans() {
        return this.loans.stream()
                .filter(loan -> loan.isOutstanding())
                .toArray(BookLoan[]::new);
    }

    public BookLoan checkoutBook(Book book) {
        if (book.isAvailable()) {
            BookLoan loan = new BookLoan(book);
            this.loans.add(loan);
            System.out.println(Messages.BOOK_CHECKOUT_SUCCESS.getMessage());
            return loan;
        } else {
            System.out.println(Messages.BOOK_CHECKOUT_FAIL.getMessage());
            throw new IllegalBookCheckoutException(Messages.BOOK_CHECKOUT_FAIL.getMessage());
        }
    }

    public void returnLoan(BookLoan loan) {
        if (loan.isOutstanding()) {
            loan.returnBook();
            System.out.println(Messages.BOOK_RETURN_SUCCESS.getMessage());
        } else {
            System.out.println(Messages.BOOK_RETURN_FAIL.getMessage());
            throw new IllegalBookReturnException(Messages.BOOK_RETURN_FAIL.getMessage());
        }
    }
}
