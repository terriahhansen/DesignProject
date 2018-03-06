package com.example.temp.trialrun3;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sheena on 2018-03-06.
 */

public class CardLockCard implements Card, Parcelable {

    private final String cardType = this.getClass().toString();
    private static final String cardTitle = "Locked out of the Lab";
    private static final String actionDescription = "Select a player to lock their save card for a turn";

    protected CardLockCard(Parcel in) {
    }

    public static final Creator<CardLockCard> CREATOR = new Creator<CardLockCard>() {
        @Override
        public CardLockCard createFromParcel(Parcel in) {
            return new CardLockCard(in);
        }

        @Override
        public CardLockCard[] newArray(int size) {
            return new CardLockCard[size];
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
