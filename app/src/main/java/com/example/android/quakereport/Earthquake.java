package com.example.android.quakereport;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jason on 1/11/18.
 */

public class Earthquake {
    private String mMagnitude;
    private String mLocation;
    private long mTimeInMilliseconds;

    public Earthquake(String magnitude, String location, String timeInMilliseconds) {

        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = Long.parseLong(timeInMilliseconds);
    }

    public Earthquake(String magnitude, String location, long timeInMilliseconds) {

        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
    }

    public String getMagnitude() {
        return mMagnitude;
    }

    public void setMagnitude(String magnitude) {
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

    public String getFormattedDate() {
        //Date date = new Date(mDate);
        Date date = new Date(mTimeInMilliseconds);
        Format formatter = new SimpleDateFormat("MMM dd, yyyy");
        return formatter.format(date);
    }

    public String getFormattedTime() {
        return null;
    }
}
