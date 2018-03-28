package com.example.temp.trialrun3;

import android.os.Parcelable;

import com.example.temp.trialrun3.Cards.Card;

import java.util.ArrayList;

/**
 * Created by Sheena on 2018-03-04.
 */

public interface Player extends Parcelable{

    int numOfCards = 0;
    boolean isAlive = true;
    ArrayList<Card> hand = new ArrayList<Card>();
    boolean canPlay = false;
    boolean isHost = false;

    public boolean isHost();
    public void setIsHost(boolean flag);
    public void setIsAlive(boolean flag);
    public boolean isAlive();
    public void playCard(Card cardToPlay);
    public Card drawCard(String gameMode);
    public void addToHand(Card cardToAdd);
    public void setCanPlay(boolean isActivePlayer);
    public boolean canPlay();
    public String toString();
    //public boolean playMultipleCards(Card[] cardsToPlay);
    public ArrayList<Card> getHand();

}
