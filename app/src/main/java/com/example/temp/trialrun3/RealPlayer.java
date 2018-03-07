package com.example.temp.trialrun3;

import com.example.temp.trialrun3.Cards.AttackCard;
import com.example.temp.trialrun3.Cards.Card;

import java.util.ArrayList;

/**
 * Created by Sheena on 2018-03-04.
 */

public class RealPlayer implements Player {
    private int numOfCards;
    private int playerNumber;
    private boolean isAlive;
    private ArrayList<Card> hand;
    private boolean canPlay;
    private boolean isHost;

    public RealPlayer(int playerNumber) {
        this.playerNumber = playerNumber;
        if(playerNumber==0)
        {
            this.isHost = true;
        }
    }

    public String toString()
    {
        StringBuilder playerRepresentation = new StringBuilder().append("Player ")
                .append(Integer.toString(playerNumber)).append(":                    ");
        if (isHost)
        {
            playerRepresentation.append("YOU");
        }
        else
        {
            playerRepresentation.append("CONNECTED PLAYER");
        }

        return playerRepresentation.toString();
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
