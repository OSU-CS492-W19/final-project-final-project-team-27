package com.example.swdb;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.swdb.data.SWFilm;
import com.example.swdb.data.SWPerson;
import com.example.swdb.data.SWPlanet;
import com.example.swdb.data.SWSearchResult;
import com.example.swdb.data.SWSpecies;
import com.example.swdb.data.SWStarship;
import com.example.swdb.data.SWVehicle;
import com.example.swdb.data.Status;
import com.example.swdb.utils.SWUtils;

import java.util.List;

public class SWSearchResultsActivity extends AppCompatActivity implements SWSearchAdapter.OnSearchItemClickListener{
    private static final String TAG = SWSearchResultsActivity.class.getSimpleName();

    private RecyclerView mSearchResultsRV;
    private EditText mSearchBoxET;
    private TextView mLoadingErrorTV;
    private ProgressBar mLoadingPB;
    private String mQuery;

    private SWSearchAdapter mSWSearchAdapter;
    private SWSearchViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        mSearchBoxET = findViewById(R.id.et_search_box);
        mSearchResultsRV = findViewById(R.id.rv_search_results);
        mLoadingErrorTV = findViewById(R.id.tv_loading_error);
        mLoadingPB = findViewById(R.id.pb_loading);

        mSearchResultsRV.setLayoutManager(new LinearLayoutManager(this));
        mSearchResultsRV.setHasFixedSize(true);

        mSWSearchAdapter = new SWSearchAdapter(this);
        mSearchResultsRV.setAdapter(mSWSearchAdapter);

        mViewModel = ViewModelProviders.of(this).get(SWSearchViewModel.class);

        mViewModel.getSearchResults().observe(this, new Observer<SWSearchResult>() {
            @Override
            public void onChanged(@Nullable SWSearchResult result) {
                mSWSearchAdapter.updateSearchResults(result);
            }
        });

        mViewModel.getLoadingStatus().observe(this, new Observer<Status>() {
            @Override
            public void onChanged(@Nullable Status status) {
                if (status == Status.LOADING) {
                    mLoadingPB.setVisibility(View.VISIBLE);
                } else if (status == Status.SUCCESS) {
                    mLoadingPB.setVisibility(View.INVISIBLE);
                    mSearchResultsRV.setVisibility(View.VISIBLE);
                    mLoadingErrorTV.setVisibility(View.INVISIBLE);
                } else {
                    mLoadingPB.setVisibility(View.INVISIBLE);
                    mSearchResultsRV.setVisibility(View.INVISIBLE);
                    mLoadingErrorTV.setVisibility(View.VISIBLE);
                }
            }
        });

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(SWUtils.EXTRA_SW_QUERY)) {
            mQuery = intent.getStringExtra(SWUtils.EXTRA_SW_QUERY);
            mSearchBoxET.setText(mQuery);
            doSWSearch(mQuery);
        }

        Button searchButton = findViewById(R.id.btn_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = mSearchBoxET.getText().toString();
                if (!TextUtils.isEmpty(searchQuery)) {
                    doSWSearch(searchQuery);
                }
            }
        });
    }

    private void doSWSearch(String query) {
        mViewModel.loadSearchResults(query);
    }

    @Override
    public void onSearchFilmClick(SWFilm film) {
        Log.d(TAG, "go to " + film.title + "'s page");
        Intent intent = new Intent(this, FilmDetailedActivity.class);
        intent.putExtra(SWFilm.EXTRA_FILM_ITEM, film);
        startActivity(intent);
    }

    @Override
    public void onSearchPersonClick(SWPerson person) {
        Log.d(TAG, "go to " + person.name + "'s page");
        Intent intent = new Intent(this, SearchDetailedActivity.class);
        intent.putExtra(SWPerson.EXTRA_PERSON_ITEM, person);
        startActivity(intent);
    }

    @Override
    public void onSearchPlanetClick(SWPlanet planet) {
        Log.d(TAG, "go to " + planet.name + "'s page");
        Intent intent = new Intent(this, PlanetsDetailedActivity.class);
        intent.putExtra(SWPlanet.EXTRA_PLANET_ITEM, planet);
        startActivity(intent);
    }

    @Override
    public void onSearchSpeciesClick(SWSpecies species) {
        Log.d(TAG, "go to " + species.name + "'s page");
        Intent intent = new Intent(this, SpeciesDetailedActivity.class);
        intent.putExtra(SWSpecies.EXTRA_SPECIES_ITEM, species);
        startActivity(intent);
    }

    @Override
    public void onSearchStarshipClick(SWStarship starship) {
        Log.d(TAG, "go to " + starship.name + "'s page");
        Intent intent = new Intent(this, StarshipsDetailedActivity.class);
        intent.putExtra(SWStarship.EXTRA_SHIP_ITEM, starship);
        startActivity(intent);
    }

    @Override
    public void onSearchVehicleClick(SWVehicle vehicle) {
        Log.d(TAG, "go to " + vehicle.name + "'s page");
        Intent intent = new Intent(this, VehiclesDetailedActivity.class);
        intent.putExtra(SWVehicle.EXTRA_VEH_ITEM, vehicle);
        startActivity(intent);
    }

}
