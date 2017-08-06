package com.rymur.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.security.InvalidParameterException;

public class PlayGameActivity extends AppCompatActivity {
    Game game;
    private String mode;
    private static final String CUR_PLAYER = "curPlayer";
    private static final String OSCORE = "oScore";
    private static final String XSCORE = "xScore";
    private static final String GAME_BOARD = "gameBoard";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        Intent intent = getIntent();
        mode = intent.getStringExtra("com.rymur.tictactoe.MODE");

        game = new Game();
        if (savedInstanceState != null) {
            /* Restore game data */
            game.setCurPlayer(savedInstanceState.getString(CUR_PLAYER));
            game.setOScore(savedInstanceState.getInt(OSCORE));
            game.setXScore(savedInstanceState.getInt(XSCORE));
            String[] oldBoard = savedInstanceState.getStringArray(GAME_BOARD);
            game.setBoard(oldBoard);

            /* Restore view data */
            for (int i = 0; i < 9; i++) {
                if (!oldBoard[i].equals("-")) {
                    Button btn = getButtonFromIntId(i);
                    btn.setClickable(false);
                    btn.setText(oldBoard[i]);
                }
            }
            TextView tvOScore = (TextView) findViewById(R.id.oScore);
            tvOScore.setText(Integer.toString(savedInstanceState.getInt(OSCORE)));
            TextView tvXScore = (TextView) findViewById(R.id.xScore);
            tvXScore.setText(Integer.toString(savedInstanceState.getInt(XSCORE)));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(CUR_PLAYER, game.getCurPlayer());
        savedInstanceState.putInt(OSCORE, game.getOScore());
        savedInstanceState.putInt(XSCORE, game.getXScore());
        savedInstanceState.putStringArray(GAME_BOARD, game.getBoard());
    }

    /**
     * Sets the button to the current player if button has not already been claimed
     * @param view - The pressed button
     */
    public void onBtnClick(View view) {
        String curPlayer = game.getCurPlayer();
        Button button = (Button) view;
        int btnId = -1;

        switch(button.getId()) {
            case (R.id.btn0):
                btnId = 0;
                break;
            case (R.id.btn1):
                btnId = 1;
                break;
            case (R.id.btn2):
                btnId = 2;
                break;
            case (R.id.btn3):
                btnId = 3;
                break;
            case (R.id.btn4):
                btnId = 4;
                break;
            case (R.id.btn5):
                btnId = 5;
                break;
            case (R.id.btn6):
                btnId = 6;
                break;
            case (R.id.btn7):
                btnId = 7;
                break;
            case (R.id.btn8):
                btnId = 8;
                break;
            default:
                throw new InvalidParameterException("Button ID not recognized");
        }

        String btnValue = button.getText().toString();
        if (game.markCell(btnId)) {
            button.setText(curPlayer);
            button.setClickable(false);
            handleGameState();
        }

        if (!mode.equals("human")) {
            int cpuChoice;
            switch (mode) {
                case "easy":
                    cpuChoice = game.markCellCPUEasy();
                    break;
                case "medium":
                    cpuChoice = game.markCellCPUMed();
                    break;
                case "hard":
                    cpuChoice = game.markCellCPUHard();
                    break;
                default:
                    throw new InvalidParameterException("mode not recognized");
            }
            makeCPUMove(cpuChoice);
            handleGameState();
        }
    }

    private void makeCPUMove(int cell) {
        Button btn = getButtonFromIntId(cell);
        btn.setText("X"); // TODO: Let player choose character
        btn.setClickable(false);
    }

    /**
     * Determines whether the game has ended and updates the UI if it has.
     */
    private void handleGameState() {
        Game.GameState state = game.hasWon();

        switch (state) {
            case DRAW:
                // Pop up a dialog informing user of draw
                Toast.makeText(getApplicationContext(), "GAME DRAW", Toast.LENGTH_LONG).show();
                resetBoard();
                break;
            case O_WON:
                // Increase O score
                TextView tvOScore = (TextView) findViewById(R.id.oScore);
                tvOScore.setText(Integer.toString(game.getOScore()));
                // Pop up dialog for new game
                Toast.makeText(getApplicationContext(), "PLAYER O WINS", Toast.LENGTH_LONG).show();
                resetBoard();
                break;
            case X_WON:
                // Increase X score
                TextView tvXScore = (TextView) findViewById(R.id.xScore);
                tvXScore.setText(Integer.toString(game.getXScore()));
                // Pop up dialog for new game
                Toast.makeText(getApplicationContext(), "PLAYER X WINS", Toast.LENGTH_LONG).show();
                resetBoard();
                break;
            default:
                // Unfinished state
                // Do nothing and continue game
                break;
        }
    }

    /**
     *
     */
    private Button getButtonFromIntId(int id) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_play_game, null);
        GridLayout board = (GridLayout) findViewById(R.id.grid);
        return (Button) board.getChildAt(id);
    }

    /**
     * Resets all buttons to blank state and creates a new Game object
     */
    private void resetBoard() {
        /* Reset all buttons */
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_play_game, null);
        GridLayout board = (GridLayout) findViewById(R.id.grid);

        for (int i = 0; i < board.getChildCount(); i++) {
            Button btn = (Button) board.getChildAt(i);
            btn.setText("-");
            btn.setClickable(true);
        }

        /* Reset Game object */
        game.resetGameBoard();
    }
}
