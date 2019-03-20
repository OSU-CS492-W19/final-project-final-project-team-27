package com.example.swdb;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.swdb.data.SWItemRepository;
import com.example.swdb.data.SWSpeciesRepository;
import com.example.swdb.utils.SWUtils;


public class SWSpeciesViewModel extends ViewModel {
    private LiveData<SWUtils.SWItemResult> mResults;
    private SWSpeciesRepository mRepository;

    public SWSpeciesViewModel() {
        mRepository = new SWSpeciesRepository();
        mResults = mRepository.getItemResults();
    }

    public LiveData<SWUtils.SWItemResult> getItemResults() {
        return mResults;
    }

    public void loadItemResults(String query) {
        mRepository.loadItemResults(query);
    }
}
