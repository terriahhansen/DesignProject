package com.example.temp.trialrun3.Cards;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.logging.Logger;

/**
 * Created by temp on 3/24/2018.
 */

public class CivilGenericCard implements Card, Parcelable {

    private final String cardType = this.getClass().toString();
    private static Logger log;

    public CivilGenericCard()
    {

    }

    protected CivilGenericCard(Parcel parcel) throws Exception
    {
        if (!this.cardType.equals(parcel.readString()))
        {
            throw new Exception();
        }
    }

    public static final Creator<CivilGenericCard> CREATOR = new Creator<CivilGenericCard>() {
        @Override
        public CivilGenericCard createFromParcel(Parcel parcel) {
            try{
                return new CivilGenericCard(parcel);
            }
            catch (Exception e){
                log.severe("Attempted creation of a Card Lock Card from a parcel of different source type"+ e.toString());
            }
            return null;
        }

        @Override
        public CivilGenericCard[] newArray(int size) {
            return new CivilGenericCard[size];
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
