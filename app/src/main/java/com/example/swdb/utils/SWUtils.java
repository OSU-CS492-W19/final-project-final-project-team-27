package com.example.swdb.utils;

import android.net.Uri;
import android.util.Log;

import com.example.swdb.data.SWFilm;
import com.example.swdb.data.SWPerson;
import com.example.swdb.data.SWPlanet;
import com.example.swdb.data.SWSearchResult;
import com.example.swdb.data.SWSpecies;
import com.example.swdb.data.SWStarship;
import com.example.swdb.data.SWVehicle;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class SWUtils {
    private final static String SW_SEARCH_BASE_URL = "https://swapi.co/api/";
    private final static String SW_SEARCH_QUERY_PARAM = "search";
    private final static String SW_SEARCH_FORMAT_PARAM = "format";
    private final static String SW_SEARCH_FORMAT_VALUE = "json";
    private final static String CATEGORY_FILMS = "films";
    private final static String CATEGORY_PEOPLE = "people";
    private final static String CATEGORY_PLANETS = "planets";
    private final static String CATEGORY_SPECIES = "species";
    private final static String CATEGORY_STARSHIPS = "starships";
    private final static String CATEGORY_VEHICLES = "vehicles";
    private final static String SORT_ATOZ = "atoz";
    private final static String SORT_ZTOA = "ztoa";

    public static final String EXTRA_SW_QUERY = "String";

    public static class SWFilmsResults {
        public ArrayList<SWFilm> results;
    }

    public static class SWPeopleResults {
        public ArrayList<SWPerson> results;
    }

    public static class SWPlanetsResults {
        public ArrayList<SWPlanet> results;
    }

    public static class SWSpeciesResults {
        public ArrayList<SWSpecies> results;
    }

    public static class SWStarshipsResults {
        public ArrayList<SWStarship> results;
    }

    public static class SWVehiclesResults {
        public ArrayList<SWVehicle> results;
    }

    public static class SWFilmResult {
        public String title;
    }

    public static class SWItemResult {
        public String name;
    }

    public static String buildSWSearchURL(String query, String category) {
        Log.d("Search", category);
        return Uri.parse(SW_SEARCH_BASE_URL).buildUpon()
                .appendEncodedPath(category)
                .appendQueryParameter(SW_SEARCH_QUERY_PARAM, query)
                .appendQueryParameter(SW_SEARCH_FORMAT_PARAM, SW_SEARCH_FORMAT_VALUE)
                .build()
                .toString();
    }

    private static void sortResults(SWSearchResult results, String category, String sortPref) {
        switch (category) {
            case CATEGORY_FILMS:
                if (sortPref.equals(SORT_ATOZ)) {
                    Collections.sort(results.films);
                } else if (sortPref.equals(SORT_ZTOA)) {
                    Collections.sort(results.films);
                    Collections.reverse(results.films);
                }
                break;
            case CATEGORY_PEOPLE:
                if (sortPref.equals(SORT_ATOZ)) {
                    Collections.sort(results.people);
                } else if (sortPref.equals(SORT_ZTOA)) {
                    Collections.sort(results.people);
                    Collections.reverse(results.people);
                }
                break;
            case CATEGORY_PLANETS:
                if (sortPref.equals(SORT_ATOZ)) {
                    Collections.sort(results.planets);
                } else if (sortPref.equals(SORT_ZTOA)) {
                    Collections.sort(results.planets);
                    Collections.reverse(results.planets);
                }
                break;
            case CATEGORY_SPECIES:
                if (sortPref.equals(SORT_ATOZ)) {
                    Collections.sort(results.species);
                } else if (sortPref.equals(SORT_ZTOA)) {
                    Collections.sort(results.species);
                    Collections.reverse(results.species);
                }
                break;
            case CATEGORY_STARSHIPS:
                if (sortPref.equals(SORT_ATOZ)) {
                    Collections.sort(results.starships);
                } else if (sortPref.equals(SORT_ZTOA)) {
                    Collections.sort(results.starships);
                    Collections.reverse(results.starships);
                }
                break;
            case CATEGORY_VEHICLES:
                if (sortPref.equals(SORT_ATOZ)) {
                    Collections.sort(results.vehicles);
                } else if (sortPref.equals(SORT_ZTOA)) {
                    Collections.sort(results.vehicles);
                    Collections.reverse(results.vehicles);
                }
                break;
            default:
                break;
        }
    }

    public static SWItemResult parseSWItem(String json) {
        Gson gson = new Gson();
        SWItemResult swItemResult = gson.fromJson(json, SWItemResult.class);
        return swItemResult;
    }

    public static SWFilmResult parseSWFilm(String json) {
        Gson gson = new Gson();
        SWFilmResult swFilmResult = gson.fromJson(json, SWFilmResult.class);
        return swFilmResult;
    }

    public static SWSearchResult parseSWSearchResults(String json, String category, String sortPref) {
        Gson gson = new Gson();
        SWSearchResult swSearchResult = new SWSearchResult();

        switch (category) {
            case CATEGORY_FILMS:
                SWFilmsResults filmResults = gson.fromJson(json, SWFilmsResults.class);
                if (filmResults != null && filmResults.results != null) {
                    swSearchResult.films = filmResults.results;
                    break;
                } else {
                    return null;
                }
            case CATEGORY_PEOPLE:
                SWPeopleResults peopleResults = gson.fromJson(json, SWPeopleResults.class);
                if (peopleResults != null && peopleResults.results != null) {
                    swSearchResult.people = peopleResults.results;
                    break;
                } else {
                    return null;
                }
            case CATEGORY_PLANETS:
                SWPlanetsResults planetsResults = gson.fromJson(json, SWPlanetsResults.class);
                if (planetsResults != null && planetsResults.results != null) {
                    swSearchResult.planets = planetsResults.results;
                    break;
                } else {
                    return null;
                }
            case CATEGORY_SPECIES:
                SWSpeciesResults speciesResults = gson.fromJson(json, SWSpeciesResults.class);
                if (speciesResults != null && speciesResults.results != null) {
                    swSearchResult.species = speciesResults.results;
                    break;
                } else {
                    return null;
                }
            case CATEGORY_STARSHIPS:
                SWStarshipsResults starshipsResults = gson.fromJson(json, SWStarshipsResults.class);
                if (starshipsResults != null && starshipsResults.results != null) {
                    swSearchResult.starships = starshipsResults.results;
                    break;
                } else {
                    return null;
                }
            case CATEGORY_VEHICLES:
                SWVehiclesResults vehiclesResults = gson.fromJson(json, SWVehiclesResults.class);
                if (vehiclesResults != null && vehiclesResults.results != null) {
                    swSearchResult.vehicles = vehiclesResults.results;
                    break;
                } else {
                    return null;
                }
            default:
                return null;
        }
        sortResults(swSearchResult, category, sortPref);
        return swSearchResult;
    }

    public static class SearchDetails implements Serializable {
        public String search_item_name;

    }

}
