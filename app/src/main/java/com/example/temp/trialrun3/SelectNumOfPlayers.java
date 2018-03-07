package com.example.temp.trialrun3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SelectNumOfPlayers extends AppCompatActivity {

    private String gameMode;
    private String difficulty;
    public static final String EXTRA_NUMOPPONENTS = "com.example.trialrun3.SelectNumOfPlayers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_num_of_players);
        gameMode = getIntent().getStringExtra(ChooseMode.EXTRA_GAMEMODE);
        difficulty = getIntent().getStringExtra(ChooseDifficulty.EXTRA_DIFFICULTY);
    }

    public void select1Opponent(View view)
    {
        Intent intent = new Intent(this, Lobby.class);
        intent.putExtra(EXTRA_NUMOPPONENTS, 1);
        intent.putExtra(ChooseDifficulty.EXTRA_DIFFICULTY, difficulty);
        intent.putExtra(ChooseMode.EXTRA_GAMEMODE, gameMode);
        startActivity(intent);
    }

    public void select2Opponents(View view)
    {
        Intent intent = new Intent(this, Lobby.class);
        intent.putExtra(EXTRA_NUMOPPONENTS, 2);
        intent.putExtra(ChooseDifficulty.EXTRA_DIFFICULTY, difficulty);
        intent.putExtra(ChooseMode.EXTRA_GAMEMODE, gameMode);
        startActivity(intent);
    }

    public void select3Opponents(View view)
    {
        Intent intent = new Intent(this, Lobby.class);
        intent.putExtra(EXTRA_NUMOPPONENTS, 3);
        intent.putExtra(ChooseDifficulty.EXTRA_DIFFICULTY, difficulty);
        intent.putExtra(ChooseMode.EXTRA_GAMEMODE, gameMode);
        startActivity(intent);
    }

    public void select4Opponents(View view)
    {
        Intent intent = new Intent(this, Lobby.class);
        intent.putExtra(EXTRA_NUMOPPONENTS, 4);
        intent.putExtra(ChooseDifficulty.EXTRA_DIFFICULTY, difficulty);
        intent.putExtra(ChooseMode.EXTRA_GAMEMODE, gameMode);
        startActivity(intent);
    }

    public void select5Opponents(View view)
    {
        Intent intent = new Intent(this, Lobby.class);
        intent.putExtra(EXTRA_NUMOPPONENTS, 5);
        intent.putExtra(ChooseDifficulty.EXTRA_DIFFICULTY, difficulty);
        intent.putExtra(ChooseMode.EXTRA_GAMEMODE, gameMode);
        startActivity(intent);
    }

}
