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
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
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


public class MainActivity extends AppCompatActivity implements RecentSearchAdapter.OnSearchItemClickListener, AdapterView.OnItemSelectedListener , SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private EditText mSearchBoxET;
    private RecyclerView mRecentSearchesRV;
    private RecentSearchAdapter mRecentSearchAdapter;


    private SWUtils.SearchDetails[] mRecentSearchArray;
    private SWUtils.SearchDetails mRecentSearchItem1;
    private SWUtils.SearchDetails mRecentSearchItem2;
    private SWUtils.SearchDetails mRecentSearchItem3;

    private String savedStateValue;


//    private TextView mLoadingErrorTV;
//    private ProgressBar mLoadingPB;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchBoxET = findViewById(R.id.et_search_box);
        mRecentSearchesRV = findViewById(R.id.rv_search_results);

        mRecentSearchesRV.setLayoutManager(new LinearLayoutManager(this));
        mRecentSearchesRV.setHasFixedSize(true);

        mRecentSearchAdapter = new RecentSearchAdapter(this);
        mRecentSearchesRV.setAdapter(mRecentSearchAdapter);

        //String searchQuery = mSearchBoxET.getText().toString();
        mRecentSearchItem1 = new SWUtils.SearchDetails();
        mRecentSearchItem2 = new SWUtils.SearchDetails();
        mRecentSearchItem3 = new SWUtils.SearchDetails();


        mRecentSearchArray = new SWUtils.SearchDetails[]{mRecentSearchItem1, mRecentSearchItem2, mRecentSearchItem3};

        if (savedInstanceState != null) {

            String[] getsavedrecents = new String[]{"", "", ""};
            getsavedrecents = savedInstanceState.getStringArray("savedStateKEY");


            if (getsavedrecents[0] != null)
                mRecentSearchItem1.search_item_name = getsavedrecents[0];
            mRecentSearchArray[0].search_item_name = getsavedrecents[0];


            if (getsavedrecents[1] != null)
                mRecentSearchItem2.search_item_name = getsavedrecents[1];
            mRecentSearchArray[1].search_item_name = getsavedrecents[1];


            if (getsavedrecents[2] != null)
                mRecentSearchItem3.search_item_name = getsavedrecents[2];
            mRecentSearchArray[2].search_item_name = getsavedrecents[2];


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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button searchButton = findViewById(R.id.btn_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = mSearchBoxET.getText().toString();
                if (mRecentSearchItem2.search_item_name != null && !mRecentSearchItem2.search_item_name.equals("")) {
                    mRecentSearchItem3.search_item_name = mRecentSearchItem2.search_item_name;
                }

                if (mRecentSearchItem1.search_item_name != null && !mRecentSearchItem1.search_item_name.equals("")) {
                    mRecentSearchItem2.search_item_name = mRecentSearchItem1.search_item_name;

                }

                mRecentSearchItem1.search_item_name = mSearchBoxET.getText().toString();

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
//                Bundle loaderArgs = new Bundle();
//                loaderArgs.putString(SEARCH_URL_KEY, mRecentSearchItem1.search_item_name);

        //mLoadingIndicatorPB.setVisibility(View.VISIBLE);
        //getSupportLoaderManager().restartLoader(RECENT_SEARCH_LOADER_ID, loaderArgs, this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        savedStateValue = mRecentSearchItem1.search_item_name;

        String[] merp = new String[]{"", "", ""};

        merp[0] = mRecentSearchItem1.search_item_name;
        merp[1] = mRecentSearchItem2.search_item_name;
        merp[2] = mRecentSearchItem3.search_item_name;

        outState.putStringArray("savedStateKEY", merp);
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
        Toast.makeText(this, pref.getString("searchFor", "person"),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //do nothing?
    }

    @Override
    public void onSearchItemClick(SWUtils.SearchDetails search) {
//        Intent intent = new Intent(this, RepoDetailActivity.class);
//        intent.putExtra(GitHubUtils.EXTRA_GITHUB_REPO, repo);
//        startActivity(intent);
        //if (!TextUtils.isEmpty(search)) {
        doSWSearch(search.search_item_name);

    }

//    @Override
//    public void onSearchItemClick(SWPerson person) {
//        Log.d(TAG, "go to " + person.name + "'s page");
//    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        //Do Something?
    }
}
