package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by jason on 1/11/18.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    private final static String LOCATION_SEPARATOR = "of";

    public EarthquakeAdapter(Activity context, List<Earthquake> earthquakes) {
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
        String location;
        try {
            location = currentEarthquake.getLocation();
        } catch (NullPointerException e) {
            location = "Nowhere";
        }

        String primaryLocation;
        String locationOffset;
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
        String formattedMagnitude = formatMagnitude(currentEarthquake.getMagnitude());

        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.primary_location);
        TextView locationOffsetView = (TextView) listItemView.findViewById(R.id.location_offset);
        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);

        primaryLocationView.setText(primaryLocation);
        locationOffsetView.setText(locationOffset);
        magnitudeView.setText(formattedMagnitude);
        dateTextView.setText(formatDate(date));
        timeTextView.setText(formatTime(date));

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);
        Log.v("color", String.valueOf(magnitudeColor));

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

    private String formatMagnitude(double mag) {
        DecimalFormat magFormatter = new DecimalFormat("0.0");
        return magFormatter.format(mag);
    }

    private int getMagnitudeColor(double mag){
        int resourceId;
        switch (String.valueOf(mag).substring(0, 1)) {
            case "2":
                resourceId =  R.color.magnitude2;
                break;
            case "3":
                resourceId = R.color.magnitude3;
                break;
            case "4":
                resourceId = R.color.magnitude4;
                break;
            case "5":
                resourceId =  R.color.magnitude5;
                break;
            case "6":
                resourceId = R.color.magnitude6;
                break;
            case "7":
                resourceId = R.color.magnitude7;
                break;
            case "8":
                resourceId = R.color.magnitude8;
                break;
            case "9":
                resourceId = R.color.magnitude9;
                break;
            case "1":
                if(mag > 2.0) {
                    resourceId = R.color.magnitude10plus;
                }
                else {
                    resourceId = R.color.magnitude1;
                }
                break;
            default:
                resourceId = R.color.colorWhite;
                break;
        }
        return ContextCompat.getColor(getContext(), resourceId);
    }
}
