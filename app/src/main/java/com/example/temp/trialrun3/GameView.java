package com.example.temp.trialrun3;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

import static android.view.ViewGroup.LayoutParams.FILL_PARENT;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class GameView extends AppCompatActivity {

    private int numOfOpp;
    private int startingHandSize = 4;
    protected String gameMode;
    private ArrayList<Button> playersShown = new ArrayList<>();
    private ArrayList<Card> cardList = new ArrayList<Card>();
    public Deck deck = Deck.getDeck();  //singleton
    private ArrayList<Player> playerList = new ArrayList<Player>();
    private ArrayList<CheckBox> playerHostCards = new ArrayList<CheckBox>();
    private Player currentPlayer;
    private Button drawCardButton;
    private Button playCardButton;
    private static final String DISCARD_MESSAGE = "A Discard card was drawn, your hand has been discarded";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_view);
        numOfOpp = getIntent().getIntExtra(SelectNumOfPlayers.EXTRA_NUMOPPONENTS,0);
        int numOfPlayers = numOfOpp+1;
        cardList = getIntent().getParcelableArrayListExtra(Lobby.EXTRA_CARD_LIST);
        playerList = getIntent().getParcelableArrayListExtra(Lobby.EXTRA_PLAYER_LIST);
        gameMode = getIntent().getStringExtra(ChooseMode.EXTRA_GAMEMODE);
        drawCardButton = findViewById(R.id.drawCardButton);
        playCardButton = findViewById(R.id.playCardButton);

        opponentButtonSetup();
        setupDeckAndInitialPlayerHands(numOfPlayers);
        initializeButtons();
        startTurnRotation();

    }


    private void startTurnRotation()
    {
        Thread turnRotater = new Thread(new Runnable() {
            @Override
            public void run() {
                initiateTurnSequence();
            }
        });
        turnRotater.start();
    }

    private void initiateTurnSequence()
    {
        for (int i=0; i<playerList.size(); i++)
        {
            Player p = playerList.get(i);
            if(!p.isAlive())
            {
                playerList.remove(p);
                break;
            }
            currentPlayer = p;          //check that at this point the play button doesnt work
            p.setCanPlay(true);         //check that this also makes current player can play to be true
            while (p.canPlay())
            {
                if (p instanceof RealPlayer)
                {
                    if (p.isHost())
                    {
                        drawCardButton.setClickable(true);
                    }
                    else
                    {
                        //for multiplayer
                    }
                }
                else if (p instanceof AI)
                {
                    drawCardButton.setClickable(false);
                    ((AI) p).performTurnAlgorithm(this);
                }
            }

        }
        if (playerList.size()>1)
        {
            initiateTurnSequence();
        }
    }

    private void initializeButtons() {
        Button deckButton;
        CompoundButton.OnCheckedChangeListener listener = getListener();

        for ( CheckBox c : playerHostCards){
            c.setOnCheckedChangeListener(listener);
        }
        deckButton = findViewById(R.id.deckButton);
        int deckSize = deck.size();
        deckButton.setText(String.valueOf(deckSize));
        drawCardButton.setClickable(false);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupDeckAndInitialPlayerHands(int numOfPlayers) {

        CardFactory cardFactory = new CardFactory();
        for(Card c : cardList)
        {
            deck.addAll(cardFactory.createCardCopy(numOfPlayers*2,c));
        }
        deck.addAll(cardFactory.makeGenericCardSet(numOfPlayers*2));
        deck.shuffle();

        for (int i=0; i<startingHandSize; i++)
        {
            for (Player p : playerList)
            {
                Card card = deck.draw();
                p.addToHand(card);
                displayCards(p, card);
            }
        }
        for (Player p : playerList)
        {
            Card card = cardFactory.makeSaveCard();
            p.addToHand(card);
            displayCards(p,card);
        }
        deck.addAll(cardFactory.makeNumOfSaveCards(numOfPlayers));
        deck.addAll(cardFactory.makeNumOfDeathCards(numOfOpp));
        deck.shuffle();
    }

    private void discardCardCheck(Player player){
        Toast toast = Toast.makeText(this, DISCARD_MESSAGE, Toast.LENGTH_SHORT);
        if ( player  instanceof RealPlayer) {
            toast.show();
            for (int j = 0; j < playerHostCards.size(); j++) {
                CheckBox checkBox = playerHostCards.get(j);
                checkBox.setVisibility(View.GONE);
                Card c = player.getHand().get(j);
                changeDiscardPile(c);
                player.getHand().remove(j);
            }
        }
        else {
            for (int i = 1; i < playerList.size(); i++) {
                if (player == playerList.get(i)) {
                    for (Card c : player.getHand())
                        changeDiscardPile(c);
                    player.getHand().clear();
                    playersShown.get(i-1).setText("0");
                }
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void displayCards(Player player, Card card){
        if(card.getClass() == DiscardHandCard.class)
            discardCardCheck(player);

        else if ( player  instanceof RealPlayer) {
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.handView);
            CheckBox checkBox = new CheckBox(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT, 1.0f);
            checkBox.setLayoutParams(layoutParams);
            getImage(card, checkBox);
            CompoundButton.OnCheckedChangeListener listener = getListener();
            checkBox.setOnCheckedChangeListener(listener);
            playerHostCards.add(checkBox);
            linearLayout.addView(checkBox);
        }
        else {
            for (int i = 1; i < playerList.size(); i++) {
                if (player == playerList.get(i)) {
                    Button button = playersShown.get(i - 1);
                    addCardNum(button);
                }
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void getImage(Card card, CheckBox checkBox){
        checkBox.setButtonTintMode(PorterDuff.Mode.SRC_OVER);
        checkBox.setScaleX(0.90f);
        ColorStateList colorStateList = new ColorStateList(new int[][]{
                new int[]{-android.R.attr.state_enabled},
                new int[]{android.R.attr.state_enabled, -android.R.attr.state_checked},
                new int[]{android.R.attr.state_enabled, android.R.attr.state_checked}
        }, new int[]{
                getResources().getColor(R.color.colorNull),
                getResources().getColor(R.color.colorNull),
                getResources().getColor(R.color.colorClick)
        }
        );
        checkBox.setButtonTintList(colorStateList);
        if( card.getClass() == AttackCard.class){
            checkBox.setButtonDrawable(R.drawable.attack);
        }
        else if(card.getClass() == AlterTheFutureCard.class)
        {
            checkBox.setButtonDrawable(R.drawable.extension);
        }
        else if(card.getClass() == CardLockCard.class)
        {
            checkBox.setButtonDrawable(R.drawable.cardlock);
        }
        else if(card.getClass() == CivilGenericCard.class)
        {
            checkBox.setButtonDrawable(R.drawable.civil);
        }
        else if(card.getClass() == DeathCard.class)
        {
            checkBox.setButtonDrawable(R.drawable.death);
        }
        else if(card.getClass() == DiscardHandCard.class)
        {
            checkBox.setButtonDrawable(R.drawable.discard);
        }
        else if(card.getClass() == EceGenericCard.class)
        {
            checkBox.setButtonDrawable(R.drawable.ece);
        }
        else if(card.getClass() == MechGenericCard.class)
        {
            checkBox.setButtonDrawable(R.drawable.mech);
        }
        else if(card.getClass() == OnaeGenericCard.class)
        {
            checkBox.setButtonDrawable(R.drawable.onae);
        }
        else if(card.getClass() == ProcessGenericCard.class)
        {
            checkBox.setButtonDrawable(R.drawable.process);
        }
        else if(card.getClass() == SaveCard.class)
        {
            checkBox.setButtonDrawable(R.drawable.save);
        }
        else if(card.getClass() == SeeTheFutureCard.class)
        {
            checkBox.setButtonDrawable(R.drawable.seethefuture);
        }
        else if(card.getClass() == SkipYourTurnCard.class)
        {
            checkBox.setButtonDrawable(R.drawable.skipyourturn);
        }
        else if(card.getClass() == TakeACard.class)
        {
            checkBox.setButtonDrawable(R.drawable.collaboration);
        }
        else if(card.getClass() == TransformationCard.class)
        {
            checkBox.setButtonDrawable(R.drawable.transform);
        }

    }

    private void getDiscardImage(Card card, Button button){
        if( card.getClass() == AttackCard.class){
            button.setBackgroundResource(R.drawable.attack);
        }
        else if(card.getClass() == AlterTheFutureCard.class)
        {
            button.setBackgroundResource(R.drawable.extension);
        }
        else if(card.getClass() == CardLockCard.class)
        {
            button.setBackgroundResource(R.drawable.cardlock);
        }
        else if(card.getClass() == CivilGenericCard.class)
        {
            button.setBackgroundResource(R.drawable.civil);
        }
        else if(card.getClass() == DeathCard.class)
        {
            button.setBackgroundResource(R.drawable.death);
        }
        else if(card.getClass() == DiscardHandCard.class)
        {
            button.setBackgroundResource(R.drawable.discard);
        }
        else if(card.getClass() == EceGenericCard.class)
        {
            button.setBackgroundResource(R.drawable.ece);
        }
        else if(card.getClass() == MechGenericCard.class)
        {
            button.setBackgroundResource(R.drawable.mech);
        }
        else if(card.getClass() == OnaeGenericCard.class)
        {
            button.setBackgroundResource(R.drawable.onae);
        }
        else if(card.getClass() == ProcessGenericCard.class)
        {
            button.setBackgroundResource(R.drawable.process);
        }
        else if(card.getClass() == SaveCard.class)
        {
            button.setBackgroundResource(R.drawable.save);
        }
        else if(card.getClass() == SeeTheFutureCard.class)
        {
            button.setBackgroundResource(R.drawable.seethefuture);
        }
        else if(card.getClass() == SkipYourTurnCard.class)
        {
            button.setBackgroundResource(R.drawable.skipyourturn);
        }
        else if(card.getClass() == TakeACard.class)
        {
            button.setBackgroundResource(R.drawable.collaboration);
        }
        else if(card.getClass() == TransformationCard.class)
        {
            button.setBackgroundResource(R.drawable.transform);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Card drawCard(View view){
        Player p = currentPlayer;
        Card c = p.drawCard(gameMode);
        if (c!=null)
        {
            displayCards(p, c);
        }
        Button b = findViewById(R.id.deckButton);
        subtractCardNum(b);
        currentPlayer.setCanPlay(false);
        return c;
    }

    private void addCardNum(Button button){
       int num = Integer.parseInt((String) button.getText());
       num++;
       button.setText( (CharSequence) String.valueOf(num));
    }

    private void subtractCardNum(Button button){
       int num = Integer.parseInt((String) button.getText());
       num--;
       button.setText( (CharSequence) String.valueOf(num));
    }

    public void playCard(View view){
        Player p = currentPlayer;
        for (int i = 0; i< playerHostCards.size() ; i++){
            CheckBox c = playerHostCards.get(i);
            if( c.isChecked()){
                c.setVisibility(View.GONE);
                playerHostCards.remove(i);
                Card card = p.getHand().get(i);
                card.performAction();
                changeDiscardPile(card);
                p.getHand().remove(i);
                for (int j = 0; j < playerHostCards.size(); j++) {
                    CheckBox ck = playerHostCards.get(j);
                    ck.setClickable(true);
                }
            }
        }
    }

    protected void changeDiscardPile(Card card){
        Button b = findViewById(R.id.discardPileButton);
        getDiscardImage(card,b);
        DiscardPile.getDiscardPile().add(card);
    }

    public CompoundButton.OnCheckedChangeListener getListener() {
        playCardButton.setClickable(false);
        CompoundButton.OnCheckedChangeListener checkBoxListener2 = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                int numOfCardsSelected = 0;
                if (isChecked) {
                    numOfCardsSelected++;
                } else {
                    numOfCardsSelected--;
                    for (int i = 0; i < playerHostCards.size(); i++) {
                        CheckBox c = playerHostCards.get(i);
                        c.setClickable(true);
                    }
                }
                if (numOfCardsSelected == 1) {
                    if (currentPlayer.isHost()&&currentPlayer.canPlay())
                    {
                        playCardButton.setClickable(true);
                    }
                    for (int i = 0; i < playerHostCards.size(); i++) {
                        CheckBox c = playerHostCards.get(i);
                        if (!c.isChecked()) {
                            c.setClickable(false);
                        }
                    }
                }
            }
        };
        return checkBoxListener2;
    }

    private void opponentButtonSetup(){
        Button button = findViewById(R.id.player1Button);
        playersShown.add(button);
        button = findViewById(R.id.player2Button);
        playersShown.add(button);
        button = findViewById(R.id.player3Button);
        playersShown.add(button);
        button = findViewById(R.id.player4Button);
        playersShown.add(button);
        button = findViewById(R.id.player5Button);
        playersShown.add(button);

        for( Button b : playersShown){
            b.setText("0");
        }

        for( int i=0; i<numOfOpp; i++){
            playersShown.get(i).setVisibility(View.VISIBLE);
        }

    }
}
