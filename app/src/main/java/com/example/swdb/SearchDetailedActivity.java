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


    private SWPerson mPerson;


    //public String[] films;
    //public String[] species;

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

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(SWPerson.EXTRA_PERSON_ITEM)) {
            mPerson = (SWPerson)intent.getSerializableExtra(
                    SWPerson.EXTRA_PERSON_ITEM
            );
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
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String searchingfor = mPerson.name;

        Uri geoUri = Uri.parse("geo:0,0").buildUpon()
                .appendQueryParameter("q", searchingfor)
                .build();
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://images.google.com/search?num=10&hl=en&site=&tbm=isch&source=hp‌​&biw=1366&bih=667&q="+ mPerson.name + "&oq=cars&gs_l=img.3..0l10.748.1058.0.1306.4.4.0.0.0.0.165‌​.209.2j1.3.0...0.0...1ac.1.8RNsNEqlcZc"));

        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }



//    public void shareForecast() {
//
//
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        String locvar = preferences.getString(getString(R.string.pref_user_key), "");
//
//
//
//        if (mForecastItem != null) {
//            String dateString = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT)
//                    .format(mForecastItem.dateTime);
//            String shareText = getString(R.string.forecast_item_share_text,
//                    locvar, dateString,
//                    mForecastItem.temperature, getLetter(letter),
//                    mForecastItem.description);
//            ShareCompat.IntentBuilder.from(this)
//                    .setType("text/plain")
//                    .setText(shareText)
//                    .setChooserTitle(R.string.share_chooser_title)
//                    .startChooser();
//        }
//    }

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

    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search_detail);
//
//        mWeatherNameTV = findViewById(R.id.tv_repo_name);
//        mWeatherSimpleTV = findViewById(R.id.tv_repo_stars);
//        mWeatherForecastTV = findViewById(R.id.tv_repo_description1);
//        mWeatherTempTV = findViewById(R.id.tv_repo_description2);
//        mWeatherWindTV = findViewById(R.id.tv_repo_description3);
//        mWeatherHumidityTV = findViewById(R.id.tv_repo_description4);
//
//
//        mPerson = null;
//        Intent intent = getIntent();
//        if (intent != null && intent.hasExtra(WeatherUtils.EXTRA_WEATHER)) {
//            mWeather = (WeatherUtils.TheWeatherSearch) intent.getSerializableExtra(WeatherUtils.EXTRA_WEATHER);
//            mWeatherNameTV.setText(mWeather.dt_txt);
//            mWeatherSimpleTV.setText(mWeather.weather[0].main);
//            mWeatherForecastTV.setText("Forecast: " + mWeather.weather[0].description);
//            mWeatherTempTV.setText("Temperature: " + Double.toString(Math.round((mWeather.main.temp) - 273.15) * (9/5) + 32) + "F");
//            mWeatherWindTV.setText("Wind: " + mWeather.wind.speed);
//            mWeatherHumidityTV.setText("Humidity: " + mWeather.main.humidity);
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.weather_detail, menu);
//        return true;
//    }
//

//
//
//    public void shareRepo() {
//        if (mWeather != null) {
//            String shareText = getString(R.string.share_weather, mWeather.dt_txt + "\n", mWeather.weather[0].main + "\n", "Forecast: " + mWeather.weather[0].description + "\n", "Temperature: " +  Double.toString(Math.round((mWeather.main.temp) - 273.15) * (9/5) + 32) + "F" + "\n", "Wind: " +  mWeather.wind.speed + "\n", "Humidity: " + mWeather.main.humidity + "\n");
//            ShareCompat.IntentBuilder.from(this)
//                    .setType("text/plain")
//                    .setText(shareText)
//                    .setChooserTitle(R.string.share_chooser_title)
//                    .startChooser();
//        }
//    }

}
