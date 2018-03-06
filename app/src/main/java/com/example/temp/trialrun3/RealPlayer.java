package com.example.temp.trialrun3;

import java.util.ArrayList;

/**
 * Created by Sheena on 2018-03-04.
 */

public class RealPlayer implements Player {
    private int numOfCards;
    private boolean isAlive;
    private ArrayList<Card> hand;
    private boolean canPlay;
    private boolean isHost;

    public RealPlayer() {
    }
    public boolean getHost(){
        return isHost;
    }

    public void playCard(Card cardToPlay){

    }
    public Card drawCard(){

        return new AttackCard();
    }
    public void addToHand(Card cardToAdd){
        hand.add(cardToAdd);
        numOfCards++;
    }
    public void setCanPlay(boolean isActivePlayer){
        canPlay = isActivePlayer;
    }

    public boolean playMultipleCards(Card[] cardsToPlay){

        return false;
    }
}
