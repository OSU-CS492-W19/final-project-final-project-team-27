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
import com.example.swdb.data.SWFilm;


public class FilmDetailedActivity extends AppCompatActivity {


    private TextView mName;
    private TextView mEpisode;
    private TextView mDirector;
    private TextView mProducer;
    private TextView mRelease;

    private SWFilm mFilm;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.film_search_detail);

        mName = findViewById(R.id.tv_name);
        mEpisode = findViewById(R.id.tv_episode);
        mDirector = findViewById(R.id.tv_director);
        mProducer = findViewById(R.id.tv_producer);
        mRelease = findViewById(R.id.tv_release);


        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(SWFilm.EXTRA_FILM_ITEM)) {
            mFilm = (SWFilm) intent.getSerializableExtra(
                    SWFilm.EXTRA_FILM_ITEM
            );
            fillInLayout(mFilm);
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
        String searchingfor = mFilm.title;

        Uri geoUri = Uri.parse("geo:0,0").buildUpon()
                .appendQueryParameter("q", searchingfor)
                .build();
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://images.google.com/search?num=10&hl=en&site=&tbm=isch&source=hp‌​&biw=1366&bih=667&q=" + mFilm.title + "&oq=cars&gs_l=img.3..0l10.748.1058.0.1306.4.4.0.0.0.0.165‌​.209.2j1.3.0...0.0...1ac.1.8RNsNEqlcZc"));

        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }

    private void fillInLayout(SWFilm filmItem) {


        String filmname = (filmItem.title);
        String filmep = getString(R.string.film_item_episode, filmItem.episode_id);
        String filmdir = getString(R.string.film_item_director, filmItem.director);
        String filmpro = getString(R.string.film_item_producer, filmItem.producer);
        String filmre = getString(R.string.film_item_release, filmItem.release_date);
        
        mName.setText(filmname);
        mEpisode.setText(filmep);
        mDirector.setText(filmdir);
        mProducer.setText(filmpro);
        mRelease.setText(filmre);


    }
}