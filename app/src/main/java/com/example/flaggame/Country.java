package com.example.flaggame;

import android.content.Context;

public class Country {
    private String name;
    private int imageInt;
    private Context context;

    public Country(Context context, int imageInt) {
        this.context = context;
        this.imageInt = imageInt;
        this.name = this.getName();
    }

    private String getName() {
        String resourceName = context.getResources().getResourceEntryName(imageInt);
        return resourceName.replace("flag_of_", "").toUpperCase();
    }

    public String getCountryName() {
        return name;
    }

    public int getImageInt() {
        return imageInt;
    }
}

