package com.example.flaggame;

import static com.example.flaggame.Constants.BLACK;
import static com.example.flaggame.Constants.BLUE;
import static com.example.flaggame.Constants.GREEN;
import static com.example.flaggame.Constants.GREY;
import static com.example.flaggame.Constants.RED;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdvancedLevelActivity extends AppCompatActivity {
    private static final int NUM_ALLOWED_ATTEMPTS = 3;
    private static final String
            WIN_MSG = "CORRECT!!",
            FAIL_MSG = "INCORRECT!!",
            TOO_MANY_ATTEMPTS_MSG = "Too Many Incorrect Attempts",
            SCORE_SUMMARY_MSG = "Your Score is: ";
    private CountryManager countryManager;
    private ImageView image1, image2, image3;
    private EditText textBox1, textBox2,textBox3;
    private boolean q1Correct, q2Correct, q3Correct, isRoundComplete;
    private Button submitBtn, backBtn;
    private TextView scoreTxt, correctTxt;

    private List<Country> roundCountries;

    private int roundScore, roundAttempts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_level);

        countryManager = new CountryManager(this);

        image1 = findViewById(R.id.advancedLvlImg1);
        image2 = findViewById(R.id.advancedLvlImg2);
        image3 = findViewById(R.id.advancedLvlImg3);
        textBox1 = findViewById(R.id.advancedLvlTxt1);
        textBox2 = findViewById(R.id.advancedLvlTxt2);
        textBox3 = findViewById(R.id.advancedLvlTxt3);
        submitBtn = findViewById(R.id.advancedLvlSubmitBtn);
        scoreTxt = findViewById(R.id.advancedLvlScoreTxt);
        correctTxt = findViewById(R.id.advancedLvlCorrectTxt);

        gameSetUp();

        // Back Button
        backBtn = findViewById(R.id.advancedLvlBackBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdvancedLevelActivity.this, NewCountryGameActivity.class);
                startActivity(intent);
            }
        });
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check if round finished is true
                // if so: refresh game
                if (isRoundComplete){
                    gameSetUp();
                }

                else {
                    if (roundAttempts == NUM_ALLOWED_ATTEMPTS) {
                        // give warning that round is over
                        completeRound(TOO_MANY_ATTEMPTS_MSG, RED);

                    } else {
                        // check answers
                        q1Correct = checkAnswer(roundCountries.get(0), textBox1);
                        q2Correct = checkAnswer(roundCountries.get(1), textBox2);
                        q3Correct = checkAnswer(roundCountries.get(2), textBox3);


                        // if all answers are correct, then enable nextBtn,
                        // roundComplete = false, display message and restart game
                        if (q1Correct && q2Correct && q3Correct) {
                            // put this in a method
                            completeRound(WIN_MSG, GREEN);
                        } else {
                            correctTxt.setText(FAIL_MSG);
                            correctTxt.setTextColor(Color.parseColor(RED));
                            roundAttempts++;
                        }
                    }
                }
            }
        });
    }
    private void gameSetUp(){
        roundCountries = countryManager.getRandomList(3);
        image1.setImageResource(roundCountries.get(0).getImageInt());
        image2.setImageResource(roundCountries.get(1).getImageInt());
        image3.setImageResource(roundCountries.get(2).getImageInt());

        roundScore = 0;roundAttempts = 1;

        // set all booleans to false
        q1Correct = false; q2Correct = false; q3Correct = false; isRoundComplete=false;

        // reset editText fields, text and colours
        textBox1.setEnabled(true);textBox2.setEnabled(true);textBox3.setEnabled(true);
        textBox1.setText("");textBox2.setText("");textBox3.setText("");
        textBox1.setTextColor(Color.parseColor(GREY));
        textBox2.setTextColor(Color.parseColor(GREY));
        textBox3.setTextColor(Color.parseColor(GREY));

        correctTxt.setText("");
        scoreTxt.setText("");


        // reset button back to submit
        submitBtn.setText("Submit");
    }

    private boolean checkAnswer(Country country, EditText editText){
        String ans = editText.getText().toString().toLowerCase();
        String correctAns = country.getCountryName().toLowerCase();

        if (ans.equals(correctAns)){

            // disable edit text
            editText.setEnabled(false);
            // Change colour in case its still red
            editText.setTextColor(Color.parseColor(GREEN));

            // return true
            return true;
        }
        else{
            editText.setTextColor(Color.parseColor(RED));
            return false;
        }

    }

    private void completeRound(String message, String colour){
        // check if answer is correct, if so add to score,
        // if not - call setEditTextIncorrect
        if (q1Correct)roundScore++;
        else setEditTextIncorrect(textBox1, roundCountries.get(0).getCountryName());

        if (q2Correct)roundScore++;
        else setEditTextIncorrect(textBox2, roundCountries.get(1).getCountryName());

        if (q3Correct)roundScore++;
        else setEditTextIncorrect(textBox3, roundCountries.get(2).getCountryName());

        isRoundComplete = true;
        submitBtn.setText("Next");
        scoreTxt.setText(SCORE_SUMMARY_MSG + this.roundScore);
        correctTxt.setText(message);
        correctTxt.setTextColor(Color.parseColor(colour));
    }

    /** setEditTextIncorrect is called once
     * @param editText to change.
     * @param correctAns to replace wrong answer.
     */
    private void setEditTextIncorrect(EditText editText,String correctAns){
        editText.setTextColor(Color.parseColor(BLUE));
        editText.setText(correctAns);
        editText.setEnabled(false);
    }
}