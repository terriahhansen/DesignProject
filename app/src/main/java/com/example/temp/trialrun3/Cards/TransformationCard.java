package com.example.temp.trialrun3.Cards;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.temp.trialrun3.GameView;

import java.util.logging.Logger;

/**
 * Created by temp on 3/7/2018.
 */

public class TransformationCard implements Card,Parcelable {

    public static final int ID_NUMBER = 8;
    private final String cardType = this.getClass().toString();
    private static final String cardTitle = "Box Of Truth";
    private static final String actionDescription = "Transform any card";
    private static final int score = 5;
    private static Logger log;
    public TransformationCard()
    {

    }
    protected TransformationCard(Parcel parcel) throws Exception{
        if (!this.cardType.equals(parcel.readString()))
        {
            throw new Exception();
        }
    }

    public static final Creator<TransformationCard> CREATOR = new Creator<TransformationCard>() {
        @Override
        public TransformationCard createFromParcel(Parcel in) {
            try{
                return new TransformationCard(in);
            }
            catch (Exception e){
                log.severe("Attempted creation of a skip Your Turn Card from a parcel of different source type"+ e.toString());
            }
            return null;
        }

        @Override
        public TransformationCard[] newArray(int size) {
            return new TransformationCard[size];
        }
    };

    @Override
    public void performAction(GameView gameView) {

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
