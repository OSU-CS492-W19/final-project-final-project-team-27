package com.example.swdb.data;

import java.io.Serializable;

public class SWFilm implements Serializable, Comparable<SWFilm> {
    public String title;
    public String episode_id;
    public String opening_crawl;
    public String director;
    public String producer;
    public String release_date;

    public static final String EXTRA_FILM_ITEM = "com.example.android.swdb.data.SWFilm";


    @Override
    public int compareTo(SWFilm otherFilm) {
        return this.title.compareToIgnoreCase(otherFilm.title);
    }
}
