package com.example.swdb.data;

import java.io.Serializable;

public class SWPerson implements Serializable, Comparable<SWPerson> {
    public String name;
    public String height;
    public String mass;
    public String hair_color;
    public String skin_color;
    public String eye_color;
    public String birth_year;
    public String gender;
    public String homeworld;
    public String[] films;
    public String[] species;

    public static final String EXTRA_PERSON_ITEM = "com.example.android.swdb.data.SWPerson";

    @Override
    public int compareTo(SWPerson otherPerson) {
        return this.name.compareToIgnoreCase(otherPerson.name);
    }
}
