package com.example.android.quakereport;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jason on 1/11/18.
 */

public class Earthquake {
    private double mMagnitude;
    private String mLocation;
    private long mDate;

    public Earthquake(double magnitude, String location, long date) {

        mMagnitude = magnitude;
        mLocation = location;
        mDate = date;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public void setMagnitude(double magnitude) {
        mMagnitude = magnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public long getDate() {
        return mDate;
    }

    public void setDate(long date) {
        mDate = date;
    }

    public String getMagnitudeAsText() {
        return String.valueOf(mMagnitude);
    }

    public String getFormattedDate() {
        Date date = new Date(mDate);
        Format formatter = new SimpleDateFormat("MMM dd, yyyy");
        return formatter.format(date);
    }
}
