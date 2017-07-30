package com.rymur.tictactoe;

import static android.view.ViewDebug.HierarchyTraceType.DRAW;

/**
 * Created by Ryan on 7/30/2017.
 * The Game class controls the logic of playing the Tic Tac Toe game.
 */

public class Game {
    private enum GameState {
        X_WON, O_WON, DRAW, UNFINISHED;
    }
    private String curPlayer = "O";
    private String board[][] = new String[3][3];

    public Game() {
        for (String[] row : board) {
            for (String cell : row) {
                cell = "-";
            }
        }
    }

    /**
     * Attempts to mark the specified cell for the current player.
     * @param cellNum
     * @return true if successful, false otherwise
     */
    public boolean markCell(int cellNum) {

    }

    /**
     * Determines whether the game has been won or not yet
     * @return true if won, false otherwise
     */
    public boolean hasWon() {

    }
}
