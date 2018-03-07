package com.example.temp.trialrun3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DeckCreator extends AppCompatActivity {

    private static final int deckConfirm_ActionCode =1;
    private int numOfCardsSelected =0;
    private static final int maxNumOfCards = 5;
    private Map <Integer, Boolean> checkBoxValues = new HashMap<>();
    private ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    private int idNum = 1;
    public static final String checkBoxValueMap = "com.example.trialrun3.CHECK_BOX_VALUE_MAP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_creator);
        final Button confirmButton = findViewById(R.id.button7);
        confirmButton.setClickable(false);

        CompoundButton.OnCheckedChangeListener checkBoxListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
            {
                if (isChecked)
                {
                    numOfCardsSelected++;
                }
                else
                {
                    confirmButton.setClickable(false);
                    numOfCardsSelected--;
                }
                if (numOfCardsSelected == maxNumOfCards)
                {
                    confirmButton.setClickable(true);
                    for (int i = 0; i<checkBoxes.size(); i++)
                    {
                        CheckBox c = checkBoxes.get(i);
                        if (!c.isChecked())
                        {
                            c.setClickable(false);
                        }
                    }
                }
            }
        };

        CheckBox checkBox1 = findViewById(R.id.checkBox);
        checkBox1.setId(idNum++);
        checkBox1.setOnCheckedChangeListener(checkBoxListener);
        checkBoxes.add(checkBox1);
        CheckBox checkBox2 = findViewById(R.id.checkBox2);
        checkBox2.setId(idNum++);
        //etc...

    }


    public void confirmDeck (View view)
    {
        for (int j = 0; j<checkBoxes.size(); j++)
        {
            CheckBox c = checkBoxes.get(j);
            checkBoxValues.put(c.getId(), c.isChecked());
        }

        Intent intent = new Intent(this, Lobby.class);
        intent.putExtra(checkBoxValueMap, (Serializable) checkBoxValues);
        setResult(deckConfirm_ActionCode, intent);
        finish();
    }


}
