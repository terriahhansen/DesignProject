package com.example.temp.trialrun3;

import java.util.ArrayList;

/**
 * Created by Sheena on 2018-03-04.
 */

public class DumbAI implements Player {
    private int numOfCards;
    private boolean isAlive = true;
    private ArrayList<Card> hand;
    private boolean canPlay;
    private boolean isHost = false;
    private DumbAIScoreCalculation calculator;

    public DumbAI() {
    }
    public boolean getHost(){
        return isHost;
    }
    public void playCard(Card cardToPlay){
        hand.remove(cardToPlay);
        discardPile.add(cardToPlay);
        cardToPlay.performAction();
    }
    public Card drawCard(){
        Card cardDrawn = deck.get(0);
        addToHand(cardDrawn);
        deck.remove(0);
        return cardDrawn;
    }
    public void addToHand(Card cardToAdd){
        hand.add(cardToAdd);
        numOfCards++;
    }
    public void setCanPlay(boolean isActivePlayer){
        canPlay = isActivePlayer;
    }
    public void setIsAlive(boolean setValue){
        isAlive = setValue;
    }

    public void startTurn(){
        calculator = new DumbAIScoreCalculation(hand);
        Card cardToPlay = calculator.calculateScore();
        if (cardToPlay != null)
            playCard(cardToPlay);
        if( drawCard().equals(DeathCard)){
           if(hand.contains(SafeCard)){
               hand.remove(SafeCard);
               discardPile.add(SafeCard);
               deck.randomAdd(DeathCard); // not sure about this
            }
            else
                setIsAlive(false);
        }

    }
}
