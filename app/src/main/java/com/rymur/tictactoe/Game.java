package com.rymur.tictactoe;

import static com.rymur.tictactoe.Game.GameState.DRAW;
import static com.rymur.tictactoe.Game.GameState.O_WON;
import static com.rymur.tictactoe.Game.GameState.UNFINISHED;
import static com.rymur.tictactoe.Game.GameState.X_WON;

/**
 * Created by Ryan on 7/30/2017.
 * The Game class controls the logic of playing the Tic Tac Toe game.
 */

public class Game {
    public enum GameState {
        X_WON, O_WON, DRAW, UNFINISHED;
    }
    private String curPlayer = "O";
    private String board[] = new String[9];

    public Game() {
        for (int i = 0; i < board.length; i++) {
                board[i] = "-";
        }
    }

    public String getCurPlayer() {
        return curPlayer;
    }

    /**
     * Attempts to mark the specified cell for the current player.
     * @param cellNum - The numerical ID of the cell to mark
     * @return true if successful, false otherwise
     */
    public boolean markCell(int cellNum) {
        if (board[cellNum].equals("-")) {
            board[cellNum] = curPlayer;
            curPlayer = curPlayer.equals("O") ? "X" : "O";
            return true;
        } else {
            return false;
        }
    }

    /**
     * Determines whether the game has been won or not yet
     * @return GameState indicating whether game is still ongoing, drawn, or won and if so who won.
     */
    public GameState hasWon() {
        /* Check rows */
        for (int i = 0; i < board.length; i += 3) {
            if (board[i].equals(board[i + 1]) && board[i].equals(board[i + 2])) {
                if (board[i].equals("O")) {
                    return O_WON;
                } else if (board[i].equals("X")) {
                    return X_WON;
                }
            }
        }

        /* Check columns */
        for (int i = 0; i < 3; i++) {
            if (board[i].equals(board[i + 3]) && board[i].equals(board[i + 6])) {
                if (board[i].equals("O")) {
                    return O_WON;
                } else if (board[i].equals("X")) {
                    return X_WON;
                }
            }
        }

        /* Check diagnonals */
        if ((board[0].equals(board[4]) && board[0].equals(board[8]))
                || (board[2].equals(board[4]) && board[0].equals(board[6]))) {
            if (board[4].equals("O")) {
                return O_WON;
            } else if (board[4].equals("X")) {
                return X_WON;
            }
        }

        /* Check for any untaken tiles */
        for (String cell : board) {
            if (cell.equals("-")) {
                return UNFINISHED;
            }
        }

        return DRAW;
    }
}
