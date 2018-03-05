package com.example.temp.trialrun3;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by temp on 3/4/2018.
 */

public class AttackCard implements Card, Parcelable
{

    protected AttackCard()
    {

    }

    protected AttackCard(Parcel in) {
    }

    public static final Creator<AttackCard> CREATOR = new Creator<AttackCard>() {
        @Override
        public AttackCard createFromParcel(Parcel in) {
            return new AttackCard(in);
        }

        @Override
        public AttackCard[] newArray(int size) {
            return new AttackCard[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
