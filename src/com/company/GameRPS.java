package com.company;
import java.util.HashSet;

public class GameRPS {

    private User user;
    private Computer computer;
    public static String[] moves;

    public GameRPS() {
        user = new User(moves);
        computer = new Computer(moves);
    }

    public static void validateMovesNumber(String[] args) {
        if (args.length < 3 || args.length % 2 == 0) {
            System.out.print("The number of moves must be unpaired and not less than 3\n" +
                             "For example: rock, paper, scissors\n");
            System.exit(0);
        }
    }

    public static void checkDuplicateItems(String[] moves) {
        HashSet<String> set = new HashSet<>(moves.length);
        for (int i = 0; i < moves.length; i++) {
            if (set.contains(moves[i])) {
                System.out.println("Move names must not be repeated");
                System.exit(0);
            }
            set.add(moves[i]);
        }
    }

    public static void getAvailableMoves(String[] args) {
        moves = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            moves[i] = args[i].trim();
        }
    }

    public void playGame() {
        String computerMove = computer.getMove();
        computer.printHmac();
        String userMove = user.getMove();
        System.out.println("Your move: " + userMove);
        System.out.println("Computer move: " + computerMove);
        System.out.println(computer.getWinner(userMove));
        computer.printHmacKey();
    }

    public static void main(String[] args) {
        validateMovesNumber(args);
        getAvailableMoves(args);
        checkDuplicateItems(moves);
        GameRPS game = new GameRPS();
        game.playGame();
    }

}
