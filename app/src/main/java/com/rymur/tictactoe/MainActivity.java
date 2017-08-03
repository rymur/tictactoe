package com.rymur.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String MODE = "com.rymur.tictactoe.MODE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Starts a one player game
     * @param view - The button that was clicked
     */
    public void onePlayerOnClick(View view) {
        Intent intent = new Intent(this, ChooseDifficultyActivity.class);
        startActivity(intent);
    }

    /**
     * Starts a two player game
     * @param view - The button that was clicked
     */
    public void twoPlayerOnClick(View view) {
        Intent intent = new Intent(this, PlayGameActivity.class);
        intent.putExtra(MODE, "human");
        startActivity(intent);
    }
}
