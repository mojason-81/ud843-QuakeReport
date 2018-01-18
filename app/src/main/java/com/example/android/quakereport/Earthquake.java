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
    private String mUrl;

    public Earthquake(String magnitude, String location, String timeInMilliseconds, String url) {

        mMagnitude = Double.parseDouble(magnitude);
        mLocation = location;
        mTimeInMilliseconds = Long.parseLong(timeInMilliseconds);
        mUrl = url;
    }

    public Earthquake(String magnitude, String location, long timeInMilliseconds, String url) {

        mMagnitude = Double.parseDouble(magnitude);
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
        mUrl = url;
    }

    public Earthquake(double magnitude, String location, String timeInMilliseconds, String url) {

        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = Long.parseLong(timeInMilliseconds);
        mUrl = url;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
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
