package com.twu.biblioteca;

import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = Factory.createBookLibrary();
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
        System.out.println(Messages.QUIT.getMessage());
    }
}
