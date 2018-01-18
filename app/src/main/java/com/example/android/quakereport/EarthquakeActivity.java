/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Earthquake>> {

    private EarthquakeAdapter mEarthquakeAdapter;
    private static final String URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";
    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private TextView mEmptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        mEmptyView = (TextView) findViewById(R.id.empty);
        earthquakeListView.setEmptyView(mEmptyView);

        List<Earthquake> earthquakes = new ArrayList<Earthquake>();
        mEarthquakeAdapter = new EarthquakeAdapter(this, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(mEarthquakeAdapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Earthquake currentEarthquake = mEarthquakeAdapter.getItem(position);
                Uri earthquakeUri;
                try {
                    earthquakeUri = Uri.parse(currentEarthquake.getUrl());
                } catch (NullPointerException e) {
                    earthquakeUri = Uri.EMPTY;
                }
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
                startActivity(websiteIntent);
            }
        });

        boolean connected = checkConnection();
        if(connected) {
            getLoaderManager().initLoader(0, null, this);
        } else {
            setNoConnectionMessage();
        }
    }

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int id, Bundle args) {
        Log.d(LOG_TAG, "CHECK: onCreateLoader() called...");
        return new EarthquakeLoader(this, URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> data) {
        Log.d(LOG_TAG, "CHECK: onLoadFinished() called...");

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.loading_spinner);
        progressBar.setVisibility(View.GONE);

        // Clear the adapter of previous earthquake data
        mEarthquakeAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (data != null && !data.isEmpty()) {
            mEarthquakeAdapter.addAll(data);
        }
        mEmptyView.setText(R.string.no_earthquakes);
    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        Log.d(LOG_TAG, "CHECK: onLoaderReset() called...");
        mEarthquakeAdapter.clear();
    }

    private boolean checkConnection() {
        ConnectivityManager connMan = (ConnectivityManager) EarthquakeActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connMan.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    private void setNoConnectionMessage() {
        TextView emptyView = (TextView) findViewById(R.id.empty);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.loading_spinner);
        progressBar.setVisibility(View.GONE);
        emptyView.setText("No Internet Connection");
    }
}
