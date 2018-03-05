package com.example.temp.trialrun3;

import java.util.ArrayList;

/**
 * Created by Sheena on 2018-03-04.
 */

public class DumbAI implements Player {
    private int numOfCards;
    private boolean isAlive;
    private ArrayList<Card> hand;
    private boolean canPlay;
    private boolean isHost = false;

    public DumbAI() {
    }
    public boolean getHost(){
        return isHost;
    }
    public boolean playCard(Card cardToPlay){

    }
    public Card drawCard(){

    }
    public void addToHand(Card cardToAdd){
        hand.add(cardToAdd);
        numOfCards++;
    }
    public void setCanPlay(boolean isActivePlayer){
        canPlay = isActivePlayer;
    }
    public boolean playMultipleCards(Card[] cardsToPlay){

    }
}
