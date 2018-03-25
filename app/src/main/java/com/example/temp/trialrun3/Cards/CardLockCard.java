package com.example.temp.trialrun3.Cards;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.logging.Logger;

/**
 * Created by Sheena on 2018-03-06.
 */

public class CardLockCard implements Card {

    public static final int ID_NUMBER = 2;
    private final String cardType = this.getClass().toString();
    private static final String cardTitle = "Locked out of the Lab";
    private static final String actionDescription = "Select a player to lock their save card for a turn";
    private static final int score = 4;
    private static Logger log;

    public CardLockCard() {
    }

    private CardLockCard(Parcel parcel) throws Exception{
        if (!this.cardType.equals(parcel.readString()))
        {
            throw new Exception();
        }
    }

    public static final Creator<CardLockCard> CREATOR = new Creator<CardLockCard>() {
        @Override
        public CardLockCard createFromParcel(Parcel parcel) {
            try{
                return new CardLockCard(parcel);
            }
            catch (Exception e){
                log.severe("Attempted creation of a Card Lock Card from a parcel of different source type"+ e.toString());
            }
            return null;
        }

        @Override
        public CardLockCard[] newArray(int size) {
            return new CardLockCard[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(cardType);
    }

    @Override
    public void performAction() {

    }

    @Override
    public int getScore() {
        return score;
    }
}
