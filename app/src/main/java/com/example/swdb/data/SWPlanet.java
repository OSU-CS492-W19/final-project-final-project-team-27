package com.example.swdb.data;

import java.io.Serializable;

public class SWPlanet implements Serializable, Comparable<SWPlanet> {
    public String name;
    public String rotation_period;
    public String orbital_period;
    public String diameter;
    public String climate;
    public String gravity;
    public String terrain;
    public String surface_water;
    public String population;

    public static final String EXTRA_PLANET_ITEM = "com.example.android.swdb.data.SWPlanet";

    @Override
    public int compareTo(SWPlanet otherPlanet) {
        return this.name.compareToIgnoreCase(otherPlanet.name);
    }
}
