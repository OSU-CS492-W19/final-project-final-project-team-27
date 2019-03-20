package com.example.swdb;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swdb.data.SWPerson;
import com.example.swdb.data.Status;
import com.example.swdb.utils.SWUtils;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements RecentSearchAdapter.OnSearchItemClickListener, AdapterView.OnItemSelectedListener{

    private static final String TAG = MainActivity.class.getSimpleName();

    private EditText mSearchBoxET;
    private RecyclerView mRecentSearchesRV;
    private RecentSearchAdapter mRecentSearchAdapter;

    private String mRecentSearchItem1 = "";
    private String mRecentSearchItem2 = "";
    private String mRecentSearchItem3 = "";
    private String[] mRecentSearchArray = new String[]{mRecentSearchItem1,mRecentSearchItem2,mRecentSearchItem3};

//    private TextView mLoadingErrorTV;
//    private ProgressBar mLoadingPB;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        Resources res = getResources();
        boolean theme = preferences.getBoolean(getString(R.string.pref_theme_key),res.getBoolean(R.bool.pref_theme_default_value));
        if(theme){
            setTheme(R.style.AppThemeDarkSide);
        }
        else{
            setTheme(R.style.AppThemeLightSide);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchBoxET = findViewById(R.id.et_search_box);
        mRecentSearchesRV = findViewById(R.id.rv_search_results);

        mRecentSearchesRV.setLayoutManager(new LinearLayoutManager(this));
        mRecentSearchesRV.setHasFixedSize(true);

        mRecentSearchAdapter = new RecentSearchAdapter(this);
        mRecentSearchesRV.setAdapter(mRecentSearchAdapter);

        if (savedInstanceState != null) {

            String[] getsavedrecents;
            getsavedrecents = savedInstanceState.getStringArray("savedStateKEY");

            if (getsavedrecents[0] != null)
                mRecentSearchItem1 = getsavedrecents[0];
            mRecentSearchArray[0] = getsavedrecents[0];

            if (getsavedrecents[1] != null)
                mRecentSearchItem2 = getsavedrecents[1];
            mRecentSearchArray[1] = getsavedrecents[1];

            if (getsavedrecents[2] != null)
                mRecentSearchItem3 = getsavedrecents[2];
            mRecentSearchArray[2] = getsavedrecents[2];

            mRecentSearchAdapter.updateSearchResults(mRecentSearchArray);
        }

        Spinner spinner = findViewById(R.id.dropdown);
        ArrayAdapter<CharSequence> spin_adapter = ArrayAdapter.createFromResource(this,
                R.array.dropdown_types, R.layout.spinner_item);
        spin_adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spin_adapter);
        spinner.setOnItemSelectedListener(this);
        SharedPreferences prefs = getSharedPreferences("com.example.swdb", MODE_PRIVATE);
        spinner.setSelection(prefs.getInt("previousDropdown", 0));

       // Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        mRecentSearchAdapter.updateSearchResults(mRecentSearchArray);

        Button searchButton = findViewById(R.id.btn_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = mSearchBoxET.getText().toString();

                if (mRecentSearchItem2 != null && !mRecentSearchItem2.equals("")) {
                    mRecentSearchItem3 = mRecentSearchItem2;
                }

                if (mRecentSearchItem1 != null && !mRecentSearchItem1.equals("")) {
                    mRecentSearchItem2 = mRecentSearchItem1;
                }

                mRecentSearchItem1 = searchQuery;

                mRecentSearchArray[0] = mRecentSearchItem1;
                mRecentSearchArray[1] = mRecentSearchItem2;
                mRecentSearchArray[2] = mRecentSearchItem3;

                SharedPreferences pref = getSharedPreferences("com.example.swdb", MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();
                edit.putString("history1", mRecentSearchItem1);
                edit.putString("history2", mRecentSearchItem2);
                edit.putString("history3", mRecentSearchItem3);
                edit.apply();

                mRecentSearchesRV.setVisibility(View.VISIBLE);

                if (savedInstanceState != null) {
                    mRecentSearchAdapter.updateSearchResults(mRecentSearchArray);
                }
                mRecentSearchAdapter.updateSearchResults(mRecentSearchArray);
                //loadrecents();

                if (!TextUtils.isEmpty(searchQuery)) {
                    doSWSearch(searchQuery);
                }
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("com.example.swdb", MODE_PRIVATE);
        mRecentSearchItem1 = preferences.getString("history1","");
        mRecentSearchItem2 = preferences.getString("history2","");
        mRecentSearchItem3 = preferences.getString("history3","");
        mRecentSearchArray[0] = mRecentSearchItem1;
        mRecentSearchArray[1] = mRecentSearchItem2;
        mRecentSearchArray[2] = mRecentSearchItem3;
        mRecentSearchAdapter.updateSearchResults(mRecentSearchArray);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putStringArray("savedStateKEY", mRecentSearchArray);
    }

    private void doSWSearch(String query) {
        Intent intent = new Intent(this, SWSearchResultsActivity.class);
        intent.putExtra(SWUtils.EXTRA_SW_QUERY, query);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //updated sharedpreferences with parent.getSelectedItem().toString().toLowerCase()
        //defines what we're searching for
        SharedPreferences pref = this.getSharedPreferences("com.example.swdb", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("searchFor", parent.getSelectedItem().toString().toLowerCase());
        edit.putInt("previousDropdown", parent.getSelectedItemPosition());
        edit.apply();
        //%LOCALAPPDATA%\Android\sdk\platform-tools
        //adb devices
        //adb -s <device> shell
        //run-as com.example.swdb
//        Toast.makeText(this, pref.getString("searchFor", "person"),
//                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //do nothing?
    }

    @Override
    public void onSearchItemClick(String search) {
//        Intent intent = new Intent(this, RepoDetailActivity.class);
//        intent.putExtra(GitHubUtils.EXTRA_GITHUB_REPO, repo);
//        startActivity(intent);
        //if (!TextUtils.isEmpty(search)) {
        doSWSearch(search);

    }
//    @Override
//    public void onSearchItemClick(SWPerson person) {
//        Log.d(TAG, "go to " + person.name + "'s page");
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
