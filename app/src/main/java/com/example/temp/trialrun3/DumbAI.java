package com.example.temp.trialrun3;

import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;

import com.example.temp.trialrun3.Cards.Card;
import com.example.temp.trialrun3.Cards.DeathCard;
import com.example.temp.trialrun3.Cards.SaveCard;

import java.util.ArrayList;

/**
 * Created by Sheena on 2018-03-04.
 */

public class DumbAI implements AI, Parcelable {
    private int numOfCards;
    private int playerNumber;
    private boolean isAlive = true;
    private ArrayList<Card> hand = new ArrayList<Card>();
    private boolean canPlay;
    private boolean isHost = false;
    private ScoreCalculation calculator = new DumbAIScoreCalculation();

    public DumbAI( int playerNumber)
    {
        this.playerNumber = playerNumber;
    }

    protected DumbAI(Parcel in) {
        numOfCards = in.readInt();
        playerNumber = in.readInt();
        isAlive = in.readByte() != 0;
        canPlay = in.readByte() != 0;
        isHost = in.readByte() != 0;
        hand = (ArrayList<Card>) in.readSerializable();
    }

    public static final Creator<DumbAI> CREATOR = new Creator<DumbAI>() {
        @Override
        public DumbAI createFromParcel(Parcel parcel) {
            return new DumbAI(parcel);
        }

        @Override
        public DumbAI[] newArray(int size) {
            return new DumbAI[size];
        }
    };

    public String toString()
    {
        return new StringBuilder().append("Player ").append(Integer.toString(playerNumber)).append(":                    AI").toString();
    }
    public boolean isHost(){
        return isHost;
    }

    @Override
    public void setIsHost(boolean flag) {
        this.isHost = flag;
    }

    public void playCard(Card cardToPlay){
        hand.remove(cardToPlay);
        DiscardPile.getDiscardPile().add(cardToPlay);
        cardToPlay.performAction();
    }

    @Override
    public Card drawCard(String gameMode)
    {
        Deck deck = Deck.getDeck();
        Card c = deck.draw();
        if (c instanceof DeathCard)
        {
            for (int i=0; i<hand.size(); i++)
            {
                if (hand.get(i) instanceof SaveCard)
                {
                    DiscardPile.getDiscardPile().add(c);
                    playCard(hand.get(i));
                    return null;
                }
            }
            setIsAlive(false);
            return null;
        }
        else
        {
            addToHand(c);
            return c;
        }
    }

    public void addToHand(Card cardToAdd){
        hand.add(cardToAdd);
        numOfCards++;
    }
    public void setCanPlay(boolean isActivePlayer){
        canPlay = isActivePlayer;
    }

    @Override
    public boolean canPlay() {
        return canPlay;
    }

    public void setIsAlive(boolean setValue){
        isAlive = setValue;
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(numOfCards);
        parcel.writeInt(playerNumber);
        parcel.writeByte((byte) (isAlive ? 1 : 0));
        parcel.writeByte((byte) (canPlay ? 1 : 0));
        parcel.writeByte((byte) (isHost ? 1 : 0));
        parcel.writeSerializable(hand);
    }
    public ArrayList<Card> getHand(){
        return hand;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void performTurnAlgorithm(GameView game)
    {
        Card cardToPlay = calculator.calculateScore(hand);
        playCard(cardToPlay);
        game.drawCard(game.getCurrentFocus());
    }

    @Override
    public Card determineCardToPlay(ScoreCalculation calculator, ArrayList<Card> hand)
    {
        return null;
    }


//    }
}
