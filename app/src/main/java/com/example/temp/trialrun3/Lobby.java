package com.example.temp.trialrun3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Lobby extends AppCompatActivity {

    private String GameMode;
    private int NumOfOpponents;
    private int addedPlayers =0;
//    private ArrayList<Player> PlayerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        GameMode = getIntent().getStringExtra(ChooseMode.EXTRA_GAMEMODE);
        NumOfOpponents = getIntent().getIntExtra(SelectNumOfPlayers.EXTRA_NUMOPPONENTS, 1);

//        TextView textView = findViewById(R.id.textView);
//        textView.setText(GameMode + " Game mode selected. "
//                +"The number of opponents chosen was:" + NumOfOpponents);
        fillOpponentList("EASY");
//        if(ChooseMode.MULTIPLAYER.equals(GameMode))
//        {
//            while (addedPlayers<NumOfOpponents)
//            {
//                waitForJoinRequest();
//            }
//
//        }
    }

    public void fillOpponentList (String Level)
    {
//        if (chooseDifficulty.EASY.equals(Level))
//        {
//            for (int i = 1; i <= NumOfOpponents; i++)
//            {
//                PlayerList.add(DumbAi(i));
//            }
//        }
//        else if ()
    }

    public void ChooseCards(View view)
    {
        Intent intent = new Intent(this, deckCreator.class);
        startActivity(intent);
    }
}
