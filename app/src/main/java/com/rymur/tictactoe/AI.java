package com.rymur.tictactoe;

import java.util.Arrays;

/**
 * This class handles the AI for the computer opponent in a one player game.
 * Created by Ryan Murray on 8/2/2017.
 */

public class AI {
    private String aiPlayer;

    public AI(String playerChar) {
        aiPlayer = playerChar;
    }

    /**
     * Returns the cell number of the move the AI has decided to make.
     * @param board - The current state of the game board.
     * @return int - The cell number the AI wants to mark.
     */
    public int getNextMove(String[] board) {
        int bestScore;
        if (aiPlayer.equals("O")) {
            bestScore = Integer.MIN_VALUE;
        } else {
            bestScore = Integer.MAX_VALUE;
        }
        int bestMove = -1;
        int depth = 9;

        /* Adjust depth to match the number of taken cells */
        for (int i = 0; i < 9; i++) {
            if (isCellEmpty(board, i)) {
                depth--;
            }
        }

        /* For every empty cell in the board evaluate making a move there */
        for (int i = 0; i < 9; i++) {
            if (isCellEmpty(board, i)) {
                String[] possibleMove = Arrays.copyOf(board, 9);
                possibleMove[i] = aiPlayer;
                int possibleMoveScore = minimax(depth + 1, possibleMove, (!aiPlayer.equals("O")));
                if (aiPlayer.equals("O")) {
                    if (possibleMoveScore > bestScore) {
                        bestScore = possibleMoveScore;
                        bestMove = i;
                    }
                } else {
                    if (possibleMoveScore < bestScore) {
                        bestScore = possibleMoveScore;
                        bestMove = i;
                    }
                }
            }
        }

        return bestMove;
    }

    /**
     * Implements the minimax algorithm for Tic Tac Toe
     * @param depth - The number of occupied cells
     * @param board - The current game board
     * @param isPlayerO - Indicates whether it is Player O's move or not
     * @return int - The best score that can be obtained from the board
     */
    private int minimax(int depth, String[] board, boolean isPlayerO) {
        int boardScore = evaluateBoard(board);
        if (depth == 9 || boardScore == -10 || boardScore == 10) {
            return boardScore;
        }

        /* NOTE: bestScore is returned +/- 1 to take into account how long it will take to reach
           an end state.
           Higher (+) scores indicate a faster O-win
           Lower (-) scores indicate a faster X-win
         */
        if (isPlayerO) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                if (isCellEmpty(board, i)) {
                    String[] newChild = Arrays.copyOf(board, 9);
                    newChild[i] = "O";
                    bestScore = Math.max(bestScore, minimax(depth + 1, newChild, !isPlayerO));
                }
            }
            return bestScore - 1;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 9; i++) {
                if (isCellEmpty(board, i)) {
                    String[] newChild = Arrays.copyOf(board, 9);
                    newChild[i] = "X";
                    bestScore = Math.min(bestScore, minimax(depth + 1, newChild, !isPlayerO));
                }
            }
            return bestScore + 1;
        }
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

    /**
     * Indicates whether a cell is unoccupied or not
     * @param board - The game board
     * @param cell - The index of the cell to test
     * @return bool - True if cell is unoccupied, false otherwise
     */
    private boolean isCellEmpty(String[] board, int cell) {
        return board[cell].equals("-");
    }
}
