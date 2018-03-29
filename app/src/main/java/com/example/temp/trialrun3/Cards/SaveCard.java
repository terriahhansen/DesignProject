package com.example.temp.trialrun3.Cards;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.temp.trialrun3.GameView;

import java.util.logging.Logger;

/**
 * Created by Sheena on 2018-03-06.
 */

public class SaveCard implements Card, Parcelable {
    private final String cardType = this.getClass().toString();
    private static final String cardTitle = "Save";
    private static final String actionDescription = "Saved by the bell(curve)";
    private static Logger log;

    public SaveCard() {
    }

    private SaveCard(Parcel parcel) throws Exception{
        if (!this.cardType.equals(parcel.readString()))
        {
            throw new Exception();
        }
    }

    public static final Creator<SaveCard> CREATOR = new Creator<SaveCard>() {
        @Override
        public SaveCard createFromParcel(Parcel in) {
           try{
               return new SaveCard(in);
           }
           catch (Exception e){
               log.severe("Attempted creation of a Save Card from a parcel of different source type"+ e.toString());
           }
           return null;
        }

        @Override
        public SaveCard[] newArray(int size) {
            return new SaveCard[size];
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
    public void performAction(GameView gameView) {

    }

    @Override
    public int getScore() {
        return 0;
    }
}
