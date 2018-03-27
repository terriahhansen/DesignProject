package com.example.temp.trialrun3;

import com.example.temp.trialrun3.Cards.Card;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Logger;

/**
 * Created by temp on 3/25/2018.
 */

public class Deck {

    private Logger log;
    private static final Deck deck = null;      //singleton
    private ArrayList<Card> deckCards = null;

    private Deck()
    {
       deckCards = new ArrayList<Card>();
    }

    public static Deck getDeck()
    {
        if (deck == null)
        {
            return new Deck();
        }
        else
        {
            return deck;
        }
    }
    public void add(Card card)
    {
        deckCards.add(card);
    }

    public void addAll(Collection<Card> cards)
    {
        deckCards.addAll(cards);
    }

    public void shuffle()
    {
        Collections.shuffle(deckCards);
    }

    public Card draw()
    {
        if (deckCards.size()>0)
        {
            Card drawnCard = deckCards.get(0);
            deckCards.remove(0);
            return drawnCard;
        }
        else
        {
            log.severe("there are no cards left in the deck to draw. this is an error state, the game should be over.");
            return null;
        }
    }
    public ArrayList<Card> getDeckCards(){
        return deckCards;
    }
}
