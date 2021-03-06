package com.example.temp.trialrun3.Cards;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.temp.trialrun3.CardActions;
import com.example.temp.trialrun3.GameView;

import java.util.logging.Logger;

/**
 * Created by Sheena on 2018-03-06.
 */

public class SeeTheFutureCard implements Card, Parcelable {

    public static final int ID_NUMBER = 6;
    private final String cardType = this.getClass().toString();
    private static final String cardTitle = "See the Future";
    private static final String actionDescription = "Look at the top three cards of the deck";
    private static final int score = 2;
    private static Logger log;

    public SeeTheFutureCard() {
    }

    private SeeTheFutureCard(Parcel parcel) throws Exception {
        if (!this.cardType.equals(parcel.readString())) {
            throw new Exception();
        }
    }

    public static final Creator<SeeTheFutureCard> CREATOR = new Creator<SeeTheFutureCard>() {
        @Override
        public SeeTheFutureCard createFromParcel(Parcel parcel) {
            try {
                return new SeeTheFutureCard(parcel);
            } catch (Exception e) {
                log.severe("Attempted creation of a See the Future Card from a parcel of different souce type" + e.toString());
            }
            return null;
        }

        @Override
        public SeeTheFutureCard[] newArray(int size) {
            return new SeeTheFutureCard[size];
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

        CardActions.SeeTheFuture(gameView);
    }

    @Override
    public int getScore() {
        return score;
    }
}
