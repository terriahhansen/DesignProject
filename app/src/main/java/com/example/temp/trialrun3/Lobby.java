package com.example.temp.trialrun3;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.temp.trialrun3.Cards.AlterTheFutureCard;
import com.example.temp.trialrun3.Cards.AttackCard;
import com.example.temp.trialrun3.Cards.Card;
import com.example.temp.trialrun3.Cards.CardLockCard;
import com.example.temp.trialrun3.Cards.DiscardHandCard;
import com.example.temp.trialrun3.Cards.SeeTheFutureCard;
import com.example.temp.trialrun3.Cards.SkipYourTurnCard;
import com.example.temp.trialrun3.Cards.TakeACard;
import com.example.temp.trialrun3.Cards.TransformationCard;

import java.util.ArrayList;
import java.util.Map;

public class Lobby extends AppCompatActivity {

    private String gameMode;
    private int numOfOpponents;
    private String difficulty;
    private int addedPlayers =0; //for multiplayer
    private static final int hostId = 0;
    private static final int chooseCardRequest = 0;
    public static final String EXTRA_CARD_LIST = "com.example.trialrun3.Lobby.CARD_LIST";
    public static final String EXTRA_PLAYER_LIST = "com.example.trialrun.Lobby.PLAYER_LIST";
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
<<<<<<< Updated upstream
=======
            intent.putExtra(EXTRA_CARD_LIST, deckCards);
            intent.putExtra(EXTRA_PLAYER_LIST, playerList);
>>>>>>> Stashed changes
            startActivity(intent);
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
                if (resultCode == DeckCreator.DECK_CONFIRM_ACTION_CODE)
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
        CardFactory cardFactory = new CardFactory();
        switch (key)
        {
            case (AttackCard.ID_NUMBER):
            {
                if (entry.getValue())
                {
                    deckCards.add(cardFactory.makeAttackCard());
                }
            }
            case (CardLockCard.ID_NUMBER):
            {
                if (entry.getValue())
                {
                    deckCards.add(cardFactory.makeCardLockCard());
                }
            }
            case (TakeACard.ID_NUMBER):
            {
                if (entry.getValue())
                {
                    deckCards.add(cardFactory.makeTakeACard());
                }
            }
            case (DiscardHandCard.ID_NUMBER):
            {
                if (entry.getValue())
                {
                    deckCards.add(cardFactory.makeDiscardHandCard());
                }
            }
            case (AlterTheFutureCard.ID_NUMBER):
            {
                if (entry.getValue())
                {
                    deckCards.add(cardFactory.makeAlterTheFutureCard());
                }
            }
            case (SeeTheFutureCard.ID_NUMBER):
            {
                if (entry.getValue())
                {
                    deckCards.add(cardFactory.makeSeeTheFutureCard());
                }
            }
            case (SkipYourTurnCard.ID_NUMBER):
            {
                if (entry.getValue())
                {
                    deckCards.add(cardFactory.makeSkipTurnCard());
                }
            }
//            case (TransformationCard.ID_NUMBER):
//            {
//                if(entry.getValue())
//                {
//                    deckCards.add(cardFactory.makeTransformationCard());
//                }
//            }
        }
    }
}
