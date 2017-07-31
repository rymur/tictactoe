package com.rymur.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.security.InvalidParameterException;

public class MainActivity extends AppCompatActivity {
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new Game();
    }

    /**
     * Sets the button to the current player if button has not already been claimed
     * @param view
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
        }

        handleGameState();
    }

    /**
     * Determines whether the game has ended and updates the UI if it has.
     */
    private void handleGameState() {
        Game.GameState state = game.hasWon();

        switch (state) {
            case DRAW:
                // Pop up a dialog informing user of draw
                break;
            case O_WON:
                // Increase O score
                // Pop up dialog for new game
                break;
            case X_WON:
                // Increase X score
                // Pop up dialog for new game
                break;
            default:
                // Unfinished state
                // Do nothing and continue game
                break;
        }
    }
}
