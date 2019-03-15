package com.example.swdb;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.swdb.data.SWPerson;
import com.example.swdb.data.SWSearchRepository;
import com.example.swdb.data.SWSearchResult;
import com.example.swdb.data.Status;

import java.util.List;

public class SWSearchViewModel extends ViewModel {
    private LiveData<SWSearchResult> mResults;
    private LiveData<List<SWPerson>> mPeople;
    private LiveData<Status> mLoadingStatus;
    private SWSearchRepository mRepository;

    public SWSearchViewModel() {
        mRepository = new SWSearchRepository();
        mResults = mRepository.getSearchResults();
        mPeople = mRepository.getPeopleResults();
        mLoadingStatus = mRepository.getLoadingStatus();
    }

    public LiveData<SWSearchResult> getSearchResults() {
        return mResults;
    }

    public LiveData<List<SWPerson>> getPeopleResults() {
        return mPeople;
    }

    public LiveData<Status> getLoadingStatus() {
        return mLoadingStatus;
    }

    public void loadSearchResults(String query) {
        mRepository.loadSearchResults(query);
    }
}
