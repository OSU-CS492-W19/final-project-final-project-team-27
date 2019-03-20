package com.example.swdb.data;

import android.os.AsyncTask;

import com.example.swdb.utils.NetworkUtils;
import com.example.swdb.utils.SWUtils;

import java.io.IOException;

public class SWSearchAsyncTask extends AsyncTask<Void, Void, String> {
    private String mURL;
    private Callback mCallback;
    private String mCategory;
    private String mSort;

    public interface Callback {
        void onSearchFinished(SWSearchResult results);
    }

    public SWSearchAsyncTask(String url, String category, String sortPref, Callback callback) {
        mURL = url;
        mCallback = callback;
        mCategory = category;
        mSort = sortPref;
    }

    @Override
    protected String doInBackground(Void... voids) {
        if (mURL != null) {
            String results = null;
            try {
                results = NetworkUtils.doHTTPGet(mURL);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return results;
        } else {
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        SWSearchResult results = new SWSearchResult();
        if (s != null) {
            results = SWUtils.parseSWSearchResults(s, mCategory, mSort);
        }
        mCallback.onSearchFinished(results);
    }
}
