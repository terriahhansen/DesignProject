package com.example.temp.trialrun3.Cards;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ProgressBar;

import com.example.temp.trialrun3.GameView;

import java.util.logging.Logger;

/**
 * Created by temp on 3/24/2018.
 */

public class ProcessGenericCard implements Card, Parcelable {

    private final String cardType = this.getClass().toString();
    private static Logger log;

    public ProcessGenericCard(){}

    protected ProcessGenericCard(Parcel parcel) throws Exception
    {
        if (!this.cardType.equals(parcel.readString()))
        {
            throw new Exception();
        }
    }

    public static final Creator<ProcessGenericCard> CREATOR = new Creator<ProcessGenericCard>() {
        @Override
        public ProcessGenericCard createFromParcel(Parcel parcel) {
            try{
                return new ProcessGenericCard(parcel);
            }
            catch (Exception e){
                log.severe("Attempted creation of a Card Lock Card from a parcel of different source type"+ e.toString());
            }
            return null;
        }

        @Override
        public ProcessGenericCard[] newArray(int size) {
            return new ProcessGenericCard[size];
        }
    };

    @Override
    public void performAction(GameView gameView) {

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
