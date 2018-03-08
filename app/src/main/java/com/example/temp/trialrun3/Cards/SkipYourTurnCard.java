package com.example.temp.trialrun3.Cards;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.logging.Logger;

/**
 * Created by Sheena on 2018-03-06.
 */

public class SkipYourTurnCard implements Card, Parcelable {

    public static final int ID_NUMBER = 7;
    private final String cardType = this.getClass().toString();
    private static final String cardTitle = "Fell Asleep In Class";
    private static final String actionDescription = "Skip Your Turn";
    private static final int score = 6;
    private static Logger log;

    public SkipYourTurnCard() {
    }

    private SkipYourTurnCard(Parcel parcel) throws Exception {
        if (!this.cardType.equals(parcel.readString()))
        {
            throw new Exception();
        }
    }

    public static final Creator<SkipYourTurnCard> CREATOR = new Creator<SkipYourTurnCard>() {
        @Override
        public SkipYourTurnCard createFromParcel(Parcel in) {
            try{
                return new SkipYourTurnCard(in);
            }
            catch (Exception e){
                log.severe("Attempted creation of a Skip Your Turn Card from a parcel of different source type"+ e.toString());
            }
            return null;
        }

        @Override
        public SkipYourTurnCard[] newArray(int size) {
            return new SkipYourTurnCard[size];
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
