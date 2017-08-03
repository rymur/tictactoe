package com.rymur.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Ryan on 8/2/2017.
 */

public class ChooseDifficultyActivity extends AppCompatActivity {
    public static final String MODE = "com.rymur.tictactoe.MODE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_difficulty);
    }

    public void easyOnClick(View view) {
        Intent intent = new Intent(this, PlayGameActivity.class);
        intent.putExtra(MODE, "easy");
        startActivity(intent);
    }

    public void hardOnClick(View view) {
        Intent intent = new Intent(this, PlayGameActivity.class);
        intent.putExtra(MODE, "hard");
        startActivity(intent);
    }
}
