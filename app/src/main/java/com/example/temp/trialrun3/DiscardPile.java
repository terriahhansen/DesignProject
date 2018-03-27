package com.example.temp.trialrun3;

import com.example.temp.trialrun3.Cards.Card;

import java.util.ArrayList;

/**
 * Created by Sheena on 2018-03-26.
 */

public class DiscardPile {
    private static final DiscardPile discardPile = null;      //singleton
    private ArrayList<Card> discardPileCards = null;

    private DiscardPile() {
        discardPileCards = new ArrayList<Card>();
    }

    public static DiscardPile getDiscardPile()
    {
        if (discardPile == null) {
            return new DiscardPile();
        }
        else {
            return discardPile;
        }
    }
    public void add(Card card)
    {
        discardPileCards.add(card);
    }


}
