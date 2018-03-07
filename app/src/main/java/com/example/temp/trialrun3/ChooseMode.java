package com.example.temp.trialrun3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ChooseMode extends AppCompatActivity {

    public static final String EXTRA_GAMEMODE = "com.example.trialrun3.GAMEMODE";
    public static final String SINGLE_PLAYER = "Single Player";
    public static final String MULTIPLAYER = "Multiplayer";
    private static final String MULTIPLAYER_UNSUPPORTED = "Multiplyer mode not yet supported";
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

    public void chooseMultiplayer(View view)
    {
        Toast.makeText(this,MULTIPLAYER_UNSUPPORTED , Toast.LENGTH_SHORT).show();
    }
}
