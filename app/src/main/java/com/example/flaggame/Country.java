package com.example.flaggame;

import android.content.Context;

/**
 * The type Country.
 */
public class Country {
    private final String name;
    private final int imageInt;
    private final Context context;

    /**
     * Instantiates a new Country.
     *
     * @param context  the context
     * @param imageInt the image int
     */
    public Country(Context context, int imageInt) {
        this.context = context;
        this.imageInt = imageInt;
        this.name = this.getName();
    }

    /**
     * Gets the Country Name from the resource name.
     */
    private String getName() {
        String resourceName = context.getResources().getResourceEntryName(this.imageInt);
        return resourceName.replace("flag_of_", "").toUpperCase();
    }

    /**
     * Gets country name.
     *
     * @return the country name
     */
    public String getCountryName() {
        return this.name;
    }

    /**
     * Gets image int.
     *
     * @return the image int
     */
    public int getImageInt() {
        return this.imageInt;
    }
}

