package com.example.temp.trialrun3.Cards;

/**
 * Created by temp on 3/4/2018.
 */

public interface Card {

    String cardTitle = "Card";
    String actionDescription = "";
    int score = 0;

    void performAction();
    int getScore();
}
