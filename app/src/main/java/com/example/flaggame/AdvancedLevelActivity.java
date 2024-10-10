package com.example.flaggame;

import static com.example.flaggame.Constants.BLACK;
import static com.example.flaggame.Constants.GREY;
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

public class AdvancedLevelActivity extends AppCompatActivity {
    private CountryManager countryManager;
    private ImageView image1, image2, image3;
    private EditText textBox1, textBox2,textBox3;
    private boolean q1Correct, q2Correct, q3Correct, roundComplete;
    private Button submitBtn;
    private TextView warningTxt, correctTxt;

    private List<Country> roundCountries;

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
        warningTxt = findViewById(R.id.advancedLvlWarningTxt);
        correctTxt = findViewById(R.id.advancedLvlCorrectTxt);

        gameSetUp();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check if round finished is true
                // if so: refresh game
                if (roundComplete){
                    gameSetUp();
                }

                else {
                    // check answers
                    q1Correct = checkAnswer(roundCountries.get(0), textBox1);
                    q2Correct = checkAnswer(roundCountries.get(1), textBox2);
                    q3Correct = checkAnswer(roundCountries.get(2), textBox3);


                    // if all answers are correct, then enable nextBtn,
                    // roundComplete = false, display message and restart game
                    if (q1Correct && q2Correct && q3Correct) {
                        roundComplete = true;
                        submitBtn.setText("Next");
                        warningTxt.setText("");
                        correctTxt.setText("CORRECT!");
                    }
                    else {
                        warningTxt.setText("INCORRECT!");
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

        // set all booleans to false
        q1Correct = false; q2Correct = false; q3Correct = false; roundComplete=false;

        // reset editText fields, text and colours
        textBox1.setEnabled(true);textBox2.setEnabled(true);textBox3.setEnabled(true);
        textBox1.setText("");textBox2.setText("");textBox3.setText("");
        textBox1.setTextColor(Color.parseColor(BLACK));
        textBox2.setTextColor(Color.parseColor(BLACK));
        textBox3.setTextColor(Color.parseColor(BLACK));

        correctTxt.setText("");
        warningTxt.setText("");


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
            editText.setTextColor(Color.parseColor(GREY));

            // return true
            return true;
        }
        else{
            editText.setTextColor(Color.parseColor(RED));
            return false;
        }

    }
}