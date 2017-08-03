package com.rymur.tictactoe;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by murrarc1 on 8/2/2017.
 */

public class AIUnitTest {
    /* Minimax will not necessarily select the center square so this test is pointless
    @Test
    public void AI_selects_center_square_at_start() {
        AI ai = new AI("O");
        String[] board = { "-", "-", "-",
                           "-", "-", "-",
                           "-", "-", "-" };
        assertEquals(4, ai.getNextMove(board));
    }
    */

    @Test
    public void AI_selects_obvious_winning_O_hor_move() {
        AI ai = new AI("O");
        String[] board = { "-", "-", "-",
                           "O", "O", "-",
                           "-", "-", "-" };
        assertEquals(5, ai.getNextMove(board));
    }

    @Test
    public void AI_selects_next_winning_O_hor_move() {
        AI ai = new AI("O");
        String[] board = { "O", "O", "-",
                           "X", "X", "-",
                           "-", "-", "-" };
        assertEquals(2, ai.getNextMove(board));
    }

    @Test
    public void AI_selects_next_winning_X_hor_move() {
        AI ai = new AI("X");
        String[] board = { "O", "O", "-",
                           "X", "X", "-",
                           "-", "-", "-" };
        assertEquals(5, ai.getNextMove(board));
    }

    @Test
    public void AI_selects_next_winning_O_col_move() {
        AI ai = new AI("O");
        String[] board = { "X", "O", "X",
                           "-", "O", "X",
                           "-", "-", "-" };
        assertEquals(7, ai.getNextMove(board));
    }

    @Test
    public void AI_selects_next_winning_X_col_move() {
        AI ai = new AI("X");
        String[] board = { "O", "X", "O",
                           "-", "X", "O",
                           "-", "-", "-" };
        assertEquals(7, ai.getNextMove(board));
    }

    @Test
    public void AI_O_blocks_X_hor_win() {
        AI ai = new AI("O");
        String[] board = { "O", "-", "-",
                           "X", "X", "-",
                           "O", "-", "-" };
        assertEquals(5, ai.getNextMove(board));
    }

    @Test
    public void AI_O_selects_right_diag_win() {
        AI ai = new AI("O");
        String[] board = { "O", "-", "-",
                           "X", "O", "X",
                           "O", "-", "-" };
        assertEquals(2, ai.getNextMove(board));
    }

    @Test
    public void AI_X_selects_left_diag_win() {
        AI ai = new AI("X");
        String[] board = { "X", "-", "-",
                           "O", "X", "O",
                           "O", "-", "-" };
        assertEquals(8, ai.getNextMove(board));
    }

    @Test
    public void AI_X_selects_move_that_will_win_in_2_moves() {
        AI ai = new AI("X");
        String[] board = {  "-", "O", "-",
                            "O", "X", "-",
                            "-", "X", "-" };
        assertEquals(6, ai.getNextMove(board));
    }
}
