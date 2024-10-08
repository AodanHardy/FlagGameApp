package com.example.flaggame;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
example usage
    FlagManager manager = new FlagManager(this);

    Country country = manager.getRandomCountry();


    ImageView imageView = findViewById(R.id.imageView2);

    imageView.setImageResource(country.getImageInt());



    TextView textView = findViewById(R.id.textView);
    textView.setText(country.getCountryName());

 */

public class FlagManager {
    private final Context context;
    private List<Country> countries;
    private final Random random = new Random();

    public FlagManager(Context context) {
        this.context = context;
        this.countries = getCountries();
    }


    public Country getRandomCountry() {
        int randomIndex = random.nextInt(countries.size());
        return countries.get(randomIndex);
    }

    public List<Country> getRandomList(int numberOfCountries){
        List<Country> randCountries = new ArrayList<>();

        for (int i = 0; i < numberOfCountries; i++){
            Country newCountry = this.getRandomCountry();
            if (!randCountries.contains(newCountry)){
                randCountries.add(newCountry);
            }
            else i--;
        }
        return randCountries;
    }

    public List<String> getCountryNames(){
        List<String> names = new ArrayList<>();
        for (Country country : this.countries){
            names.add(country.getCountryName());
        }
        return names;
    }

    private List<Country> getCountries(){
        List<Country> fetchedCounties = new ArrayList<>();
        fetchedCounties.add(new Country(this.context, R.drawable.flag_of_algeria));
        fetchedCounties.add(new Country(this.context, R.drawable.flag_of_australia));
        fetchedCounties.add(new Country(this.context, R.drawable.flag_of_belgium));
        fetchedCounties.add(new Country(this.context, R.drawable.flag_of_brazil));
        fetchedCounties.add(new Country(this.context, R.drawable.flag_of_canada));
        fetchedCounties.add(new Country(this.context, R.drawable.flag_of_colombia));
        fetchedCounties.add(new Country(this.context, R.drawable.flag_of_croatia));
        fetchedCounties.add(new Country(this.context, R.drawable.flag_of_denmark));
        fetchedCounties.add(new Country(this.context, R.drawable.flag_of_egypt));
        fetchedCounties.add(new Country(this.context, R.drawable.flag_of_finland));
        fetchedCounties.add(new Country(this.context, R.drawable.flag_of_france));
        fetchedCounties.add(new Country(this.context, R.drawable.flag_of_germany));
        fetchedCounties.add(new Country(this.context, R.drawable.flag_of_greece));
        fetchedCounties.add(new Country(this.context, R.drawable.flag_of_portugal));
        fetchedCounties.add(new Country(this.context, R.drawable.flag_of_singapore));
        fetchedCounties.add(new Country(this.context, R.drawable.flag_of_spain));
        fetchedCounties.add(new Country(this.context, R.drawable.flag_of_sweden));
        fetchedCounties.add(new Country(this.context, R.drawable.flag_of_switzerland));
        fetchedCounties.add(new Country(this.context, R.drawable.flag_of_turkey));
        fetchedCounties.add(new Country(this.context, R.drawable.flag_of_ukraine));
        fetchedCounties.add(new Country(this.context, R.drawable.flag_of_zimbabwe));

        return fetchedCounties;
    }


}
