package com.example.flaggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewCountryGameActivity extends AppCompatActivity {
    Button guessTheCountryBtn;
    Button guessHintsBtn;
    Button guessTheFlagBtn;
    Button advancedLvlBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_country_game);


        guessTheCountryBtn = findViewById(R.id.guessTheCountryBtn);
        guessTheCountryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewCountryGameActivity.this, GuessTheCountryActivity.class);
                startActivity(intent);
            }
        });


        guessHintsBtn = findViewById(R.id.guessHintsBtn);
        guessHintsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewCountryGameActivity.this, GuessHintsActivity.class);
                startActivity(intent);
            }
        });


        guessTheFlagBtn = findViewById(R.id.guessTheFlagBtn);
        guessTheFlagBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewCountryGameActivity.this, GuessTheFlagActivity.class);
                startActivity(intent);
            }
        });


        advancedLvlBtn = findViewById(R.id.advancedLevelBtn);
        advancedLvlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewCountryGameActivity.this, AdvancedLevelActivity.class);
                startActivity(intent);
            }
        });
    }


}