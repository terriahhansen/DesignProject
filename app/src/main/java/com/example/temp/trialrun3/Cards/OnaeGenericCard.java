package com.example.temp.trialrun3.Cards;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.logging.Logger;

/**
 * Created by temp on 3/24/2018.
 */

public class OnaeGenericCard implements Card, Parcelable {

    public OnaeGenericCard(){}

    private final String cardType = this.getClass().toString();
    private static Logger log;

    protected OnaeGenericCard(Parcel parcel) throws Exception
    {
        if (!this.cardType.equals(parcel.readString()))
        {
            throw new Exception();
        }
    }

    public static final Creator<OnaeGenericCard> CREATOR = new Creator<OnaeGenericCard>() {
        @Override
        public OnaeGenericCard createFromParcel(Parcel parcel) {
            try{
                return new OnaeGenericCard(parcel);
            }
            catch (Exception e){
                log.severe("Attempted creation of a Card Lock Card from a parcel of different source type"+ e.toString());
            }
            return null;
        }

        @Override
        public OnaeGenericCard[] newArray(int size) {
            return new OnaeGenericCard[size];
        }
    };

    @Override
    public void performAction() {

    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(cardType);
    }
}
