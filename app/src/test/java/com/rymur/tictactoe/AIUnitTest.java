package com.rymur.tictactoe;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by murrarc1 on 8/2/2017.
 */

public class AIUnitTest {
    @Test
    public void horizontal_o_wins() {
        AI ai = new AI();

        String[] row1Win = { "O", "O", "O", "-", "-", "-", "-", "-", "-" };
        String[] row2Win = { "-", "-", "-", "O", "O", "O", "-", "-", "-" };
        String[] row3Win = { "-", "-", "-", "-", "-", "-", "O", "O", "O" };
        String[] randRow1 = { "O", "O", "O", "X", "-", "X", "-", "-", "-" };
        String[] randRow2 = { "X", "-", "-", "X", "X", "-", "O", "O", "O" };

        String[][] testBoards = { row1Win, row2Win, row3Win, randRow1, randRow2 };

        for (String[] board : testBoards) {
            assertEquals(ai.)
        }
    }
}
