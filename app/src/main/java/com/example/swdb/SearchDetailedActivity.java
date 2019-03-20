package com.example.swdb;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.SharedPreferences;

//import com.bumptech.glide.Glide;
import com.example.swdb.utils.SWUtils;
import com.example.swdb.data.SWPerson;
//import java.text.DateFormat;


public class SearchDetailedActivity extends AppCompatActivity {


    private TextView mName;
    private TextView mHeight;
    private TextView mMass;
    private TextView mHair;
    private TextView mSkin;
    private TextView mEye;
    private TextView mBirth;
    private TextView mGender;
    private TextView mHome;
    private TextView mSpec;

    private SWItemViewModel mHomeViewModel;
    private SWSpeciesViewModel mSpeciesViewModel;

    private SWPerson mPerson;

    private String mHomeland;
    private String mPersonSpec;

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
        setContentView(R.layout.activity_search_detail);

        mName = findViewById(R.id.tv_name);
        mHeight = findViewById(R.id.tv_height);
        mMass = findViewById(R.id.tv_mass);
        mHair = findViewById(R.id.tv_hair);
        mSkin = findViewById(R.id.tv_skin);
        mEye = findViewById(R.id.tv_eye);
        mBirth = findViewById(R.id.tv_birth);
        mGender = findViewById(R.id.tv_gender);
        mHome = findViewById(R.id.tv_home);
        mSpec = findViewById(R.id.tv_spec);

        mPersonSpec = getString(R.string.person_item_spec);

        mSpeciesViewModel = ViewModelProviders.of(this).get(SWSpeciesViewModel.class);

        mSpeciesViewModel.getItemResults().observe(this, new Observer<SWUtils.SWItemResult>() {
            @Override
            public void onChanged(@Nullable SWUtils.SWItemResult result) {
                if (result != null && result.name != null) {
                    mPersonSpec = mPersonSpec.concat(" " + result.name);
                    mSpec.setText(mPersonSpec);
                } else {
                    Log.d("Activity", "null");
                }
            }
        });

        mHomeViewModel = ViewModelProviders.of(this).get(SWItemViewModel.class);

        mHomeViewModel.getItemResults().observe(this, new Observer<SWUtils.SWItemResult>() {
            @Override
            public void onChanged(@Nullable SWUtils.SWItemResult result) {
                if (result != null && result.name != null) {
                    Log.d("Activity", "Homeworld: " + result.name);
                    mHomeland = result.name;
                    String personhome = getString(R.string.person_item_home, mHomeland);
                    mHome.setText(personhome);
                } else {
                    Log.d("Activity", "null");
                }
            }
        });

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(SWPerson.EXTRA_PERSON_ITEM)) {
            mPerson = (SWPerson)intent.getSerializableExtra(
                    SWPerson.EXTRA_PERSON_ITEM
            );
            getHomeland(mPerson.homeworld);
            for (int i = 0; i < mPerson.species.length; i++) {
                getSpecies(mPerson.species[i]);
            }
            fillInLayout(mPerson);
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
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://images.google.com/search?num=10&hl=en&site=&tbm=isch&source=hp‌​&biw=1366&bih=667&q="+ mPerson.name + "&oq=cars&gs_l=img.3..0l10.748.1058.0.1306.4.4.0.0.0.0.165‌​.209.2j1.3.0...0.0...1ac.1.8RNsNEqlcZc"));

        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }

    private void getSpecies(String query) {
        mSpeciesViewModel.loadItemResults(query);
    }

    private void getHomeland(String query) {
        mHomeViewModel.loadItemResults(query);
    }

    private void fillInLayout(SWPerson personItem) {

        String personname = (personItem.name);
        String personbirth = getString(R.string.person_item_birth, personItem.birth_year);
        String personeye = getString(R.string.person_item_eye, personItem.eye_color);
        String persongen = getString(R.string.person_item_gender,personItem.gender);
        String personhair = getString(R.string.person_item_hair,personItem.hair_color);
        String personhigh = getString(R.string.person_item_height,personItem.height);
        String personhome = (personItem.homeworld);
        String personmass = getString(R.string.person_item_mass,personItem.mass);
        String personskin = getString(R.string.person_item_skin,personItem.skin_color);

        mName.setText(personname);
        mHeight.setText(personhigh);
        mMass.setText(personmass);
        mHair.setText(personhair);
        mSkin.setText(personskin);
        mEye.setText(personeye);
        mBirth.setText(personbirth);
        mGender.setText(persongen);
        mSpec.setText(mPersonSpec);
        mHome.setText(mHomeland);

    }
}
