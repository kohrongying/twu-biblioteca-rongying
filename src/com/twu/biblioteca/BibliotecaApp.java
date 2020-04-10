package com.twu.biblioteca;

import java.util.Scanner;

public class BibliotecaApp {
    private static String state = "WELCOME";

    private static Library initialiseLibrary() {
        Book book = new Book("TDD for Dummies", "TWU", 2000, 1, 1);
        Book book2 = new Book("The Agile Brown Fox Jumped Over the Waterfall", "Marty Howler", 2010, 1, 1);
        Book book3 = new Book("Pear Programming", "Fruity Peeler", 2012, 1, 1);
        return new Library(new Book[]{book, book2, book3});
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = initialiseLibrary();
        StateMachine fsm = new StateMachine(library);

        System.out.println(Messages.WELCOME.getMessage());
        System.out.println("You may type q or quit to quit the application at any time");
        fsm.getNextPrompt();

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("q") || userInput.equals("quit")) {
                break;
            } else {
                try {
                    int userInt = Integer.parseInt(userInput);
                    fsm.nextState(userInt);
                    fsm.getNextPrompt();
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        }


        scanner.close();
        System.out.println("Thank you for using Biblioteca! Have a nice day!");
    }
}
