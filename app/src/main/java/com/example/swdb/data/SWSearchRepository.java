package com.example.swdb.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.text.TextUtils;
import android.util.Log;

import com.example.swdb.utils.SWUtils;

public class SWSearchRepository implements SWSearchAsyncTask.Callback {

    private final static String TAG = SWSearchRepository.class.getSimpleName();

    private MutableLiveData<SWSearchResult> mResults;
    private MutableLiveData<Status> mLoadingStatus;

    private String mCurrentQuery;

    public SWSearchRepository() {
        mResults = new MutableLiveData<>();
        mResults.setValue(null);

        mLoadingStatus = new MutableLiveData<>();
        mLoadingStatus.setValue(Status.SUCCESS);

        mCurrentQuery = null;
    }

    public LiveData<SWSearchResult> getSearchResults() {
        return mResults;
    }

    public MutableLiveData<Status> getLoadingStatus() {
        return mLoadingStatus;
    }

    public void loadSearchResults(String query, String category, String sortPref) {
        if (shouldExecuteSearch(query)) {
            mCurrentQuery = query;
            mLoadingStatus.setValue(Status.LOADING);
            String url = SWUtils.buildSWSearchURL(query, category);
            Log.d(TAG, "querying search URL: " + url);
            new SWSearchAsyncTask(url, category, sortPref, this).execute();
        } else {
            Log.d("SWSearchRepo", "using cached results");
        }
    }

    private boolean shouldExecuteSearch(String query) {
        return !TextUtils.equals(query, mCurrentQuery);
    }

    @Override
    public void onSearchFinished(SWSearchResult results ) {
        mResults.setValue(results);
        if (results != null) {
            mLoadingStatus.setValue(Status.SUCCESS);
        } else {
            mLoadingStatus.setValue(Status.ERROR);
        }
    }
}
