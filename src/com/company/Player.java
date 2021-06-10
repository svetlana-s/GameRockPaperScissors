package com.company;
import java.util.ArrayList;
import java.util.Collections;

public abstract class Player {

    public ArrayList<String> availableMoves;

    public Player(String[] moves) {
        availableMoves = new ArrayList<>();
        Collections.addAll(availableMoves, moves);
    }

    public abstract String getMove();
}
