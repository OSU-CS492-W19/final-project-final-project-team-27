package com.example.swdb.data;

import android.os.AsyncTask;
import android.telecom.Call;

import com.example.swdb.utils.NetworkUtils;
import com.example.swdb.utils.SWUtils;

import java.io.IOException;
import java.util.List;

public class SWSearchAsyncTask extends AsyncTask<Void, Void, String> {
    private String mURL;
    private Callback mCallback;

    public interface Callback {
        void onSearchFinished(List<SWPerson> people);
    }

    public SWSearchAsyncTask(String url, Callback callback) {
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
        List<SWPerson> people = null;
        if (s != null) {
            people = SWUtils.parseSWPeopleResults(s);
        }
        mCallback.onSearchFinished(people);
    }
}
