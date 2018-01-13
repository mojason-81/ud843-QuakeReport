package com.example.android.quakereport;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.Format;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by jason on 1/11/18.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    private final static String LOCATION_SEPARATOR = "of";

    String primaryLocation;
    String locationOffset;

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
       super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_item, parent, false
            );
        }

        Earthquake currentEarthquake = getItem(position);
        String location = currentEarthquake.getLocation();

        if (location.contains(LOCATION_SEPARATOR)) {
            String[] parts = location.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            // Set primaryLocation as the second part and strip leading space
            primaryLocation = parts[1].substring(1, parts[1].length() - 1);
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = location;
        }

        Date date = new Date(currentEarthquake.getTimeInMilliseconds());

        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.primary_location);
        TextView locationOffsetView = (TextView) listItemView.findViewById(R.id.location_offset);
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude);
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);

        primaryLocationView.setText(primaryLocation);
        locationOffsetView.setText(locationOffset);
        magnitudeTextView.setText(currentEarthquake.getMagnitude());
        dateTextView.setText(formatDate(date));
        timeTextView.setText(formatTime(date));

        return listItemView;
    }

    private String formatDate(Date dateObj) {
        Format dateFormatter = new SimpleDateFormat("MMM dd, yyyy");
        return dateFormatter.format(dateObj);
    }

    private String formatTime(Date dateObj) {
        Format timeFormatter = new SimpleDateFormat("h:mm a");
        return timeFormatter.format(dateObj);
    }
}
