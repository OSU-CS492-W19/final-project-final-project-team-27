package com.example.swdb;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.view.MenuItem;

public class SettingsActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

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
        setContentView(R.layout.activity_settings);

        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.pref_theme_key))) {
            Resources res = getResources();
            boolean theme = sharedPreferences.getBoolean(getString(R.string.pref_theme_key),res.getBoolean(R.bool.pref_theme_default_value));
            if(theme){
                setTheme(R.style.AppThemeDarkSide);
            }
            else{
                setTheme(R.style.AppThemeLightSide);
            }
            this.recreate();
        }
    }
}
