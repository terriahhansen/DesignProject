package com.example.temp.trialrun3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SelectNumOfPlayers extends AppCompatActivity {

    private String GameMode;
    public static final String EXTRA_NUMOPPONENTS = "com.example.trialrun3.SelectNumOfPlayers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_num_of_players);
        GameMode = getIntent().getStringExtra(ChooseMode.EXTRA_GAMEMODE);
    }

    public void select1Opponent(View view)
    {
        Intent intent = new Intent(this, Lobby.class);
        intent.putExtra(EXTRA_NUMOPPONENTS, 1);
        intent.putExtra(ChooseMode.EXTRA_GAMEMODE, GameMode);
        startActivity(intent);
    }

    public void select2Opponents(View view)
    {
        Intent intent = new Intent(this, Lobby.class);
        intent.putExtra(EXTRA_NUMOPPONENTS, 2);
        intent.putExtra(ChooseMode.EXTRA_GAMEMODE, GameMode);
        startActivity(intent);
    }

    public void select3Opponents(View view)
    {
//        Intent intent = new Intent(this, Lobby.class);
//        startActivity(intent);
    }

    public void select4Opponents(View view)
    {
//        Intent intent = new Intent(this, Lobby.class);
//        startActivity(intent);
    }

    public void select5Opponents(View view)
    {
//        Intent intent = new Intent(this, Lobby.class);
//        startActivity(intent);
    }

}
