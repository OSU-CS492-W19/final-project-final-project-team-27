package com.example.swdb.data;

import android.os.AsyncTask;

import com.example.swdb.utils.NetworkUtils;
import com.example.swdb.utils.SWUtils;

import java.io.IOException;

public class SWSpeciesAsyncTask extends AsyncTask<Void, Void, String> {
    private String mURL;
    private Callback mCallback;

    public interface Callback {
        void onSearchFinished(SWUtils.SWItemResult results);
    }

    public SWSpeciesAsyncTask(String url, Callback callback) {
        mURL = url;
        mCallback = callback;
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
        SWUtils.SWItemResult result = new SWUtils.SWItemResult();
        if (s != null) {
            result = SWUtils.parseSWItem(s);
        }
        mCallback.onSearchFinished(result);
    }
}
