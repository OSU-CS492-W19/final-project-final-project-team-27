package com.example.swdb.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.text.TextUtils;
import android.util.Log;

import com.example.swdb.utils.SWUtils;

import java.util.List;

public class SWSearchRepository implements SWSearchAsyncTask.Callback {

    private final static String TAG = SWSearchRepository.class.getSimpleName();

    private MutableLiveData<List<SWPerson>> mPeople;
    private MutableLiveData<Status> mLoadingStatus;

    private String mCurrentQuery;

    public SWSearchRepository() {
        mPeople = new MutableLiveData<>();
        mPeople.setValue(null);

        mLoadingStatus = new MutableLiveData<>();
        mLoadingStatus.setValue(Status.SUCCESS);

        mCurrentQuery = null;
    }

    public LiveData<List<SWPerson>> getPeopleResults() {
        return mPeople;
    }

    public MutableLiveData<Status> getmLoadingStatus() {
        return mLoadingStatus;
    }

    public void loadSearchResults(String query) {
        if (shouldExecuteSearch(query)) {
            mCurrentQuery = query;
            mPeople.setValue(null);
            mLoadingStatus.setValue(Status.LOADING);
            String url = SWUtils.buildSWSearchURL(query);
            Log.d(TAG, "querying search URL: " + url);
            new SWSearchAsyncTask(url, this).execute();
        } else {
            Log.d("SWSearchRepo", "using cached results");
        }
    }

    private boolean shouldExecuteSearch(String query) {
        return !TextUtils.equals(query, mCurrentQuery);
    }

    @Override
    public void onSearchFinished(List<SWPerson> people) {
        mPeople.setValue(people);
        if (people != null) {
            mLoadingStatus.setValue(Status.SUCCESS);
        } else {
            mLoadingStatus.setValue(Status.ERROR);
        }
    }
}
