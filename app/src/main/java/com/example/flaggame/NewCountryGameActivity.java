package com.example.flaggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewCountryGameActivity extends AppCompatActivity {
    Button guessTheCountryBtn;
    Button guessHintsBtn;
    Button guessTheFlag;
    Button advancedLvlBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_country_game);


        guessTheCountryBtn = findViewById(R.id.guessTheCountryBtn);
        guessTheCountryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                startActivity(intent);
            }
        });
    }
}