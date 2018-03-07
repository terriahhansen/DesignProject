package com.example.temp.trialrun3.Cards;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.logging.Logger;

/**
 * Created by Sheena on 2018-03-06.
 */

public class TakeACard implements Card, Parcelable {

    private final String cardType = this.getClass().toString();
    private static final String cardTitle = "Collaboration";
    private static final String actionDescription = "Take a random card from another player of your choice";
    private static final int score = 1;
    private static Logger log;

    public TakeACard() {
    }

    private TakeACard(Parcel parcel) throws Exception{
        if(!this.cardType.equals(parcel.readString()))
        {
            throw new Exception();
        }
    }

    public static final Creator<TakeACard> CREATOR = new Creator<TakeACard>() {
        @Override
        public TakeACard createFromParcel(Parcel in) {
            try{
                return new TakeACard(in);
            }
            catch (Exception e){
                log.severe("Attempted creation of a Take a Card from a parcel of different source type"+ e.toString());
            }
            return null;
        }

        @Override
        public TakeACard[] newArray(int size) {
            return new TakeACard[size];
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
// do something
    }

    @Override
    public int getScore() {
        return score;
    }
}
