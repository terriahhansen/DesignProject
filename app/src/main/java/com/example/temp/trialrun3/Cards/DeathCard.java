package com.example.temp.trialrun3.Cards;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.temp.trialrun3.GameView;

import java.util.logging.Logger;

/**
 * Created by Sheena on 2018-03-06.
 */

public class DeathCard implements Card, Parcelable {
    private final String cardType = this.getClass().toString();
    private static final String cardTitle = "Failed Your Exam";
    private static final String actionDescription = "Please repeat the term again next year";
    private static Logger log;

    public DeathCard() {
    }

    private DeathCard(Parcel parcel) throws Exception{
        if (!this.cardType.equals(parcel.readString()))
        {
            throw new Exception();
        }
    }

    public static final Creator<DeathCard> CREATOR = new Creator<DeathCard>() {
        @Override
        public DeathCard createFromParcel(Parcel in) {
            try{
                return new DeathCard(in);
            }
            catch (Exception e){
                log.severe("Attempted creation of a Death Card from a parcel of different source type"+ e.toString());
            }
            return null;
        }

        @Override
        public DeathCard[] newArray(int size) {
            return new DeathCard[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cardType);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void performAction(GameView gameView) {

    }

    @Override
    public int getScore() {
        return 0;
    }
}
