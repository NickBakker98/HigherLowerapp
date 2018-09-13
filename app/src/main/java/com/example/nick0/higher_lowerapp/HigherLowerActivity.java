//Created by Nick Bakker (500763259)
//This app will allow you to guess if the number on the dice will be higher or lower than the
//current number. If you guess right, you get a point.
package com.example.nick0.higher_lowerapp;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HigherLowerActivity extends AppCompatActivity {

    //Adding variables to the app
    private TextView mScoreValue;
    private TextView mHighScoreValue;
    private ListView mScoreList;
    private ArrayAdapter<String> mAdapter;
    private List<String> mThrows;
    private ImageView mDiceImage;
    private FloatingActionButton mLowerButton;
    private FloatingActionButton mHigherButton;
    private int[] mImageNames;
    private int randomImageIndex = 0;
    private int previousScore = 0;
    private int lastScore = 0;
    private TextView mWinLose;
    private int score;
    private int highscore;
    private long filthyHack;

    Random r;

    private void updateUI() {
        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mThrows);
            mScoreList.setAdapter(mAdapter);
        }
        else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_higher_lower);
        //Creating the list of which numbers where thrown
        mThrows = new ArrayList<>();

        //Creating a randomizer for the dice pictures
        r = new Random();

        //Linking the variables to the items in the xml file
        mScoreValue = findViewById(R.id.score_value);
        mHighScoreValue = findViewById(R.id.highscore_value);
        mScoreList = findViewById(R.id.score_list);
        mDiceImage = findViewById(R.id.dice_image);
        mLowerButton = findViewById(R.id.lower_button);
        mHigherButton = findViewById(R.id.higher_button);
        mWinLose = findViewById(R.id.win_lose_text);
        mImageNames = new int[]{R.drawable.d1, R.drawable.d2,R.drawable.d3,R.drawable.d4,R.drawable.d5,R.drawable.d6};


        //Code for the "Arrow down" button
        mLowerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                do {
                    //Random image from dice images will be generated
                    lastScore = (int) mImageNames[r.nextInt(mImageNames.length)];
                } while (lastScore == previousScore);
                //Display message which number was last thrown.
                filthyHack = (long) lastScore - 2131165275;
                mThrows.add("Throw is: " + String.valueOf(filthyHack));
                mDiceImage.setImageResource(lastScore);
                //Check with if-else statement if you won or lost. In case of a win, you get a point
                if (lastScore < previousScore) {
                    mWinLose.setText("You Win");
                    score++;
                    if (score > highscore)
                        highscore = score;
                }
                else {
                    mWinLose.setText("You Lose");
                    score = 0;
                }
                previousScore = lastScore;
                //Display the score and highscore.
                mScoreValue.setText(score + " ");
                mHighScoreValue.setText(highscore + " ");
                updateUI();
            }
        });

        //Code for the "Arrow up" button
        mHigherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                do {
                  //Random image from dice images will be generated
                    lastScore = (int) mImageNames[r.nextInt(mImageNames.length)];
                } while (lastScore == previousScore);
                filthyHack = (long) lastScore - 2131165275;
                mThrows.add("Throw is: " + String.valueOf(filthyHack));
                mDiceImage.setImageResource(lastScore);
                //Check with if-else statement if you won or lost. In case of a win, you get a point

                if (lastScore > previousScore) {
                    mWinLose.setText("You Win");
                    score++;
                    if (score > highscore)
                        highscore = score;
                }
                else {
                    mWinLose.setText("You Lose");
                    score = 0;
                }
                previousScore = lastScore;
                mScoreValue.setText(score + " ");
                mHighScoreValue.setText(highscore + " ");
                updateUI();
            }
        });
    }
}
 