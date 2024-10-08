package com.example.flaggame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int image = R.drawable.flag_of_brazil;

        Country country = new Country(this, image);

        ImageView imageView = findViewById(R.id.imageView2);

        imageView.setImageResource(country.getImageInt());

        TextView textView = findViewById(R.id.textView);

        textView.setText(country.getCountryName());


    }
}