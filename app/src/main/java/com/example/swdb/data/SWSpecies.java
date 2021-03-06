package com.example.swdb.data;

import java.io.Serializable;

public class SWSpecies implements Serializable, Comparable<SWSpecies> {
    public String name;
    public String classification;
    public String designation;
    public String average_height;
    public String skin_colors;
    public String hair_colors;
    public String eye_colors;
    public String average_lifespan;
    public String homeworld;
    public String language;
    public static final String EXTRA_SPECIES_ITEM = "com.example.android.swdb.data.SWSpecies";

    @Override
    public int compareTo(SWSpecies otherSpecies) {
        return this.name.compareToIgnoreCase(otherSpecies.name);
    }
}
