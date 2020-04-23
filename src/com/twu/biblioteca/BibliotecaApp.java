package com.twu.biblioteca;

import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = Factory.createBookLibrary();
        Library movieLibrary = Factory.createMovieLibrary();
        User[] users = Factory.createUsers();

        StateMachine fsm = new StateMachine(library, movieLibrary, users);

        System.out.println(Messages.WELCOME.getMessage());
        System.out.println("You may type q or quit to quit the application at any time");
        fsm.getNextPrompt();

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("q") || userInput.equals("quit")) {
                break;
            } else {
                fsm.nextState(userInput);
                fsm.getNextPrompt();
            }
        }


        scanner.close();
        System.out.println(Messages.QUIT.getMessage());
    }
}
