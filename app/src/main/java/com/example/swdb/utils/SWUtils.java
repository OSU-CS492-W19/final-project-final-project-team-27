package com.example.swdb.utils;

import android.net.Uri;

import com.example.swdb.data.SWPerson;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;

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

    public static final String EXTRA_SW_QUERY = "String";

    public static class SWPeopleResults {
        public ArrayList<SWPerson> results;
    }

    public static String buildSWSearchURL(String query) {
        return Uri.parse(SW_SEARCH_BASE_URL).buildUpon()
                .appendEncodedPath(CATEGORY_PEOPLE)
                .appendQueryParameter(SW_SEARCH_QUERY_PARAM, query)
                .appendQueryParameter(SW_SEARCH_FORMAT_PARAM, SW_SEARCH_FORMAT_VALUE)
                .build()
                .toString();
    }

    public static ArrayList<SWPerson> parseSWPeopleResults(String json) {
        Gson gson = new Gson();
        SWPeopleResults results = gson.fromJson(json, SWPeopleResults.class);
        if (results != null && results.results != null) {
            return results.results;
        } else {
            return null;
        }
    }
}
