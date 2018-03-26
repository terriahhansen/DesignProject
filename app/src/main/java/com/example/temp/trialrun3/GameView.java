package com.example.temp.trialrun3;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.example.temp.trialrun3.Cards.AlterTheFutureCard;
import com.example.temp.trialrun3.Cards.AttackCard;
import com.example.temp.trialrun3.Cards.Card;
import com.example.temp.trialrun3.Cards.CardLockCard;
import com.example.temp.trialrun3.Cards.CivilGenericCard;
import com.example.temp.trialrun3.Cards.DeathCard;
import com.example.temp.trialrun3.Cards.DiscardHandCard;
import com.example.temp.trialrun3.Cards.EceGenericCard;
import com.example.temp.trialrun3.Cards.MechGenericCard;
import com.example.temp.trialrun3.Cards.OnaeGenericCard;
import com.example.temp.trialrun3.Cards.ProcessGenericCard;
import com.example.temp.trialrun3.Cards.SaveCard;
import com.example.temp.trialrun3.Cards.SeeTheFutureCard;
import com.example.temp.trialrun3.Cards.SkipYourTurnCard;
import com.example.temp.trialrun3.Cards.TakeACard;
import com.example.temp.trialrun3.Cards.TransformationCard;

import java.util.ArrayList;
import java.util.Collections;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class GameView extends AppCompatActivity {

    private int numOfOpp;
    private int startingHandSize = 4;
    private ArrayList<Button> playersShown = new ArrayList<>();
    private ArrayList<Card> cardList = new ArrayList<Card>();
    public Deck deck = Deck.getDeck();  //singleton
    private ArrayList<Player> playerList = new ArrayList<Player>();
    private ArrayList<CheckBox> playerHostCards = new ArrayList<CheckBox>();


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

        for( int i=0; i<numOfOpp-1 ; i++){
            playersShown.get(i).setVisibility(View.VISIBLE);
        }

        CompoundButton.OnCheckedChangeListener listener = getListener();

        for ( CheckBox c : playerHostCards){
            c.setOnCheckedChangeListener(listener);
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
                Card card = deck.draw();
                p.addToHand(card);
                displayCards(p, card);
            }
        }
        for(Player p : playerList)
        {
            Card card = cardFactory.makeSaveCard();
            p.addToHand(card);
            displayCards(p,card);
        }
        deck.addAll(cardFactory.makeNumOfSaveCards(numOfPlayers));
        deck.addAll(cardFactory.makeNumOfDeathCards(numOfOpp));
        deck.shuffle();
    }

    private void displayCards(Player player, Card card){
       if ( player == playerList.get(0)) {
           LinearLayout linearLayout = (LinearLayout) findViewById(R.id.handView);
           CheckBox checkBox = new CheckBox(this);
           LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
           checkBox.setLayoutParams(layoutParams);
           getImage(card, checkBox);
           CompoundButton.OnCheckedChangeListener listener = getListener();
           checkBox.setOnCheckedChangeListener(listener);
           playerHostCards.add(checkBox);
           linearLayout.addView(checkBox);
       }
    }

    private void getImage(Card card, CheckBox checkBox){
        if( card.getClass() == AttackCard.class){
            checkBox.setBackgroundResource(R.drawable.attack);
        }
        else if(card.getClass() == AlterTheFutureCard.class)
        {
            checkBox.setBackgroundResource(R.drawable.extension);
        }
        else if(card.getClass() == CardLockCard.class)
        {
            checkBox.setBackgroundResource(R.drawable.cardlock);
        }
        else if(card.getClass() == CivilGenericCard.class)
        {
            checkBox.setBackgroundResource(R.drawable.death);
        }
        else if(card.getClass() == DeathCard.class)
        {
            checkBox.setBackgroundResource(R.drawable.death);
        }
        else if(card.getClass() == DiscardHandCard.class)
        {
            checkBox.setBackgroundResource(R.drawable.discard);
        }
        else if(card.getClass() == EceGenericCard.class)
        {
            checkBox.setBackgroundResource(R.drawable.death);
        }
        else if(card.getClass() == MechGenericCard.class)
        {
            checkBox.setBackgroundResource(R.drawable.death);
        }
        else if(card.getClass() == OnaeGenericCard.class)
        {
            checkBox.setBackgroundResource(R.drawable.death);
        }
        else if(card.getClass() == ProcessGenericCard.class)
        {
            checkBox.setBackgroundResource(R.drawable.death);
        }
        else if(card.getClass() == SaveCard.class)
        {
            checkBox.setBackgroundResource(R.drawable.save);
        }
        else if(card.getClass() == SeeTheFutureCard.class)
        {
            checkBox.setBackgroundResource(R.drawable.seethefuture);
        }
        else if(card.getClass() == SkipYourTurnCard.class)
        {
            checkBox.setBackgroundResource(R.drawable.skipyourturn);
        }
        else if(card.getClass() == TakeACard.class)
        {
            checkBox.setBackgroundResource(R.drawable.collaboration);
        }
        else if(card.getClass() == TransformationCard.class)
        {
            checkBox.setBackgroundResource(R.drawable.transform);
        }

    }

    public void drawCard(View view){
        Player p = playerList.get(0);
        Card c = deck.draw();
        p.addToHand(c);
        displayCards(p, c);
    }

    public void playCard(View view){
        for (int i = 0; i< playerHostCards.size() ; i++){
            CheckBox c = playerHostCards.get(i);
            if( c.isChecked()){
                c.setVisibility(View.GONE);
                playerHostCards.remove(i);
                for (int j = 0; j < playerHostCards.size(); j++) {
                    CheckBox ck = playerHostCards.get(j);
                    ck.setClickable(true);
                }
            }
        }
    }

    public CompoundButton.OnCheckedChangeListener getListener() {
        final Button playButton = findViewById(R.id.playCardButton);
        playButton.setClickable(false);
        CompoundButton.OnCheckedChangeListener checkBoxListener2 = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                int numOfCardsSelected = 0;
                if (isChecked) {
                    numOfCardsSelected++;
                } else {
                    numOfCardsSelected--;
                    for (int i = 0; i < playerHostCards.size(); i++) {
                        CheckBox c = playerHostCards.get(i);
                        c.setClickable(true);
                    }
                }
                if (numOfCardsSelected == 1) {
                    playButton.setClickable(true);
                    for (int i = 0; i < playerHostCards.size(); i++) {
                        CheckBox c = playerHostCards.get(i);
                        if (!c.isChecked()) {
                            c.setClickable(false);
                        }
                    }
                }
            }
        };
        return checkBoxListener2;
    }
}
