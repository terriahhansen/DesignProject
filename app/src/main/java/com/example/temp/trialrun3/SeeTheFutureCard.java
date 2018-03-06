package com.example.temp.trialrun3;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sheena on 2018-03-06.
 */

public class SeeTheFutureCard implements Card, Parcelable {

    private final String cardType = this.getClass().toString();
    private static final String cardTitle = "See the Future";
    private static final String actionDescription = "Look at the top three cards of the deck";


    protected SeeTheFutureCard(Parcel in) {
        cardType = in.readString();
    }

    public static final Creator<SeeTheFutureCard> CREATOR = new Creator<SeeTheFutureCard>() {
        @Override
        public SeeTheFutureCard createFromParcel(Parcel in) {
            return new SeeTheFutureCard(in);
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
    public void performAction() {

    }
}
