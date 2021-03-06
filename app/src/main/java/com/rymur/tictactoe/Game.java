package com.rymur.tictactoe;

//import static android.view.ViewDebug.HierarchyTraceType.DRAW;
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
    private int oScore;
    private int xScore;
    private AI ai;

    public Game() {
        resetGameBoard();
        ai = new AI("X");  // TODO: Let player choose character
        oScore = 0;
        xScore = 0;
    }

    public void resetGameBoard() {
        for (int i = 0; i < board.length; i++) {
            board[i] = "-";
        }
    }

    public String getCurPlayer() {
        return curPlayer;
    }

    public void setCurPlayer(String player) { curPlayer = player; }

    public int getOScore() { return oScore; }

    public void setOScore(int score) { oScore = score; }

    public int getXScore() { return xScore; }

    public void setXScore(int score) { xScore = score; }

    public String[] getBoard() { return board; }

    public void setBoard(String[] array) { board = array.clone(); }



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
     * Selects an empty cell at random.
     * @return int - The cell selected by the AI.
     */
    public int markCellCPUEasy() {
        int move = ai.getRandomMove(board);
        board[move] = curPlayer;
        curPlayer = curPlayer.equals("O") ? "X" : "O";

        return move;
    }

    /**
     * Selects a move without looking ahead.
     * @return int - The cell selected by the AI.
     */
    public int markCellCPUMed() {
        int move = ai.getShortsightedMove(board);
        board[move] = curPlayer;
        curPlayer = curPlayer.equals("O") ? "X" : "O";

        return move;
    }

    /**
     * Selects the optimal cell to play next.
     * @return int - The cell selected by the AI
     */
    public int markCellCPUHard() {
        int move = ai.getNextMove(board);
        board[move] = curPlayer;
        curPlayer = curPlayer.equals("O") ? "X" : "O";

        return move;
    }

    /**
     * Determines whether the game has been won or not yet
     * @return GameState indicating whether game is still ongoing, drawn, or won and if so who won.
     */
    public GameState hasWon() {
        /* Check rows */
        for (int i = 0; i < board.length; i += 3) {
            if (board[i].equals(board[i + 1]) && board[i].equals(board[i + 2])) {
                if (!(board[i].equals("-"))) {
                    return findWinner(i);
                }
            }
        }

        /* Check columns */
        for (int i = 0; i < 3; i++) {
            if (board[i].equals(board[i + 3]) && board[i].equals(board[i + 6])) {
                if (!(board[i].equals("-"))) {
                    return findWinner(i);
                }
            }
        }

        /* Check diagnonals */
        if ((board[0].equals(board[4]) && board[0].equals(board[8]))
                || (board[2].equals(board[4]) && board[2].equals(board[6]))) {
            if (!(board[4].equals("-"))) {
                return findWinner(4);
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

    /**
     * Determines the winner from a given index and increases the score for the winner by 1.
     * @param index - The index of the array where the winner's character is located.
     * @return GameState
     */
    private GameState findWinner(int index) {
        if (board[index].equals("O")) {
            oScore++;
            return O_WON;
        } else if (board[index].equals("X")) {
            xScore++;
            return X_WON;
        } else {
            /* TODO: consider throwing exception or having this method control determination of all
            finished states
             */
            return UNFINISHED;
        }
    }
}
