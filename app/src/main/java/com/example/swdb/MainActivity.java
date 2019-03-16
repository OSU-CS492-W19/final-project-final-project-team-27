package com.example.swdb;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private static final String TAG = MainActivity.class.getSimpleName();

    private EditText mSearchBoxET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchBoxET = findViewById(R.id.et_search_box);
        Spinner spinner = (Spinner) findViewById(R.id.dropdown);
        ArrayAdapter<CharSequence> spin_adapter = ArrayAdapter.createFromResource(this,
                R.array.dropdown_types, R.layout.spinner_item);
        spin_adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spin_adapter);
        spinner.setOnItemSelectedListener(this);

        Button searchButton = findViewById(R.id.btn_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = mSearchBoxET.getText().toString();
                if (!TextUtils.isEmpty(searchQuery)) {
                    doSWSearch(searchQuery);
                }
            }
        });
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
        Toast.makeText(this, parent.getSelectedItem().toString().toLowerCase(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //do nothing?
    }
}
