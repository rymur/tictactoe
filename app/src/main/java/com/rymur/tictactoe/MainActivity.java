package com.rymur.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button board[][] = new Button[3][3];
    private String curPlayer = "O";  // TicTacToe rules specify O always goes first

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Sets the button to the current player if button has not already been claimed
     * @param view
     */
    public void onBtnClick(View view) {
        Button button = (Button) view;
        String btnValue = button.getText().toString();
        if (btnValue.equals("-")) {
            button.setText(curPlayer);
            button.setClickable(false);
            curPlayer = curPlayer == "O" ? "X" : "O";
        }
    }
}
