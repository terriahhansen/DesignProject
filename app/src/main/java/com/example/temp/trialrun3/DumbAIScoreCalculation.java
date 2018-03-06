package com.example.temp.trialrun3;

import java.util.ArrayList;

/**
 * Created by Sheena on 2018-03-06.
 */

public class DumbAIScoreCalculation implements ScoreCalculation {

    private ArrayList<Card> cardsToCheck;

    public DumbAIScoreCalculation(ArrayList<Card> hand) {

        cardsToCheck = hand;
    }

    public Card calculateScore(){
        Card cardSelected=cardsToCheck.get(0);
        if  (cardSelected != null){
            for (int i=0; i<cardsToCheck.size() ; i++){
                if( cardSelected.getScore() < cardsToCheck.get(i).getScore())
                    cardSelected = cardsToCheck.get(i);
            }
        }
        return cardSelected;
    }
}
