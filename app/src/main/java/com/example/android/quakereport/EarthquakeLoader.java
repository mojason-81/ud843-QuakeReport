package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.android.quakereport.Earthquake;
import com.example.android.quakereport.QueryUtils;

import java.util.List;

/**
 * Created by jason on 1/15/18.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {
    private static String LOG_TAG = "EarthquakeLoader";
    private String mUrl;

    public EarthquakeLoader(@NonNull Context context, String url) {
        super(context);
        Log.d(LOG_TAG, "EarthquakeLoader Constructor");
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.d(LOG_TAG, "onStartLoading");
        forceLoad();
    }

    @Nullable
    @Override
    public List<Earthquake> loadInBackground() {
        Log.d(LOG_TAG, "loadInBackground");
        if (mUrl == null || mUrl.length() < 1) {
            return null;
        }
        return QueryUtils.fetchEarthquakeData(mUrl);
    }

}
