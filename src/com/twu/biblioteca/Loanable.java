package com.twu.biblioteca;

public interface Loanable {

    public String getTitle();

    public void incrementCopiesAvailable();

    public void decrementCopiesAvailable();

    public Boolean isAvailable();
}
