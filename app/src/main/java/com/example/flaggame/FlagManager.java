package com.example.flaggame;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class FlagManager {
    private Context context;
    private List<Country> countries;

    public FlagManager(Context context) {
        this.context = context;
        this.countries = getCountries();
    }


    public void getRandomCountry(){
    }

    public void getRandomList(int numberOfCountries){}



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
