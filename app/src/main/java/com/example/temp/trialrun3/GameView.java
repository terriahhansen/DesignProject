package com.example.temp.trialrun3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
<<<<<<< Updated upstream
import android.view.View;
import android.widget.Button;
=======
import android.widget.TextView;

import com.example.temp.trialrun3.Cards.Card;
>>>>>>> Stashed changes

import java.util.ArrayList;

public class GameView extends AppCompatActivity {

<<<<<<< Updated upstream
    private int numOfOpp;
    private ArrayList<Button> playersShown = new ArrayList<>();
=======
    private ArrayList<Card> cardList = new ArrayList<Card>();
>>>>>>> Stashed changes
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_view);
<<<<<<< Updated upstream
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
=======
        cardList = (ArrayList<Card>) getIntent().getSerializableExtra(Lobby.EXTRA_CARD_LIST);
        StringBuilder cardLister = new StringBuilder();
        for (int i=0; i<cardList.size(); i++)
        {
            cardLister.append(cardList.get(i).getClass().toString()).append("\n");
        }
        TextView textView = findViewById(R.id.textView);
        textView.setText(cardLister.toString());
>>>>>>> Stashed changes

        for( int i=0; i<numOfOpp ; i++){
            playersShown.get(i).setVisibility(View.VISIBLE);
        }
    }
}
