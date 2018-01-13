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
    private long mTimeInMilliseconds;

    public Earthquake(String magnitude, String location, String timeInMilliseconds) {

        mMagnitude = Double.parseDouble(magnitude);
        mLocation = location;
        mTimeInMilliseconds = Long.parseLong(timeInMilliseconds);
    }

    public Earthquake(String magnitude, String location, long timeInMilliseconds) {

        mMagnitude = Double.parseDouble(magnitude);
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
    }

    public Earthquake(double magnitude, String location, String timeInMilliseconds) {

        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = Long.parseLong(timeInMilliseconds);
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public void setMagnitude(String magnitude) {
        mMagnitude = Double.parseDouble(magnitude);
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

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public void setTimeInMilliseconds(String timeInMilliseconds) {
        mTimeInMilliseconds = Long.parseLong(timeInMilliseconds);
    }

    public void setTimeInMilliseconds(long timeInMilliseconds) {
        mTimeInMilliseconds = timeInMilliseconds;
    }
}
