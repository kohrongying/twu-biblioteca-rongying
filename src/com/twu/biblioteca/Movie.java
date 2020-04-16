package com.twu.biblioteca;

public class Movie extends Resource {
    private String title;
    private int yearReleased;
    private String directedBy;
    private int movieRating;

    public Movie(String title, int yearReleased, String directedBy, int movieRating, int numCopiesTotal, int numCopiesAvailable) {
        super(numCopiesTotal, numCopiesAvailable);
        this.title = title;
        this.yearReleased = yearReleased;
        this.directedBy = directedBy;
        this.movieRating = movieRating;
    }

    @Override
    public String toString() {
        String status = this.isAvailable() ? "Available" : "On Loan";
        String rating = this.movieRating == 0 ? "Unrated" : Integer.toString(this.movieRating);
        return String.format("%-" + 40 + "s", this.title)
                + String.format("%-" + 20 + "s", this.directedBy)
                + String.format("%-" + 9 + "s", this.yearReleased)
                + String.format("%-" + 5 + "s", rating)
                + status;
    }
}
