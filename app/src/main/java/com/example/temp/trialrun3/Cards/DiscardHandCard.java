package com.example.temp.trialrun3.Cards;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.temp.trialrun3.Cards.Card;

import java.util.logging.Logger;

/**
 * Created by Sheena on 2018-03-06.
 */

public class DiscardHandCard implements Card, Parcelable {

    public static final int ID_NUMBER = 4;
    private final String cardType = this.getClass().toString();
    private static final String cardTitle = "The House Always Wins";
    private static final String actionDescription = "Discard your entire hand";
    private static Logger log;

    public DiscardHandCard() {
    }

    private DiscardHandCard(Parcel parcel) throws Exception{
        if (!this.cardType.equals(parcel.readString()))
        {
            throw new Exception();
        }
    }

    public static final Creator<DiscardHandCard> CREATOR = new Creator<DiscardHandCard>() {
        @Override
        public DiscardHandCard createFromParcel(Parcel in) {
            try {
                return new DiscardHandCard(in);
            }
            catch (Exception e){
                log.severe("Attempted creation of a Discard Card from a parcel of different source type"+ e.toString());
            }
            return null;
        }

        @Override
        public DiscardHandCard[] newArray(int size) {

            return new DiscardHandCard[size];
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
        return 0;
    }
}
