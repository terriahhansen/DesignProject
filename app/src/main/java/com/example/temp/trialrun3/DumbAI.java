package com.example.temp.trialrun3;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.temp.trialrun3.Cards.Card;

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
    private DumbAIScoreCalculation calculator;

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
    public boolean getHost(){
        return isHost;
    }
    public void playCard(Card cardToPlay){
        hand.remove(cardToPlay);
//        discardPile.add(cardToPlay);
        cardToPlay.performAction();
    }
    public Card drawCard(){

//         should probably be :
//         return Deck.getDeck().draw();

//        Card cardDrawn = deck.get(0);
//        addToHand(cardDrawn);
//        deck.remove(0);
//        return cardDrawn;
        return null;
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
//        if( drawCard().equals(DeathCard)){
//           if(hand.contains(SafeCard)){
//               hand.remove(SafeCard);
//               discardPile.add(SafeCard);
//               deck.randomAdd(DeathCard); // not sure about this
//            }
//            else
//                setIsAlive(false);
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

//    }
}
