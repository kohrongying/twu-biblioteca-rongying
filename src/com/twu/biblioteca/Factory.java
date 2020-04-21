package com.twu.biblioteca;

public class Factory {

    public static Library createBookLibrary() {
        Book book = new Book("TDD for Dummies", "TWU", 2000, 1, 1);
        Book book2 = new Book("The Agile Brown Fox Jumped Over the Waterfall", "Marty Howler", 2010, 1, 1);
        Book book3 = new Book("Pear Programming", "Fruity Peeler", 2012, 1, 0);
        Book[] books = new Book[]{book, book2, book3};
        return new Library(books);
    }

    public static Library createMovieLibrary() {
        Movie movie = new Movie("Harry Potty and The Travelling Bat", 2020, "Steven Eysberg", 4, 5, 5);
        Movie movie2 = new Movie("Captain Singapore and the Lightning Thief", 2009, "Lee Kuching", 0, 1, 1);
        Movie movie3 = new Movie("MAWA Man Fights Back", 2020, "Band of Doodlers", 8, 1, 1);
        Movie[] movies = new Movie[]{movie, movie2, movie3};
        return new Library(movies);
    }

    public static User[] createUsers() {
        User user = new User("Rong Ying", "rongying@biblioteca.in", "+91-123-456-789", "123-456789", "f04adb8ac810fd3ed1ba3c1869c97a13cd7889544bff0746ac6b14e7ee76282cd1296d8fae235c3ff3f38e7ea3d78a195b2c2e1b5e1a1b4b14ce08ad5a60d88d");
        User user2 = new User("Turtwig", "turtwig@biblioteca.in", "+91-123-456-789", "987-654321", "f04adb8ac810fd3ed1ba3c1869c97a13cd7889544bff0746ac6b14e7ee76282cd1296d8fae235c3ff3f38e7ea3d78a195b2c2e1b5e1a1b4b14ce08ad5a60d88d");
        return new User[]{user, user2};
    }
}
