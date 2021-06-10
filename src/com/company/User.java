package com.company;
import java.util.Scanner;

public class User extends Player {

    public Scanner inputScanner;

    public User(String[] moves) {
        super(moves);
        inputScanner = new Scanner(System.in);
    }

    public void printAvailableMoves() {
        System.out.print("Available moves:\n");
        for (int i = 0; i < availableMoves.size(); i++) {
            System.out.println(i+1 + " - " + availableMoves.get(i));
        }
        System.out.print("0 - exit\n");
    }

    public String validateMove() {
        String userInput = inputScanner.nextLine();
        if (availableMoves.contains(userInput)) {
            return userInput;
        } else if (userInput.matches("^-?\\d+$") && Integer.parseInt(userInput) == 0) {
            System.exit(0);
        } else if (userInput.matches("^-?\\d+$") && Integer.parseInt(userInput) <= availableMoves.size()) {
            return availableMoves.get((Integer.parseInt(userInput))-1);
        }
        System.out.print("\nChoose a possible move, please.\n");
        return getMove();
    }

    public String getMove() {
        printAvailableMoves();
        System.out.print("Enter your move: ");
        return validateMove();
    }

}
