package com.example.temp.trialrun3.Cards;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.temp.trialrun3.CardActions;
import com.example.temp.trialrun3.GameView;

import java.util.logging.Logger;

/**
 * Created by temp on 3/4/2018.
 */

public class AttackCard implements Card, Parcelable
{
    public static final int ID_NUMBER = 1;
    private final String cardType = this.getClass().toString();
    private static final String cardTitle = "Virus Detected!";
    private static final String actionDescription = "End your turn. \n" +
            "The next player must take two turns.";
    private static final int score = 7;
    private static Logger log;


    public AttackCard()
    {

    }
    private AttackCard(Parcel parcel) throws Exception {

        if (!this.cardType.equals(parcel.readString()))
        {
          throw new Exception();
        }
    }

    public static final Creator<AttackCard> CREATOR = new Creator<AttackCard>() {
        @Override
        public AttackCard createFromParcel(Parcel parcel) {
            try
            {
                return new AttackCard(parcel);
            }
            catch (Exception e)
            {
               log.severe("Attempted creation of an Attack Card from a parcel of different source type"+ e.toString());
            }
            return null;
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
    public void writeToParcel(Parcel parcel, int flags) {

        parcel.writeString(cardType);
    }

    @Override
    public void performAction(GameView gameView) {

        CardActions.Attack();
    }

    @Override
    public int getScore() {
        return score;
    }
}
