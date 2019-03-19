package com.example.swdb;

import android.content.Intent;
import android.content.res.Resources;
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
import com.example.swdb.data.SWStarship;


public class StarshipsDetailedActivity extends AppCompatActivity {

    private TextView mName;
    private TextView mModel;
    private TextView mManu;
    private TextView mCost;
    private TextView mLength;
    private TextView mSpeed;
    private TextView mCrew;
    private TextView mPassenger;
    private TextView mCargo;
    private TextView mConsum;
    private TextView mHyper;
    private TextView mMGLT;
    private TextView mStarClass;


    private SWStarship mShip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Resources res = getResources();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean theme = sharedPreferences.getBoolean(getString(R.string.pref_theme_key),res.getBoolean(R.bool.pref_theme_default_value));
        if(theme){
            setTheme(R.style.AppThemeDarkSide);
        }
        else{
            setTheme(R.style.AppThemeLightSide);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.starship_search_detail);

        mName = findViewById(R.id.tv_name);
        mModel = findViewById(R.id.tv_model);
        mManu = findViewById(R.id.tv_manu);
        mCost = findViewById(R.id.tv_cost);
        mLength = findViewById(R.id.tv_length);
        mSpeed = findViewById(R.id.tv_speed);
        mCrew = findViewById(R.id.tv_crew);
        mPassenger = findViewById(R.id.tv_passenger);
        mCargo = findViewById(R.id.tv_cargo);
        mConsum = findViewById(R.id.tv_consum);
        mHyper = findViewById(R.id.tv_hyper);
        mMGLT = findViewById(R.id.tv_mglt);
        mStarClass = findViewById(R.id.tv_starclass);



        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(SWStarship.EXTRA_SHIP_ITEM)) {
            mShip = (SWStarship) intent.getSerializableExtra(
                    SWStarship.EXTRA_SHIP_ITEM
            );
            fillInLayout(mShip);
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
        String searchingfor = mShip.name;

        Uri geoUri = Uri.parse("geo:0,0").buildUpon()
                .appendQueryParameter("q", searchingfor)
                .build();
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://images.google.com/search?num=10&hl=en&site=&tbm=isch&source=hp‌​&biw=1366&bih=667&q=" + mShip.name + "&oq=cars&gs_l=img.3..0l10.748.1058.0.1306.4.4.0.0.0.0.165‌​.209.2j1.3.0...0.0...1ac.1.8RNsNEqlcZc"));

        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }

    private void fillInLayout(SWStarship shipItem) {


        String shipname = (shipItem.name);
        String shipmodel = getString(R.string.ship_item_model, shipItem.model);
        String shipmanu = getString(R.string.ship_item_manu, shipItem.manufacturer);
        String shipcost = getString(R.string.ship_item_cost, shipItem.cost_in_credits);
        String shiplen = getString(R.string.ship_item_length, shipItem.length);
        String shipspeed = getString(R.string.ship_item_speed, shipItem.max_atmosphering_speed);
        String shipcrew = getString(R.string.ship_item_crew, shipItem.crew);
        String shippass = getString(R.string.ship_item_pass, shipItem.passengers);
        String shipcargo = getString(R.string.ship_item_cargo, shipItem.cargo_capacity);
        String shipconsum = getString(R.string.ship_item_consum, shipItem.consumables);
        String shiphyp = getString(R.string.ship_item_hyper, shipItem.hyperdrive_rating);
        String shipmglt = getString(R.string.ship_item_mglt, shipItem.MGLT);
        String shipclass = getString(R.string.ship_item_class, shipItem.starship_class);

        mName.setText(shipname);
        mModel.setText(shipmodel);
        mManu.setText(shipmanu);
        mCost.setText(shipcost);
        mLength.setText(shiplen);
        mSpeed.setText(shipspeed);
        mCrew.setText(shipcrew);
        mPassenger.setText(shippass);
        mCargo.setText(shipcargo);
        mConsum.setText(shipconsum);
        mHyper.setText(shiphyp);
        mMGLT.setText(shipmglt);
        mStarClass.setText(shipclass);


    }
}