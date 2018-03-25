package com.example.temp.trialrun3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.temp.trialrun3.Cards.Card;

import java.util.ArrayList;
import java.util.Collections;

public class GameView extends AppCompatActivity {

    private int numOfOpp;
    private int startingHandSize = 4;
    private ArrayList<Button> playersShown = new ArrayList<>();
    private ArrayList<Card> cardList = new ArrayList<Card>();
    public Deck deck = Deck.getDeck();  //singleton
    private ArrayList<Player> playerList = new ArrayList<Player>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_view);
        numOfOpp = getIntent().getIntExtra(SelectNumOfPlayers.EXTRA_NUMOPPONENTS,0);
        int numOfPlayers = numOfOpp++;
        cardList = getIntent().getParcelableArrayListExtra(Lobby.EXTRA_CARD_LIST);
        playerList = getIntent().getParcelableArrayListExtra(Lobby.EXTRA_PLAYER_LIST);

        setupDeckAndInitialPlayerHands(numOfPlayers);

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

    private void setupDeckAndInitialPlayerHands(int numOfPlayers) {

        CardFactory cardFactory = new CardFactory();
        for(Card c : cardList)
        {
            deck.addAll(cardFactory.createCardCopy(numOfPlayers*2,c));
        }
        deck.addAll(cardFactory.makeGenericCardSet(numOfPlayers*2));
        deck.shuffle();

        for (int i=0; i<startingHandSize; i++)
        {
            for (Player p : playerList)
            {
                p.addToHand(deck.draw());
            }
        }
        for(Player p : playerList)
        {
            p.addToHand(cardFactory.makeSaveCard());
        }
        deck.addAll(cardFactory.makeNumOfSaveCards(numOfPlayers));
        deck.addAll(cardFactory.makeNumOfDeathCards(numOfOpp));
        deck.shuffle();
    }

}
