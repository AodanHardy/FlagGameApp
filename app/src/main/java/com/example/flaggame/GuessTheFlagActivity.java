package com.example.flaggame;

import static com.example.flaggame.Constants.GREEN;
import static com.example.flaggame.Constants.RED;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class GuessTheFlagActivity extends AppCompatActivity {
    private static final String WIN_MSG = "CORRECT!", FAIL_MSG = "WRONG!";
    private List<Country> countryList;
    private Country correctCountry;
    private CountryManager countryManager;
    private final Random random = new Random();
    private TextView correctAnswerTextView, msgTextView;
    private ImageView imageView1, imageView2, imageView3;
    private boolean roundEnded;
    private Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_the_flag);

        countryManager = new CountryManager(this);

        correctAnswerTextView = findViewById(R.id.guessTheFlagCorrectAnswerTextView);
        msgTextView = findViewById(R.id.guessTheFlagMsgTxt);

        imageView1 = findViewById(R.id.guessTheFlagImg1);
        imageView2 = findViewById(R.id.guessTheFlagImg2);
        imageView3 = findViewById(R.id.guessTheFlagImg3);

        Button nextBtn = findViewById(R.id.guessTheFlagNextBtn);

        gameSetUp();


        // Back button
        backBtn = findViewById(R.id.guessTheFlagBackBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuessTheFlagActivity.this, NewCountryGameActivity.class);
                startActivity(intent);
            }
        });
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
        msgTextView.setText("");

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
            this.msgTextView.setTextColor(Color.parseColor(GREEN));
            this.msgTextView.setText(WIN_MSG);
        }
        else {
            this.msgTextView.setTextColor(Color.parseColor(RED));
            this.msgTextView.setText(FAIL_MSG);
        }
    }
}