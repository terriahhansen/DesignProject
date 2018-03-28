package com.example.temp.trialrun3;

import android.app.Activity;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.temp.trialrun3.Cards.AttackCard;
import com.example.temp.trialrun3.Cards.Card;
import com.example.temp.trialrun3.Cards.DeathCard;
import com.example.temp.trialrun3.Cards.SaveCard;

import java.util.ArrayList;

/**
 * Created by Sheena on 2018-03-04.
 */

public class RealPlayer implements Player, Parcelable {
    private int numOfCards;
    private int playerNumber;
    private boolean isAlive = true;
    private ArrayList<Card> hand = new ArrayList<>();
    private boolean canPlay;
    private boolean isHost;

    public RealPlayer(int playerNumber) {
        this.playerNumber = playerNumber;
        if(playerNumber==0)
        {
            this.isHost = true;
        }
    }

    protected RealPlayer(Parcel parcel) {
        numOfCards = parcel.readInt();
        playerNumber = parcel.readInt();
        isAlive = parcel.readByte() != 0;
        canPlay = parcel.readByte() != 0;
        isHost = parcel.readByte() != 0;
        hand = (ArrayList<Card>) parcel.readSerializable();
    }

    public static final Creator<RealPlayer> CREATOR = new Creator<RealPlayer>() {
        @Override
        public RealPlayer createFromParcel(Parcel parcel) {
            return new RealPlayer(parcel);
        }

        @Override
        public RealPlayer[] newArray(int size) {
            return new RealPlayer[size];
        }
    };

    public String toString()
    {
        StringBuilder playerRepresentation = new StringBuilder().append("Player ")
                .append(Integer.toString(playerNumber)).append(":                    ");
        if (isHost)
        {
            playerRepresentation.append("YOU");
        }
        else
        {
            playerRepresentation.append("CONNECTED PLAYER");
        }

        return playerRepresentation.toString();
    }
    public boolean isHost(){
        return isHost;
    }

    @Override
    public void setIsHost(boolean flag) {
        this.isHost = flag;
    }

    @Override
    public void setIsAlive(boolean flag) {
        this.isAlive = flag;
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }

    public void playCard(Card cardToPlay){

    }

    public Card drawCard(String gameMode){
        Deck deck = Deck.getDeck();
        Card c = deck.draw();
        if (c instanceof DeathCard)
        {
            for (int i=0; i<hand.size(); i++)
            {
                if (hand.get(i) instanceof SaveCard)
                {
                    DiscardPile.getDiscardPile().add(c);
                    playCard(hand.get(i));
                    return null;
                }
            }
            if(ChooseMode.SINGLE_PLAYER.equals(gameMode))
            {
                //go to you loose
            }
            setIsAlive(false);
            return null;
        }
        else
        {
            addToHand(c);
            return c;
        }
    }
    public void addToHand(Card cardToAdd){
        hand.add(cardToAdd);
        numOfCards++;
    }
    public void setCanPlay(boolean isActivePlayer){
        canPlay = isActivePlayer;
    }

    @Override
    public boolean canPlay() {
        return canPlay;
    }

    public boolean playMultipleCards(Card[] cardsToPlay){

        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(numOfCards);
        parcel.writeInt(playerNumber);
        parcel.writeByte((byte) (isAlive ? 1 : 0));
        parcel.writeByte((byte) (canPlay ? 1 : 0));
        parcel.writeByte((byte) (isHost ? 1 : 0));
        parcel.writeSerializable(hand);
    }

    public ArrayList<Card> getHand(){
        return hand;
    }
}
