package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

/**
 * Created by jason on 1/15/18.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {
    private static String LOG_TAG = "EarthquakeLoader";
    private String mUrl;

    public EarthquakeLoader(@NonNull Context context, String url) {
        super(context);
        Log.d(LOG_TAG, "CHECK: EarthquakeLoader Constructor() called...");
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.d(LOG_TAG, "CHECK: onStartLoading() called...");
        forceLoad();
    }

    @Nullable
    @Override
    public List<Earthquake> loadInBackground() {
        Log.d(LOG_TAG, "CHECK: loadInBackground() called...");
        if (mUrl == null | mUrl.length() < 1) {
            return null;
        }
        return QueryUtils.fetchEarthquakeData(mUrl);
    }

}
