package com.example.flaggame;

import static com.example.flaggame.Constants.GREEN;
import static com.example.flaggame.Constants.NUM_OF_COUNTRIES;
import static com.example.flaggame.Constants.RED;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GuessHintsActivity extends AppCompatActivity {
    CountryManager countryManager;
    private List<Country> countryList;
    private ImageView flagImage;
    private TextView guessTextView, livesTextView, msgTextView;
    private Button submitBtn;
    private EditText guessField;
    private String roundCountryName, guessedCountryName;
    private int lives;
    private boolean roundFinished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_hints);

        countryManager = new CountryManager(this);

        countryList = countryManager.getRandomList(NUM_OF_COUNTRIES);

        this.flagImage = findViewById(R.id.guessHintFlagImage);
        this.guessTextView = findViewById(R.id.guessHintGuessTextView);
        this.submitBtn = findViewById(R.id.guessHintSubmitbtn);
        this.guessField = findViewById(R.id.guessHintTextField);
        this.livesTextView = findViewById(R.id.guessHintsLivesTxt);
        this.msgTextView = findViewById(R.id.guessedHintMsgTxt);


        this.newRoundRefresh();



        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (roundFinished){
                    newRoundRefresh();
                }

                // retrive guess
                String guess = guessField.getText().toString();

                // check if guess is valid and convert to char
                if (isValidGuess(guess)){
                    char cGuess = guess.charAt(0);

                    // pass to method that will return true if char exists in
                    if (makeGuess(cGuess)){
                        // if it exists, pass to method that will replace "-" with char
                        replaceTemplateWithCorrectGuess(cGuess);

                        // check is round is won
                        if (checkIfWon()){
                            win();
                            finishRound();
                        }
                    }
                    else {
                        // if bad guess, take away life and tell user
                        lives--;
                        if (lives == 0){
                            fail();
                            finishRound();
                        }
                    }
                    refreshGuess();
                }


            }
        });


    }

    private void newRoundRefresh(){
        Country roundCountry = countryList.get(0);
        this.lives = 3;
        this.roundFinished = false;
        guessField.setEnabled(true);

        this.livesTextView.setText(String.valueOf(lives));
        // set/reset image
        this.flagImage.setImageResource(roundCountry.getImageInt());

        //reset button
        submitBtn.setText("Submit");

        //reset messages
        this.msgTextView.setText("");

        this.roundCountryName = roundCountry.getCountryName();
        this.guessedCountryName = getEmptyCountryName();
        this.guessTextView.setText(guessedCountryName);
        countryList.remove(roundCountry);

    }

    private String getEmptyCountryName(){
        char template = '-';
        StringBuilder fullTemplate = new StringBuilder();

        for (int i = 0; i < this.roundCountryName.length(); i++ )
            fullTemplate.append(template);
        return fullTemplate.toString();
    }

    private boolean isValidGuess(String userInput) {
        // Check if the input is a single character
        if (userInput.length() != 1) {
            // populate warning message
            return false;
        }

        // Check if the input is a letter
        char guessChar = userInput.charAt(0);
        if (!Character.isLetter(guessChar)) {
            // populate warning message

            return false;
        }

        // If all checks pass, the input is valid
        return true;
    }


    private boolean makeGuess(char c){
        return this.roundCountryName.toLowerCase().indexOf(Character.toLowerCase(c)) != -1;
    } // returns true if char is in country name

    private void replaceTemplateWithCorrectGuess(char c) {
        // Convert guessedCountryName and roundCountryName to char arrays
        char[] guessedChars = guessedCountryName.toCharArray();
        char[] roundChars = roundCountryName.toCharArray();

        // Iterate over roundCountryName to check for the guessed character
        for (int i = 0; i < roundChars.length; i++) {
            if (Character.toLowerCase(roundChars[i]) == Character.toLowerCase(c)) {
                // Replace dash in guessedCountryName with the correct character
                guessedChars[i] = roundChars[i];
            }
        }

        // Update guessedCountryName with the new string after replacing correct guesses
        guessedCountryName = new String(guessedChars);
    }


    private void refreshGuess(){
        // refresh guessTextview and reset warnings
        this.guessTextView.setText(guessedCountryName);
        this.guessField.setText("");
        this.livesTextView.setText(String.valueOf(this.lives));
    }

    private void fail(){
        this.msgTextView.setTextColor(Color.parseColor(RED));
        this.msgTextView.setText("WRONG! The correct answer is " + roundCountryName);

    }

    private boolean checkIfWon() {
        return !guessedCountryName.contains("-");
    }

    private void win(){
        this.msgTextView.setTextColor(Color.parseColor(GREEN));
        this.msgTextView.setText("CORRECT! YOU WIN!");
    }
    private void finishRound(){
        guessField.setEnabled(false);
        this.roundFinished = true;
        submitBtn.setText("Next");
    }
}