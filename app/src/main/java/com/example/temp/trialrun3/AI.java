package com.example.temp.trialrun3;

import android.os.Parcelable;

import com.example.temp.trialrun3.Cards.Card;

import java.util.ArrayList;

/**
 * Created by temp on 3/25/2018.
 */

public interface AI extends Player, Parcelable {

    void performTurnAlgorithm(GameView game);
    Card determineCardToPlay(ScoreCalculation calculator, ArrayList<Card> hand);
    //need to add some stuff here, such as a score calc member, and any methods that are common to AI's
}
