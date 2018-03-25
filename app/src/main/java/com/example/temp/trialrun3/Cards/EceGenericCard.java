package com.example.temp.trialrun3.Cards;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.logging.Logger;

/**
 * Created by temp on 3/24/2018.
 */

public class EceGenericCard implements Card, Parcelable {

    private final String cardType = this.getClass().toString();
    private static Logger log;

    public EceGenericCard() {}

    protected EceGenericCard(Parcel parcel) throws Exception
    {
        if (!this.cardType.equals(parcel.readString()))
        {
            throw new Exception();
        }
    }

    public static final Creator<EceGenericCard> CREATOR = new Creator<EceGenericCard>() {
        @Override
        public EceGenericCard createFromParcel(Parcel parcel) {
            try{
                return new EceGenericCard(parcel);
            }
            catch (Exception e){
                log.severe("Attempted creation of a Card Lock Card from a parcel of different source type"+ e.toString());
            }
            return null;
        }

        @Override
        public EceGenericCard[] newArray(int size) {
            return new EceGenericCard[size];
        }
    };

    @Override
    public void performAction() {

    }

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(cardType);
    }
}
