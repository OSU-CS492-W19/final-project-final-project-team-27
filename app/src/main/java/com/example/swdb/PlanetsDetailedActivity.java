package com.example.swdb;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.SharedPreferences;

import com.example.swdb.utils.SWUtils;
import com.example.swdb.data.SWPlanet;


public class PlanetsDetailedActivity extends AppCompatActivity {




    private TextView mName;
    private TextView mRotate;
    private TextView mOrbit;
    private TextView mDiam;
    private TextView mClimate;
    private TextView mGravity;
    private TextView mTerrain;
    private TextView mWater;
    private TextView mPop;
    private SWPlanet mPlanet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.planet_search_detail);

        mName = findViewById(R.id.tv_name);
        mRotate = findViewById(R.id.tv_rotate);
        mOrbit = findViewById(R.id.tv_orbit);
        mDiam = findViewById(R.id.tv_diam);
        mClimate = findViewById(R.id.tv_climate);
        mGravity = findViewById(R.id.tv_grav);
        mTerrain = findViewById(R.id.tv_terrain);
        mWater = findViewById(R.id.tv_water);
        mPop = findViewById(R.id.tv_pop);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(SWPlanet.EXTRA_PLANET_ITEM)) {
            mPlanet = (SWPlanet) intent.getSerializableExtra(
                    SWPlanet.EXTRA_PLANET_ITEM
            );
            fillInLayout(mPlanet);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_images:
                taketoimages();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void taketoimages() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String searchingfor = mPlanet.name;

        Uri geoUri = Uri.parse("geo:0,0").buildUpon()
                .appendQueryParameter("q", searchingfor)
                .build();
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://images.google.com/search?num=10&hl=en&site=&tbm=isch&source=hp‌​&biw=1366&bih=667&q=" + mPlanet.name + "&oq=cars&gs_l=img.3..0l10.748.1058.0.1306.4.4.0.0.0.0.165‌​.209.2j1.3.0...0.0...1ac.1.8RNsNEqlcZc"));

        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }

    private void fillInLayout(SWPlanet planetItem) {


        String planetname = (planetItem.name);
        String planetrot = getString(R.string.planet_item_rotate, planetItem.rotation_period);
        String planetorb = getString(R.string.planet_item_orbit, planetItem.orbital_period);
        String planetdiam = getString(R.string.planet_item_diam, planetItem.diameter);
        String planetclim = getString(R.string.planet_item_clim, planetItem.climate);
        String planetgrav = getString(R.string.planet_item_grav, planetItem.gravity);
        String planetterr = getString(R.string.planet_item_terr, planetItem.terrain);
        String planetwater = getString(R.string.planet_item_water, planetItem.surface_water);
        String planetpop = getString(R.string.planet_item_pop, planetItem.population);

        mName.setText(planetname);
        mRotate.setText(planetrot);
        mOrbit.setText(planetorb);
        mDiam.setText(planetdiam);
        mClimate.setText(planetclim);
        mGravity.setText(planetgrav);
        mTerrain.setText(planetterr);
        mWater.setText(planetwater);
        mPop.setText(planetpop);


    }
}