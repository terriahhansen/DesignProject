package com.example.temp.trialrun3;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.temp.trialrun3.Cards.AttackCard;
import com.example.temp.trialrun3.Cards.Card;

import java.util.ArrayList;
import java.util.Map;

public class Lobby extends AppCompatActivity {

    private String gameMode;
    private int numOfOpponents;
    private String difficulty;
    private int addedPlayers =0; //for multiplayer
    private static final int hostId = 0;
    private static final int chooseCardRequest = 0;
    private ArrayList<Card> deckCards = new ArrayList<>();
    private ArrayList<Player> playerList = new ArrayList<>();
    private static final String NO_DECK_MESSAGE = "Please choose the cards you wish to include in the game deck before continuing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        gameMode = getIntent().getStringExtra(ChooseMode.EXTRA_GAMEMODE);
        numOfOpponents = getIntent().getIntExtra(SelectNumOfPlayers.EXTRA_NUMOPPONENTS, 1);
        difficulty = getIntent().getStringExtra(ChooseDifficulty.EXTRA_DIFFICULTY);

        fillPlayerList(difficulty);
        ArrayAdapter<Player> playerArrayAdapter = new ArrayAdapter<Player>(this, R.layout.simple_text_view, playerList);
        ListView listView = (ListView) findViewById(R.id.playerList);
        listView.setAdapter(playerArrayAdapter);
//        if(ChooseMode.MULTIPLAYER.equals(gameMode))
//        {
//            while (addedPlayers<numOfOpponents)
//            {
//                waitForJoinRequest();
//            }
//
//        }
    }

    public void fillPlayerList(String Level)
    {
        PlayerFactory playerFactory = new PlayerFactory();
        playerList.add(playerFactory.createRealPlayer(hostId));
        if (ChooseDifficulty.EASY_MODE.equals(Level))
        {
            for (int i = 1; i <= numOfOpponents; i++)
            {
                playerList.add(playerFactory.createDumbAI(i));
            }
        }
    }

    public void startGame(View view)
    {
        if (deckCards.size()==0)
        {
            Snackbar.make(view,NO_DECK_MESSAGE,Snackbar.LENGTH_LONG).show();
        }
        else
        {
            Intent intent = new Intent(this, GameView.class);
            intent.putExtra(ChooseMode.EXTRA_GAMEMODE, gameMode);
            intent.putExtra(ChooseDifficulty.EXTRA_DIFFICULTY, difficulty);
            intent.putExtra(SelectNumOfPlayers.EXTRA_NUMOPPONENTS, numOfOpponents);
        }
    }

    public void chooseCards(View view)
    {
        Intent intent = new Intent(this, DeckCreator.class);
        startActivityForResult(intent,chooseCardRequest);
    }

    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent childIntent) {
        switch (requestCode)
        {
            case (chooseCardRequest):
            {
                if (resultCode == 1)
                {
                    deckCards.clear();
                    Map<Integer, Boolean> chosenCardMap = (Map<Integer, Boolean>) childIntent.getSerializableExtra(DeckCreator.checkBoxValueMap);
                    for (Map.Entry<Integer,Boolean> entry : chosenCardMap.entrySet())
                    {
                        addAppropriateCard(entry);
                    }
                }
            }
            break;
        }

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
