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
    private String mDate;

    public Earthquake(String magnitude, String location, String date) {

        mMagnitude = magnitude;
        mLocation = location;
        mDate = date;
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

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getFormattedDate() {
        //Date date = new Date(mDate);
        Date date = new Date(Long.parseLong(mDate));
        Format formatter = new SimpleDateFormat("MMM dd, yyyy");
        return formatter.format(date);
    }
}
