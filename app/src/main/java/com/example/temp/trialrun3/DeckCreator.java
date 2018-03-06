package com.example.temp.trialrun3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class DeckCreator extends AppCompatActivity {

    private ArrayList<Card> CardList;
    private int numOfCardsSelected;
    private static final int maxNumOfCards = 5;
    private Map <Integer, Boolean> checkBoxValues;
    private ArrayList<CheckBox> checkBoxes;
    private int i = 1;
    public static final String checkBoxValueMap = "com.example.trialrun3.CHECK_BOX_VALUE_MAP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_creator);

        CheckBox checkBox1 = findViewById(R.id.checkBox);
        checkBox1.setId(i++);
//        CheckBox checkBox2 = findViewById(R.id.checkBox2);
//        checkBox2.setId(i++);
        //etc...
    }


    public void confirmDeck (View view)
    {
        for (CheckBox c : checkBoxes)
        {
           checkBoxValues.put(c.getId(), c.isChecked());
        }
        Intent intent = new Intent(this, Lobby.class);
        intent.putExtra(checkBoxValueMap, (Serializable) checkBoxValues);
        setResult(DeckCreator.RESULT_OK, intent);
    }


}
