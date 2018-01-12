package com.example.android.quakereport;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jason on 1/11/18.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
       super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false
            );
        }

        Earthquake currentEarthquake = getItem(position);

        TextView locationTextView = (TextView) listItemView.findViewById(R.id.location);
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude);
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);

        locationTextView.setText(currentEarthquake.getLocation());
        magnitudeTextView.setText(currentEarthquake.getMagnitudeAsText());
        dateTextView.setText(currentEarthquake.getFormattedDate());

        return listItemView;
    }
}
