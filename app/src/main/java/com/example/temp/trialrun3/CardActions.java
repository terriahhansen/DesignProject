package com.example.temp.trialrun3;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.example.temp.trialrun3.Cards.AlterTheFutureCard;
import com.example.temp.trialrun3.Cards.Card;

/**
 * Created by temp on 3/4/2018.
 */

public class CardActions {

    public static void Attack()
    {

    }
    public static void SeeTheFuture(GameView gameview){
        Deck deck = Deck.getDeck();
        for ( int i = 0; i <3 ; i++) {
            Card card = deck.getDeckCards().get(i);
            ConstraintLayout constraintLayout = gameview.findViewById(R.id.seeTheFutureContraint);
            constraintLayout.setVisibility(View.VISIBLE);
            LinearLayout linearLayout = (LinearLayout) gameview.findViewById(R.id.seeTheFutureView);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            Button button = new Button(gameview);
            button.setText(String.valueOf(i));
            button.setTextScaleX(5f);
            button.setLayoutParams(layoutParams);
            gameview.getImageButton(card, button);
            linearLayout.addView(button);
        }
    }
    public static void AlterTheFuture(){

    }
}
