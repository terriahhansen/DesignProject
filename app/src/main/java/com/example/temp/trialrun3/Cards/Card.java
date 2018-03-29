package com.example.temp.trialrun3.Cards;

import android.os.Parcelable;

import com.example.temp.trialrun3.GameView;

/**
 * Created by temp on 3/4/2018.
 */

public interface Card extends Parcelable{

    String cardTitle = "Card";
    String actionDescription = "";
    String cardType = "Card";
    int score = 0;

    void performAction(GameView gameView);
    int getScore();
}
