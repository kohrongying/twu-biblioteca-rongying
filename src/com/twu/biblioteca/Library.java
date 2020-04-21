package com.twu.biblioteca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Library implements Serializable {
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
            throw new IllegalResourceCheckoutException();
        }
    }

    public void returnLoan(BookLoan loan) {
        if (loan.isOutstanding()) {
            loan.returnBook();
            System.out.println(Messages.BOOK_RETURN_SUCCESS.getMessage());
        } else {
            System.out.println(Messages.BOOK_RETURN_FAIL.getMessage());
            throw new IllegalResourceReturnException();
        }
    }

    public Book getAvailableBookByTitle(String bookTitle) {
        Book[] books = getAvailableBooks();
        for (Book book : books) {
            if (book.getBookTitle().equals(bookTitle)) {
                return book;
            }
        }
        return null;
    }

    public BookLoan getOutstandingLoanByBookTitle(String bookTitle) {
        for (int i = 0; i < loans.size(); i += 1) {
            BookLoan loan = loans.get(i);
            if (loan.getBook().getBookTitle().equals(bookTitle)) {
                return loan;
            }
        }
        return null;
    }
}