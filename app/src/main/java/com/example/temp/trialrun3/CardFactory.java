package com.example.temp.trialrun3;

import com.example.temp.trialrun3.Cards.AlterTheFutureCard;
import com.example.temp.trialrun3.Cards.AttackCard;
import com.example.temp.trialrun3.Cards.Card;
import com.example.temp.trialrun3.Cards.CardLockCard;
import com.example.temp.trialrun3.Cards.DeathCard;
import com.example.temp.trialrun3.Cards.DiscardHandCard;
import com.example.temp.trialrun3.Cards.SaveCard;
import com.example.temp.trialrun3.Cards.SeeTheFutureCard;
import com.example.temp.trialrun3.Cards.SkipYourTurnCard;
import com.example.temp.trialrun3.Cards.TakeACard;
import com.example.temp.trialrun3.Cards.TransformationCard;

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

    public Card makeTransformationCard()
    {
        return new TransformationCard();
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
