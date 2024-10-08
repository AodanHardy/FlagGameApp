package com.example.flaggame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FlagManager manager = new FlagManager(this);

        Country country = manager.getRandomCountry();


        ImageView imageView = findViewById(R.id.imageView2);

        imageView.setImageResource(country.getImageInt());



        TextView textView = findViewById(R.id.textView);
        textView.setText(country.getCountryName());


    }
}