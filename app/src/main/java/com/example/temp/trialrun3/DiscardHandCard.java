package com.example.temp.trialrun3;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sheena on 2018-03-06.
 */

public class DiscardHandCard implements Card, Parcelable {

    private final String cardType = this.getClass().toString();
    private static final String cardTitle = "The House Always Wins";
    private static final String actionDescription = "Discard your entire hand";

    protected DiscardHandCard(Parcel in) {
    }

    public static final Creator<DiscardHandCard> CREATOR = new Creator<DiscardHandCard>() {
        @Override
        public DiscardHandCard createFromParcel(Parcel in) {
            return new DiscardHandCard(in);
        }

        @Override
        public DiscardHandCard[] newArray(int size) {
            return new DiscardHandCard[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    @Override
    public void performAction() {

    }
}
