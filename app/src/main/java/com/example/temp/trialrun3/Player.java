package com.example.temp.trialrun3;

import java.util.ArrayList;

/**
 * Created by Sheena on 2018-03-04.
 */

public interface Player {
    int numOfCards;
    boolean isAlive;
    ArrayList<Card> hand;
    boolean canPlay;
    boolean isHost;

    boolean getHost();
    boolean playCard(Card cardToPlay);
    Card drawCard();
    void addToHand(Card cardToAdd);
    void setCanPlay(boolean isActivePlayer);
    boolean playMultipleCards(Card[] cardsToPlay);

}
