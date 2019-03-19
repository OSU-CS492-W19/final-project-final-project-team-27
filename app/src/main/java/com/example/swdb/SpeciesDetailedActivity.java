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
import com.example.swdb.data.SWSpecies;


public class SpeciesDetailedActivity extends AppCompatActivity {

    private TextView mName;
    private TextView mClass;
    private TextView mDes;
    private TextView mHeight;
    private TextView mSkin;
    private TextView mHair;
    private TextView mEye;
    private TextView mLife;
    private TextView mHome;
    private TextView mLang;

    private SWSpecies mSpecies;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.species_search_detail);

        mName = findViewById(R.id.tv_name);
        mClass = findViewById(R.id.tv_class);
        mDes = findViewById(R.id.tv_des);
        mHeight = findViewById(R.id.tv_spechigh);
        mSkin = findViewById(R.id.tv_specskin);
        mHair = findViewById(R.id.tv_spechair);
        mEye = findViewById(R.id.tv_speceye);
        mLife = findViewById(R.id.tv_life);
        mHome = findViewById(R.id.tv_spechome);
        mLang = findViewById(R.id.tv_speclang);


        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(SWSpecies.EXTRA_SPECIES_ITEM)) {
            mSpecies = (SWSpecies) intent.getSerializableExtra(
                    SWSpecies.EXTRA_SPECIES_ITEM
            );
            fillInLayout(mSpecies);
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
        String searchingfor = mSpecies.name;

        Uri geoUri = Uri.parse("geo:0,0").buildUpon()
                .appendQueryParameter("q", searchingfor)
                .build();
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://images.google.com/search?num=10&hl=en&site=&tbm=isch&source=hp‌​&biw=1366&bih=667&q=" + mSpecies.name + "&oq=cars&gs_l=img.3..0l10.748.1058.0.1306.4.4.0.0.0.0.165‌​.209.2j1.3.0...0.0...1ac.1.8RNsNEqlcZc"));

        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }

    private void fillInLayout(SWSpecies specItem) {


        String specname = (specItem.name);
        String specclass = getString(R.string.spec_item_class, specItem.classification);
        String specdes = getString(R.string.spec_item_des, specItem.designation);
        String spechigh = getString(R.string.spec_item_high, specItem.average_height);
        String specskin = getString(R.string.spec_item_skin, specItem.skin_colors);
        String spechair = getString(R.string.spec_item_hair, specItem.hair_colors);
        String speceye = getString(R.string.spec_item_eye, specItem.eye_colors);
        String speclife = getString(R.string.spec_item_life, specItem.average_lifespan);
        String spechome = getString(R.string.spec_item_home, specItem.homeworld);
        String speclang = getString(R.string.spec_item_lang, specItem.language);

        mName.setText(specname);
        mClass.setText(specclass);
        mDes.setText(specdes);
        mHeight.setText(spechigh);
        mSkin.setText(specskin);
        mHair.setText(spechair);
        mEye.setText(speceye);
        mLife.setText(speclife);
        mHome.setText(spechome);
        mLang.setText(speclang);

    }
}