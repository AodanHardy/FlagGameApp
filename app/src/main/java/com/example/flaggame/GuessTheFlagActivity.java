package com.example.flaggame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.awt.font.TextAttribute;
import java.util.List;
import java.util.Random;

public class GuessTheFlagActivity extends AppCompatActivity {

    private List<Country> countryList;
    private Country correctCountry;
    private CountryManager countryManager;

    private final Random random = new Random();


    private TextView correctAnswerTextView;
    private TextView winMsgTxt;
    private TextView loseMsgTxt;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private Button nextBtn;

    private boolean roundEnded;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_the_flag);

        countryManager = new CountryManager(this);

        correctAnswerTextView = findViewById(R.id.guessTheFlagCorrectAnswerTextView);
        winMsgTxt = findViewById(R.id.guessTheFlagWinMsgTxt);
        loseMsgTxt = findViewById(R.id.guessTheFlagLoseMsgTxt);

        imageView1 = findViewById(R.id.guessTheFlagImg1);
        imageView2 = findViewById(R.id.guessTheFlagImg2);
        imageView3 = findViewById(R.id.guessTheFlagImg3);

        nextBtn = findViewById(R.id.guessTheFlagNextBtn);

        gameSetUp();

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check if round has not ended
                if (!roundEnded) {
                    // check if answer is correct
                    checkAnswer(countryList.get(0));
                }
                roundEnded = true;
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!roundEnded) {
                    // check if answer is correct
                    checkAnswer(countryList.get(1));
                }
                roundEnded = true;
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!roundEnded) {
                    // check if answer is correct
                    checkAnswer(countryList.get(2));
                }
                roundEnded = true;
            }
        });

        // if next btn is clicked
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check if round has ended:
                if (roundEnded) gameSetUp();
            }
        });
    }

    private void gameSetUp(){
        roundEnded = false;

        // Clear warnings
        loseMsgTxt.setText("");
        winMsgTxt.setText("");

        // getting list of three random countries
        this.countryList = countryManager.getRandomList(3);

        // pick one at random to be correct answer
        this.correctCountry = this.countryList.get(random.nextInt(countryList.size()));

        // set images and textview
        correctAnswerTextView.setText(this.correctCountry.getCountryName());
        imageView1.setImageResource(countryList.get(0).getImageInt());
        imageView2.setImageResource(countryList.get(1).getImageInt());
        imageView3.setImageResource(countryList.get(2).getImageInt());


    }

    private void checkAnswer(Country guessedCountry){
        if (guessedCountry.equals(this.correctCountry)){
            this.winMsgTxt.setText("CORRECT!");
        }
        else {
            this.loseMsgTxt.setText("WRONG!");
        }
    }
}