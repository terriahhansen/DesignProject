package com.example.temp.trialrun3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class GameView extends AppCompatActivity {

    private int numOfOpp;
    private ArrayList<Button> playersShown = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_view);
        numOfOpp = getIntent().getIntExtra(SelectNumOfPlayers.EXTRA_NUMOPPONENTS,0);

        Button button = findViewById(R.id.player1Button);
        playersShown.add(button);
        button = findViewById(R.id.player2Button);
        playersShown.add(button);
        button = findViewById(R.id.player3Button);
        playersShown.add(button);
        button = findViewById(R.id.player4Button);
        playersShown.add(button);
        button = findViewById(R.id.player5Button);
        playersShown.add(button);

        for( int i=0; i<numOfOpp ; i++){
            playersShown.get(i).setVisibility(View.VISIBLE);
        }
    }
}
