//package com.example.swdb;
//
//import android.content.Intent;
//import android.support.v4.app.ShareCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.preference.PreferenceManager;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.content.SharedPreferences;
//
////import com.bumptech.glide.Glide;
//import com.example.swdb.utils.SWUtils;
//import java.text.DateFormat;
//
//
//public class SearchDetailedActivity extends AppCompatActivity {
//
//
//
//    private TextView mDateTV;
//    private TextView mTempDescriptionTV;
//    private TextView mLowHighTempTV;
//    private TextView mWindTV;
//    private TextView mHumidityTV;
//    private ImageView mWeatherIconIV;
//    private TextView mName;
//    public String letter;
//
//    private SWUtils.SWPerson mPerson;
//
////    private TextView mWeatherNameTV;
////    private TextView mWeatherSimpleTV;
////    private TextView mWeatherForecastTV;
////    private TextView mWeatherTempTV;
////    private TextView mWeatherWindTV;
////    private TextView mWeatherHumidityTV;
////    //private WeatherUtils.TheWeatherSearch mWeather;
////    private SWUtils.SWPerson mPerson;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search_detail);
//
//        mDateTV = findViewById(R.id.tv_date);
//        mTempDescriptionTV = findViewById(R.id.tv_temp_description);
//        mLowHighTempTV = findViewById(R.id.tv_low_high_temp);
//        mWindTV = findViewById(R.id.tv_wind);
//        //mHumidityTV = findViewById(R.id.tv_humidity);
//        mWeatherIconIV = findViewById(R.id.iv_weather_icon);
//        mName = findViewById(R.id.tv_name);
//
//        Intent intent = getIntent();
//        if (intent != null && intent.hasExtra(SWUtils.EXTRA_PERSON_ITEM)) {
//            mPerson = (SWUtils.SWPerson)intent.getSerializableExtra(
//                    SWUtils.EXTRA_PERSON_ITEM
//            );
//            fillInLayout(mPerson);
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.search_detail, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_share:
//                shareForecast();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
//    public String getLetter(String letter){
//
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        String tempvar = preferences.getString(
//                getString(R.string.pref_temp_key), getString(R.string.pref_temp_default)
//        );
//
//        if ((tempvar).equals("imperial")){
//            letter = "F";
//        }
//        else if ((tempvar).equals("metric")){
//            letter = "C";
//
//        }
//        else
//            letter = "K";
//
//        return letter;
//    }
//
//
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
//
//    private void fillInLayout(SWUtils.SWPerson personItem) {
//
//
//
//        String dateString = DateFormat.getDateTimeInstance().format(personItem.name);
//        String detailString = getString(R.string.search_item_details, personItem.eye_color,
//                getLetter(letter), personItem.hair_color);
////        String lowHighTempString = getString(R.string.forecast_item_low_high_temp,
////                forecastItem.temperatureLow, forecastItem.temperatureHigh,
////                getLetter(letter));
//
////        String windString = getString(R.string.forecast_item_wind, forecastItem.windSpeed,
////                forecastItem.windDirection);
////        String humidityString = getString(R.string.forecast_item_humidity, forecastItem.humidity);
//        //String iconURL = SWUtils.buildIconURL(forecastItem.icon);
//
//        mDateTV.setText(dateString);
//        mTempDescriptionTV.setText(detailString);
////        mLowHighTempTV.setText(lowHighTempString);
////        mWindTV.setText(windString);
////        mHumidityTV.setText(humidityString);
////        Glide.with(this).load(iconURL).into(mWeatherIconIV);
//    }
//
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_search_detail);
////
////        mWeatherNameTV = findViewById(R.id.tv_repo_name);
////        mWeatherSimpleTV = findViewById(R.id.tv_repo_stars);
////        mWeatherForecastTV = findViewById(R.id.tv_repo_description1);
////        mWeatherTempTV = findViewById(R.id.tv_repo_description2);
////        mWeatherWindTV = findViewById(R.id.tv_repo_description3);
////        mWeatherHumidityTV = findViewById(R.id.tv_repo_description4);
////
////
////        mPerson = null;
////        Intent intent = getIntent();
////        if (intent != null && intent.hasExtra(WeatherUtils.EXTRA_WEATHER)) {
////            mWeather = (WeatherUtils.TheWeatherSearch) intent.getSerializableExtra(WeatherUtils.EXTRA_WEATHER);
////            mWeatherNameTV.setText(mWeather.dt_txt);
////            mWeatherSimpleTV.setText(mWeather.weather[0].main);
////            mWeatherForecastTV.setText("Forecast: " + mWeather.weather[0].description);
////            mWeatherTempTV.setText("Temperature: " + Double.toString(Math.round((mWeather.main.temp) - 273.15) * (9/5) + 32) + "F");
////            mWeatherWindTV.setText("Wind: " + mWeather.wind.speed);
////            mWeatherHumidityTV.setText("Humidity: " + mWeather.main.humidity);
////        }
////    }
////
////    @Override
////    public boolean onCreateOptionsMenu(Menu menu) {
////        getMenuInflater().inflate(R.menu.weather_detail, menu);
////        return true;
////    }
////
////    @Override
////    public boolean onOptionsItemSelected(MenuItem item) {
////        switch (item.getItemId()) {
////            case R.id.action_share:
////                shareRepo();
////                return true;
////            default:
////                return super.onOptionsItemSelected(item);
////        }
////    }
////
////
////    public void shareRepo() {
////        if (mWeather != null) {
////            String shareText = getString(R.string.share_weather, mWeather.dt_txt + "\n", mWeather.weather[0].main + "\n", "Forecast: " + mWeather.weather[0].description + "\n", "Temperature: " +  Double.toString(Math.round((mWeather.main.temp) - 273.15) * (9/5) + 32) + "F" + "\n", "Wind: " +  mWeather.wind.speed + "\n", "Humidity: " + mWeather.main.humidity + "\n");
////            ShareCompat.IntentBuilder.from(this)
////                    .setType("text/plain")
////                    .setText(shareText)
////                    .setChooserTitle(R.string.share_chooser_title)
////                    .startChooser();
////        }
////    }
//
//}
