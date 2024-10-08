package com.example.flaggame;

import static com.example.flaggame.Constants.NUM_OF_COUNTRIES;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.List;

public class GuessHintsActivity extends AppCompatActivity {
    CountryManager countryManager;
    private List<Country> countryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_hints);

        countryManager = new CountryManager(this);

        countryList = countryManager.getRandomList(NUM_OF_COUNTRIES);


    }
}