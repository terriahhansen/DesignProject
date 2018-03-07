package com.example.temp.trialrun3;

/**
 * Created by Sheena on 2018-03-04.
 */

public class PlayerFactory {

    public Player createRealPlayer(int playerNumber){
        return new RealPlayer(playerNumber);
    };

    public Player createDumbAI(int playerNumber){
        return new DumbAI(playerNumber);
    }
}
