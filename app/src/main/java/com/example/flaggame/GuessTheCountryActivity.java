package com.example.flaggame;

import static com.example.flaggame.Constants.NUM_OF_COUNTRIES;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.List;


public class GuessTheCountryActivity extends AppCompatActivity {
    private String correctAnswer;
    private List<Country> randomOrderCountryList;
    private FlagManager flagManager;

    private int score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_the_country);

        // Init flag manager
        flagManager = new FlagManager(this);

        // populate list with countries in random order
        randomOrderCountryList = flagManager.getRandomList(NUM_OF_COUNTRIES);

    }

    /* gameRefresh will setup game each time:
            * Get next country in list
            * display it in imageField
            * set country name to correct answer

      Should be called onCreate and once user clicks next
     */

    private void gameRefresh(){}


    /* checkAnswer will
            * compare users selected answer with correct answer
            * Displays either green or red message depending on answer
            * updates score
     */
    private void checkAnswer(){}
}