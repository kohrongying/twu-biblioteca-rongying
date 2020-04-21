package com.twu.biblioteca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Library implements Serializable {
    private Loanable[] resources;
    private List<Loan> loans = new ArrayList<Loan>();

    public Library(Loanable[] resources) {
        this.resources = resources;
    }

    public Loanable[] getAvailableResources() {
        return Arrays.stream(this.resources)
                .filter(s -> s.isAvailable())
                .toArray(Loanable[]::new);
    }

    public Loan[] getOutstandingLoans() {
        return this.loans.stream()
                .filter(loan -> loan.isOutstanding())
                .toArray(Loan[]::new);
    }

    public Loan checkoutResource(Loanable resource) {
        if (resource.isAvailable()) {
            Loan loan = new Loan(resource);
            this.loans.add(loan);
            System.out.println(Messages.BOOK_CHECKOUT_SUCCESS.getMessage());
            return loan;
        } else {
            System.out.println(Messages.BOOK_CHECKOUT_FAIL.getMessage());
            throw new IllegalResourceCheckoutException();
        }
    }

    public void returnLoan(Loan loan) {
        if (loan.isOutstanding()) {
            loan.returnResource();
            System.out.println(Messages.BOOK_RETURN_SUCCESS.getMessage());
        } else {
            System.out.println(Messages.BOOK_RETURN_FAIL.getMessage());
            throw new IllegalResourceReturnException();
        }
    }

    public Loanable getAvailableResourceByTitle(String title) {
        Loanable[] resources = getAvailableResources();
        for (Loanable res : resources) {
            if (res.getTitle().equals(title)) {
                return res;
            }
        }
        return null;
    }

    public Loan getOutstandingLoanByTitle(String title) {
        for (int i = 0; i < loans.size(); i += 1) {
            Loan loan = loans.get(i);
            if (loan.getResource().getTitle().equals(title)) {
                return loan;
            }
        }
        return null;
    }
}
