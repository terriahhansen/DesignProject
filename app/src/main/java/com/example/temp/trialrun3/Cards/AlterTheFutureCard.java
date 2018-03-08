package com.example.temp.trialrun3.Cards;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.logging.Logger;

/**
 * Created by Sheena on 2018-03-06.
 */

public class AlterTheFutureCard implements Card, Parcelable {

    public static final int ID_NUMBER = 5;
    private final String cardType = this.getClass().toString();
    private static final String cardTitle = "Extension";
    private static final String actionDescription = "View the top three cards of the deck and alter the order";
    private static final int score = 3;
    private static Logger log;

    public AlterTheFutureCard() {
    }

    private AlterTheFutureCard(Parcel parcel) throws Exception{
        if (!this.cardType.equals(parcel.readString()))
        {
            throw new Exception();
        }
    }

    public static final Creator<AlterTheFutureCard> CREATOR = new Creator<AlterTheFutureCard>() {
        @Override
        public AlterTheFutureCard createFromParcel(Parcel in) {
            try{
                return new AlterTheFutureCard(in);
            }
            catch (Exception e){
                log.severe("Attempted creation of an Alter the Future Card from a parcel of different source type"+ e.toString());
            }
            return null;
        }

        @Override
        public AlterTheFutureCard[] newArray(int size) {
            return new AlterTheFutureCard[size];
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
