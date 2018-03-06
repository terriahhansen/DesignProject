package com.example.temp.trialrun3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class Lobby extends AppCompatActivity {

    private String GameMode;
    private int NumOfOpponents;
    private int addedPlayers =0;
    private static final int chooseCardRequest = 0;
    private ArrayList<Card> deckCards;
    private TextView textView;
//    private ArrayList<Player> PlayerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        GameMode = getIntent().getStringExtra(ChooseMode.EXTRA_GAMEMODE);
        NumOfOpponents = getIntent().getIntExtra(SelectNumOfPlayers.EXTRA_NUMOPPONENTS, 1);

        textView = findViewById(R.id.textView);
        textView.setText(GameMode + " Game mode selected. "
                +"The number of opponents chosen was: " + NumOfOpponents );
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
//        if (ChooseDifficulty.EASY.equals(Level))
//        {
//            for (int i = 1; i <= NumOfOpponents; i++)
//            {
//                PlayerList.add(DumbAi(i));
//            }
//        }
//        else if ()
    }

    public void chooseCards(View view)
    {
        Intent intent = new Intent(this, DeckCreator.class);
        startActivityForResult(intent,0);
    }

    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent childIntent) {
        switch (requestCode)
        {
            case (chooseCardRequest):
            {
                if (resultCode == DeckCreator.RESULT_OK)
                {
                    Map<Integer, Boolean> chosenCardMap = (Map<Integer, Boolean>) childIntent.getSerializableExtra(DeckCreator.checkBoxValueMap);
                    for (Map.Entry<Integer,Boolean> entry : chosenCardMap.entrySet())
                    {
                        addAppropriateCard(entry);
                    }
                }
            }
            break;
        }
        String createdCardTypes = "";
        for (Card c : deckCards)
        {
            createdCardTypes += c.getClass().toString();
        }
        textView.setText(createdCardTypes);
    }

    private void addAppropriateCard(Map.Entry<Integer, Boolean> entry) {
        int key = entry.getKey();
        switch (key)
        {
            case (1):
            {
                if (entry.getValue())
                {
                    deckCards.add(new AttackCard());
                }
            }
//            case (2):
//            {
//                if (entry.getValue())
//                {
//                    deckCards.add(new )
//                }
//            }
        }
    }
}
