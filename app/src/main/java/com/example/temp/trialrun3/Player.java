package com.example.temp.trialrun3;

import java.util.ArrayList;

/**
 * Created by Sheena on 2018-03-04.
 */

public interface Player {
    public int numOfCards = 0;
    public boolean isAlive = false;
    public ArrayList<Card> hand = new ArrayList<Card>();
    public boolean canPlay = false;
    public boolean isHost = false;

    public boolean getHost();
    public void playCard(Card cardToPlay);
    public Card drawCard();
    public void addToHand(Card cardToAdd);
    public void setCanPlay(boolean isActivePlayer);
    //public boolean playMultipleCards(Card[] cardsToPlay);

}
