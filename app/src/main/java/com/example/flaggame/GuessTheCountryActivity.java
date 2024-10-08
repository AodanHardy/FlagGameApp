package com.example.flaggame;

import static com.example.flaggame.Constants.NUM_OF_COUNTRIES;

import androidx.appcompat.app.AppCompatActivity;

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
    private String correctAnswer;

    private boolean isAnswered = false;
    private List<Country> randomOrderCountryList;
    private FlagManager flagManager;

    private ImageView imageView;
    private TextView warningTextView;
    private TextView correctAnswerTextView;

    private Button submitOrNextBtn;
    private Spinner countriesSpinner;
    private List<String> countryNames;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_the_country);

        // Init flag manager
        flagManager = new FlagManager(this);

        // populate list with countries in random order
        randomOrderCountryList = flagManager.getRandomList(NUM_OF_COUNTRIES);

        // setting imageview and btn
        imageView = findViewById(R.id.guessTheCountryImageView);
        submitOrNextBtn = findViewById(R.id.guessTheCountrySubmitBtn);
        warningTextView = findViewById(R.id.guessTheCountryCorrectAnswerText);
        correctAnswerTextView = findViewById(R.id.guessTheCountryCorrectAnswerTextView);

        // Setting up spinner
        countriesSpinner = findViewById(R.id.guessTheCountrySpinner);
        countryNames = flagManager.getCountryNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, countryNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countriesSpinner.setAdapter(adapter);


        this.gameRefresh();



        // submit button clicked
        submitOrNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // need to check if isAnswered is true - if so, then reset warning labels,
                // and call gameRefresh()
                if (isAnswered == true){
                    gameRefresh();
                }else {

                    // if not need to call checkAnswer
                    checkAnswer(countriesSpinner.getSelectedItem().toString());
                    // next i need to change text in button to Next, and set isAnswered to true
                    isAnswered = true;
                    submitOrNextBtn.setText("NEXT");
                }

            }
        });

    }

    /* gameRefresh will setup game each time:
            * Get next country in list
            * display it in imageField
            * set country name to correct answer

      Should be called onCreate and once user clicks next
     */

    private void gameRefresh(){
        // clear warnings
        this.correctAnswerTextView.setText("");
        this.warningTextView.setText("");

        // reset isAnswered
        isAnswered = false;

        // reset Button back to submit
        submitOrNextBtn.setText("Submit");


        // need to put in a check if list is empty, is so end game
        Country displayCountry = randomOrderCountryList.get(0);
        imageView.setImageResource(displayCountry.getImageInt());
        this.correctAnswer = displayCountry.getCountryName();

        // remove country from list
        this.randomOrderCountryList.remove(displayCountry);


    }


    /* checkAnswer will
            * compare users selected answer with correct answer
            * Displays either green or red message depending on answer

         If Correct;
            * updates score
            * updates button to say next
            * reset bool answerSubmitted
            *
         If incorrect:
         * display red medsage with correct answer
         * change
         *
     */
    private void checkAnswer(String answer){

        // if correct - display green
         if (Objects.equals(this.correctAnswer, answer)){
             correctAnswerTextView.setText("That is the correct Answer!!");
         }
        // if not - display red and correct answer
        else {
            warningTextView.setText("Incorect: The Answer is " + this.correctAnswer);
         }
    }
}