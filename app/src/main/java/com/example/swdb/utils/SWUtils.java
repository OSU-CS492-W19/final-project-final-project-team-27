package com.example.swdb.utils;

import android.net.Uri;

import com.google.gson.Gson;

import java.io.Serializable;

public class SWUtils {
    private final static String SW_SEARCH_BASE_URL = "https://swapi.co/api/";
    private final static String SW_SEARCH_QUERY_PARAM = "search";
    private final static String SW_SEARCH_FORMAT_PARAM = "format";
    private final static String SW_SEARCH_FORMAT_VALUE = "json";
    private final static String CATEGORY_FILMS = "films/";
    private final static String CATEGORY_PEOPLE = "people/";
    private final static String CATEGORY_PLANETS = "planets/";
    private final static String CATEGORY_SPECIES = "species/";
    private final static String CATEGORY_STARSHIPS = "starships/";
    private final static String CATEGORY_VEHICLES = "vehicles/";

    public static class SWPerson implements Serializable {
        public String name;
        public String height;
        public String mass;
        public String hair_color;
        public String skin_color;
        public String eye_color;
        public String birth_year;
        public String gender;
        public String homeworld;
        public SWFilm[] films;
        public SWSpecies[] species;
    }

    public static class SWFilm implements Serializable {
        public String title;
        public String episode_id;
        public String opening_crawl;
        public String director;
        public String producer;
        public String release_date;
    }

    public static class SWSpecies implements Serializable {
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
    }

    public static class SWPlanet implements Serializable {
        public String name;
        public String rotation_period;
        public String orbital_period;
        public String diameter;
        public String climate;
        public String gravity;
        public String terrain;
        public String surface_water;
        public String population;
    }

    public static class SWStarship implements Serializable {
        public String name;
        public String model;
        public String manufacturer;
        public String cost_in_credits;
        public String length;
        public String max_atmosphering_speed;
        public String crew;
        public String passengers;
        public String cargo_capacity;
        public String consumables;
        public String hyperdrive_rating;
        public String MGLT;
        public String starship_class;
    }

    public static class SWVehicle implements Serializable {
        public String name;
        public String model;
        public String manufacturer;
        public String cost_in_credits;
        public String length;
        public String max_atmosphering_speed;
        public String crew;
        public String passengers;
        public String cargo_capacity;
        public String consumables;
        public String vehicle_class;
    }

    public static class SWPeopleResults {
        public SWPerson[] results;
    }

    public static String buildSWSearchURL(String query) {
        return Uri.parse(SW_SEARCH_BASE_URL).buildUpon()
                .appendEncodedPath(CATEGORY_PEOPLE)
                .appendQueryParameter(SW_SEARCH_QUERY_PARAM, query)
                .appendQueryParameter(SW_SEARCH_FORMAT_PARAM, SW_SEARCH_FORMAT_VALUE)
                .build()
                .toString();
    }

    public static SWPerson[] parseSWPeopleResults(String json) {
        Gson gson = new Gson();
        SWPeopleResults results = gson.fromJson(json, SWPeopleResults.class);
        if (results != null && results.results != null) {
            return results.results;
        } else {
            return null;
        }
    }
}
