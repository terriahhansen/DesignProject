package com.example.temp.trialrun3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ChooseMode extends AppCompatActivity {

    public static final String EXTRA_GAMEMODE = "com.example.trialrun3.GAMEMODE";
    public static final String SINGLE_PLAYER = "Single Player";
    public static final String MULTIPLAYER = "Multiplayer";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_mode);
    }

    public void chooseSinglePlayer(View view)
    {
        Intent intent = new Intent(this, ChooseDifficulty.class);
        intent.putExtra(EXTRA_GAMEMODE, SINGLE_PLAYER);
        startActivity(intent);
    }
}
