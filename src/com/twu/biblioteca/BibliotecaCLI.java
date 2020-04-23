package com.twu.biblioteca;

import org.apache.commons.cli.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BibliotecaCLI {
    private static Library library;

    public static void main(String[] args) throws IOException {

        // Definition Stage
        Options options = new Options();
        Option menu = Option.builder("m")
                            .desc("Shows list of menu options")
                            .optionalArg(true)
                            .numberOfArgs(1)
                            .build();
        options.addOption(menu);
        options.addOption("h", false, "Shows welcome and help page");
        options.addOption("cb",true, "Checkout a book");
        options.addOption("rb",true, "Return a book");
        options.addOption("q",false, "Quit application");

        try {
            // Initialise Library object
            readObject();

            // Parsing Stage
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);
            HelpFormatter formatter = new HelpFormatter();

            // Interrogation Stage
            // Show menu
            if (cmd.hasOption("m")) {
                String arg = cmd.getOptionValue("m");
                if (arg == null) {
                    System.out.println("1. List of Books");
                } else if (arg.equals("1")) {
                    Loanable[] books = library.getAvailableResources();
                    for (Loanable b : books) {
                        System.out.println(b.toString());
                    }
                } else {
                    System.out.println(Messages.INVALID_OPTION.getMessage());
                }
            }
            // Checkout book
            else if (cmd.hasOption("cb")) {
                String bookTitle = cmd.getOptionValue("cb");
                Loanable book = library.getAvailableResourceByTitle(bookTitle);
                if (book != null) {
                    library.checkoutResource(book);
                    writeObject();
                } else {
                    System.out.println(Messages.RESOURCE_CHECKOUT_FAIL.getMessage());
                }
            }
            // Return book
            else if (cmd.hasOption("rb")) {
                String bookTitle = cmd.getOptionValue("rb");
                Loan loan = library.getOutstandingLoanByTitle(bookTitle);
                if (loan != null) {
                    library.returnLoan(loan);
                    writeObject();
                } else {
                    System.out.println(Messages.RESOURCE_RETURN_FAIL.getMessage());
                }
            }
            // Help
            else if (cmd.hasOption("h")) {
                formatter.printHelp("biblioteca", options);
            }
            // Quit
            else if(cmd.hasOption("q")) {
                initializeObject();
                System.out.println(Messages.QUIT.getMessage());
            }
            // Welcome Message
            else {
                System.out.println(Messages.WELCOME.getMessage());
                formatter.printHelp("biblioteca", options);
            }


        } catch (FileNotFoundException e) {
            System.out.println("ERR: File not found");
        } catch (IOException e) {
            System.out.println("ERR: Error initializing stream");
        } catch (MissingArgumentException e) {
            System.out.println("ERR: Please include an argument");
        } catch (ParseException e) {
            System.out.println("ERR: Error with parsing");
        }
    }

    public static void writeObject() throws IOException {
        FileOutputStream f = new FileOutputStream(new File("library.txt"));
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(library);
        o.close();
        f.close();
    }

    public static void readObject() throws IOException {
        try {
            FileInputStream fi = new FileInputStream(new File("library.txt"));
            ObjectInputStream oi = new ObjectInputStream(fi);
            library = (Library) oi.readObject();
            oi.close();
            fi.close();
        } catch (FileNotFoundException e) {
            initializeObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void initializeObject() throws IOException {
        library = Factory.createBookLibrary();
        writeObject();
    }
}
