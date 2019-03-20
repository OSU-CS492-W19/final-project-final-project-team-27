package com.example.swdb.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.text.TextUtils;
import android.util.Log;

import com.example.swdb.utils.SWUtils;

public class SWSpeciesRepository implements SWSpeciesAsyncTask.Callback {

    private final static String TAG = SWSpeciesRepository.class.getSimpleName();

    private MutableLiveData<SWUtils.SWItemResult> mResults;

    private String mCurrentQuery;

    public SWSpeciesRepository() {
        mResults = new MutableLiveData<>();
        //mResults.setValue(null);

        mCurrentQuery = null;
    }

    public LiveData<SWUtils.SWItemResult> getItemResults() {
        return mResults;
    }

    public void loadItemResults(String query) {
        if (shouldExecuteSearch(query)) {
            mCurrentQuery = query;
            new SWSpeciesAsyncTask(query, this).execute();
        } else {
            Log.d(TAG, "using cached results");
        }
    }

    private boolean shouldExecuteSearch(String query) {
        return !TextUtils.equals(query, mCurrentQuery);
    }

    @Override
    public void onSearchFinished(SWUtils.SWItemResult results ) {
        Log.d("Repo", results.name);
        mResults.setValue(results);
    }
}
