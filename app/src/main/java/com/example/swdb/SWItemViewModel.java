package com.example.swdb;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.swdb.data.SWItemRepository;
import com.example.swdb.utils.SWUtils;


public class SWItemViewModel extends ViewModel {
    private LiveData<SWUtils.SWItemResult> mResults;
    private SWItemRepository mRepository;

    public SWItemViewModel() {
        mRepository = new SWItemRepository();
        mResults = mRepository.getItemResults();
    }

    public LiveData<SWUtils.SWItemResult> getItemResults() {
        return mResults;
    }

    public void loadItemResults(String query) {
        mRepository.loadItemResults(query);
    }
}
