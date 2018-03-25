package com.example.temp.trialrun3;

import com.example.temp.trialrun3.Cards.AlterTheFutureCard;
import com.example.temp.trialrun3.Cards.AttackCard;
import com.example.temp.trialrun3.Cards.Card;
import com.example.temp.trialrun3.Cards.CardLockCard;
import com.example.temp.trialrun3.Cards.CivilGenericCard;
import com.example.temp.trialrun3.Cards.DeathCard;
import com.example.temp.trialrun3.Cards.DiscardHandCard;
import com.example.temp.trialrun3.Cards.EceGenericCard;
import com.example.temp.trialrun3.Cards.MechGenericCard;
import com.example.temp.trialrun3.Cards.OnaeGenericCard;
import com.example.temp.trialrun3.Cards.ProcessGenericCard;
import com.example.temp.trialrun3.Cards.SaveCard;
import com.example.temp.trialrun3.Cards.SeeTheFutureCard;
import com.example.temp.trialrun3.Cards.SkipYourTurnCard;
import com.example.temp.trialrun3.Cards.TakeACard;
import com.example.temp.trialrun3.Cards.TransformationCard;

import java.util.ArrayList;

/**
 * Created by temp on 3/4/2018.
 */

class CardFactory {

    public Card makeAttackCard()
    {
        return new AttackCard();
    }

    public Card makeSeeTheFutureCard()
    {
        return new SeeTheFutureCard();
    }

    public Card makeDiscardHandCard()
    {
        return new DiscardHandCard();
    }

    public Card makeCardLockCard()
    {
        return new CardLockCard();
    }

    public Card makeTakeACard()
    {
        return new TakeACard();
    }

    public Card makeSkipTurnCard()
    {
        return new SkipYourTurnCard();
    }

    public Card makeAlterTheFutureCard()
    {
        return new AlterTheFutureCard();
    }

    public Card makeDeathCard()
    {
        return new DeathCard();
    }

    public Card makeSaveCard()
    {
        return new SaveCard();
    }

    public ArrayList<Card> makeNumOfSaveCards(int numToMake)
    {
        ArrayList<Card> saveCards = new ArrayList<Card>();
        for (int i=0; i<numToMake; i++)
        {
            saveCards.add(makeSaveCard());
        }
        return saveCards;
    }

    public ArrayList<Card> makeNumOfDeathCards(int numToMake)
    {
        ArrayList<Card> deathCards = new ArrayList<Card>();
        for (int i=0; i<numToMake; i++)
        {
            deathCards.add(makeDeathCard());
        }
        return deathCards;
    }

    public Card makeTransformationCard()
    {
        return new TransformationCard();
    }

    public ArrayList<Card> makeGenericCardSet(int numOfEachCard)
    {
        ArrayList<Card> GenericCards = new ArrayList<Card>();
        for (int i=0; i<numOfEachCard; i++)
        {
            GenericCards.add(new EceGenericCard());
            GenericCards.add(new CivilGenericCard());
            GenericCards.add(new MechGenericCard());
            GenericCards.add(new ProcessGenericCard());
            GenericCards.add(new OnaeGenericCard());

        }
        return GenericCards;
    }

    public ArrayList<Card> createCardCopy(int numOfCopies, Card cardToCreate)
    {
        ArrayList<Card> cardCopies = new ArrayList<Card>();

        if(cardToCreate instanceof AlterTheFutureCard)
        {
            cardCopies.add(new AlterTheFutureCard());
            numOfCopies--;
        }
        else if (cardToCreate instanceof AttackCard)
        {
            cardCopies.add(new AttackCard());
            numOfCopies--;
        }
        else if (cardToCreate instanceof CardLockCard)
        {
            cardCopies.add(new CardLockCard());
            numOfCopies--;
        }
        else if (cardToCreate instanceof DiscardHandCard)
        {
            cardCopies.add(new DiscardHandCard());
            numOfCopies--;
        }
        else if (cardToCreate instanceof SeeTheFutureCard)
        {
            cardCopies.add(new SeeTheFutureCard());
            numOfCopies--;
        }
        else if (cardToCreate instanceof SkipYourTurnCard)
        {
            cardCopies.add(new SkipYourTurnCard());
            numOfCopies--;
        }
        else if (cardToCreate instanceof TakeACard)
        {
            cardCopies.add(new TakeACard());
            numOfCopies--;
        }
        else if (cardToCreate instanceof TransformationCard)
        {
            cardCopies.add(new TransformationCard());
            numOfCopies--;
        }

        if (numOfCopies!=0)
        {
            cardCopies.addAll(createCardCopy(numOfCopies, cardToCreate));
        }

        return cardCopies;
    }

//    public Card make3OfAKindCard()
//    {
//        return new ThreeOfAKindRuleCard();
//    }
//
//    public Card make5DiffirentCard()
//    {
//        return new FiveDifferentRuleCard();
//    }

}
