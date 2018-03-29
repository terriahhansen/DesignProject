package com.example.temp.trialrun3.Cards;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.temp.trialrun3.GameView;

import java.util.logging.Logger;

/**
 * Created by temp on 3/24/2018.
 */

public class MechGenericCard implements Card, Parcelable {

    private final String cardType = this.getClass().toString();
    private static Logger log;

    public MechGenericCard(){}

    protected MechGenericCard(Parcel parcel) throws Exception
    {
        if (!this.cardType.equals(parcel.readString()))
        {
            throw new Exception();
        }
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags)
    {
        parcel.writeString(cardType);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MechGenericCard> CREATOR = new Creator<MechGenericCard>() {
        @Override
        public MechGenericCard createFromParcel(Parcel parcel) {
            try{
                return new MechGenericCard(parcel);
            }
            catch (Exception e){
                log.severe("Attempted creation of a Card Lock Card from a parcel of different source type"+ e.toString());
            }
            return null;
        }

        @Override
        public MechGenericCard[] newArray(int size) {
            return new MechGenericCard[size];
        }
    };

    @Override
    public void performAction(GameView gameView) {

    }

    @Override
    public int getScore() {
        return score;
    }
}
