package com.rymur.tictactoe;

/**
 * This class handles the AI for the computer opponent in a one player game.
 * Created by Ryan Murray on 8/2/2017.
 */

public class AI {
    public AI() {}

    /**
     * Returns the cell number of the move the AI has decided to make.
     * @param board - The current state of the game board.
     * @return int - The cell number the AI wants to mark.
     */
    public int getNextMove(String[] board) {
        // TODO
        return 0;
    }

    private int minimax() {
        // TODO
        return 0;
    }

    /**
     * Determines whether a board is a good move for O, X, or both.
     * @param board - The state of the game board to start from.
     * @return int - The score of the board. +10 if good for O, -10 if good for X, 0 if draw.
     */
    private int evaluateBoard(String[] board) {
        /* Check rows */
        for (int i = 0; i < board.length; i += 3) {
            if (board[i].equals(board[i + 1]) && board[i].equals(board[i + 2])) {
                if (board[i].equals("O")) {
                    return 10;
                } else if (board[i].equals("X")) {
                    return -10;
                }
            }
        }

        /* Check columns */
        for (int i = 0; i < 3; i++) {
            if (board[i].equals(board[i + 3]) && board[i].equals(board[i + 6])) {
                if (board[i].equals("O")) {
                    return 10;
                } else if (board[i].equals("X")) {
                    return -10;
                }
            }
        }

        /* Check diagonals */
        if ((board[0].equals(board[4]) && board[0].equals(board[8]))
                || (board[2].equals(board[4]) && board[2].equals(board[6]))) {
            if (board[4].equals("O")) {
                return 10;
            } else if (board[4].equals("X")) {
                return -10;
            }
        }

        return 0;
    }
}
