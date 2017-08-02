package com.rymur.tictactoe;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class GameUnitTest {
    @Test
    public void markCell_can_mark_unoccupied_cell() throws Exception {
        Game game = new Game();
        assertTrue(game.markCell(4));
    }
    @Test
    public void markCell_cannot_mark_occupied_cell() throws Exception {
        Game game = new Game();
        game.markCell(1);
        assertFalse(game.markCell(1));
    }

    @Test
    public void markCell_switches_O_to_X_upon_success() throws Exception {
        Game game = new Game();
        String O = game.getCurPlayer();
        game.markCell(0);
        String X = game.getCurPlayer();
        assertNotEquals(O, X);
    }

    @Test
    public void markCell_switches_X_to_O_upon_success() throws Exception {
        Game game = new Game();
        game.markCell(0);
        String X = game.getCurPlayer();
        game.markCell(1);
        String O = game.getCurPlayer();
        assertNotEquals(X, O);
    }

    @Test
    public void markCell_does_not_switch_player_upon_fail() throws Exception {
        Game game = new Game();
        game.markCell(0);
        String before = game.getCurPlayer();
        game.markCell(0);
        String after = game.getCurPlayer();
        assertEquals(before, after);
    }
}