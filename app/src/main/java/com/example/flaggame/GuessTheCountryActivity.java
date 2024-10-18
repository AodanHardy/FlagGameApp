package com.example.flaggame;

import static com.example.flaggame.Constants.GREEN;
import static com.example.flaggame.Constants.NUM_OF_COUNTRIES;
import static com.example.flaggame.Constants.RED;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;


public class GuessTheCountryActivity extends AppCompatActivity {
    private static final String
            WIN_MSG = "That is the correct Answer!!",
            LOSE_MSG = "Incorect: The Answer is ";
    private String correctAnswer;
    private boolean isAnswered = false;
    private List<Country> randomOrderCountryList;
    private ImageView imageView;
    private TextView answerLabelTextView;
    private Button submitOrNextBtn, backBtn;
    private Spinner countriesSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_the_country);

        // Init flag manager
        CountryManager countryManager = new CountryManager(this);

        // populate list with countries in random order
        randomOrderCountryList = countryManager.getRandomList(NUM_OF_COUNTRIES);

        // setting imageview and btn
        imageView = findViewById(R.id.guessTheCountryImageView);
        submitOrNextBtn = findViewById(R.id.guessTheCountrySubmitBtn);
        answerLabelTextView = findViewById(R.id.guessTheCountryCorrectAnswerTextView);

        // Setting up spinner
        countriesSpinner = findViewById(R.id.guessTheCountrySpinner);
        List<String> countryNames = countryManager.getCountryNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, countryNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countriesSpinner.setAdapter(adapter);

        this.gameRefresh();


        backBtn = findViewById(R.id.guessTheCountryBackBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuessTheCountryActivity.this, NewCountryGameActivity.class);
                startActivity(intent);
            }
        });

        // submit button clicked
        submitOrNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // need to check if isAnswered is true - if so, then reset warning labels,
                // and call gameRefresh()
                if (isAnswered){
                    gameRefresh();
                }else {
                    // if not need to call checkAnswer
                    checkAnswer(countriesSpinner.getSelectedItem().toString());
                    // next i need to change text in button to Next, and set isAnswered to true
                    isAnswered = true;
                    countriesSpinner.setEnabled(false);
                    submitOrNextBtn.setText("Next");
                }

            }
        });

    }

    /** gameRefresh will setup game each time:
            * Get next country in list
            * display it in imageField
            * set country name to correct answer
     */

    private void gameRefresh(){
        // clear warnings
        this.answerLabelTextView.setText("");


        // reset isAnswered
        isAnswered = false;
        countriesSpinner.setEnabled(true);

        // reset Button back to submit
        submitOrNextBtn.setText("Submit");


        // need to put in a check if list is empty, is so end game
        Country displayCountry = randomOrderCountryList.get(0);
        imageView.setImageResource(displayCountry.getImageInt());
        this.correctAnswer = displayCountry.getCountryName();

        // remove country from list
        this.randomOrderCountryList.remove(displayCountry);
    }

    private void checkAnswer(String answer){
        // if correct - display green
         if (Objects.equals(this.correctAnswer, answer)) {
             answerLabelTextView.setText(WIN_MSG);
             answerLabelTextView.setTextColor(Color.parseColor(GREEN));
         }

        // if not - display red and correct answer

        else {
            answerLabelTextView.setText(LOSE_MSG + this.correctAnswer);
            answerLabelTextView.setTextColor(Color.parseColor(RED));
        }

    }
}