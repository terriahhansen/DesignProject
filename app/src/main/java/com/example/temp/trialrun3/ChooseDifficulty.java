package com.example.temp.trialrun3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ChooseDifficulty extends AppCompatActivity {

    private String mode;
    public static final String EXTRA_DIFFICULTY = "com.example.temp.trialrun3.ChooseDifficulty";
    private static final String EASY_MODE = "Easy";
    private static final String HARD_MODE = "Hard";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_difficulty);
        mode = getIntent().getStringExtra(ChooseMode.EXTRA_GAMEMODE);
    }

    public void chooseEasy (View view)
    {
        Intent intent = new Intent(this, SelectNumOfPlayers.class);
        intent.putExtra(ChooseMode.EXTRA_GAMEMODE, mode);
        intent.putExtra(EXTRA_DIFFICULTY,EASY_MODE);
        startActivity(intent);
    }
}
