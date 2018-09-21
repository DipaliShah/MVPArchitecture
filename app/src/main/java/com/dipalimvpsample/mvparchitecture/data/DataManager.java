package com.dipalimvpsample.mvparchitecture.data;

import android.content.Context;


import com.dipalimvpsample.mvparchitecture.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * Created by niravj on 2/14/2018.
 */

@Singleton
public class DataManager {

    private Context mContext;
    private SharedPrefsHelper mSharedPrefsHelper;


    @Inject
    public DataManager(@ApplicationContext Context context,
                       SharedPrefsHelper sharedPrefsHelper) {
        mContext = context;
        mSharedPrefsHelper = sharedPrefsHelper;
    }

    public void saveAccessToken(String accessToken) {
        mSharedPrefsHelper.put(SharedPrefsHelper.PREF_KEY_ACCESS_TOKEN, accessToken);
    }

    public String getAccessToken() {
        return mSharedPrefsHelper.get(SharedPrefsHelper.PREF_KEY_ACCESS_TOKEN, null);
    }

    public void setWelcomeScreen() {
        mSharedPrefsHelper.put(SharedPrefsHelper.IS_FIRST_TIME, false);
    }

    public boolean checkForWelcomeScreen() {
        return mSharedPrefsHelper.get(SharedPrefsHelper.IS_FIRST_TIME, true);
    }


    //Double can not store in sharepreference, So we can add as string value
    public void setLocationData(String name, Double latitude, Double longitude) {
        mSharedPrefsHelper.put(SharedPrefsHelper.LOCATION_NAME, name);
        if (longitude > 0.0)
            mSharedPrefsHelper.writeToSharedPreferences(SharedPrefsHelper.LONGITUDE, longitude);

        if (latitude > 0.0)
            mSharedPrefsHelper.writeToSharedPreferences(SharedPrefsHelper.LATITUDE, latitude);
    }

    public String getLocationName() {
        return mSharedPrefsHelper.get(SharedPrefsHelper.LOCATION_NAME, "");
    }

    public double getLatitude() {
        return mSharedPrefsHelper.readDoubleFromSharedPreferences(SharedPrefsHelper.LATITUDE);
    }

    public double getLongitude() {
        return mSharedPrefsHelper.readDoubleFromSharedPreferences(SharedPrefsHelper.LONGITUDE);
    }

    public String getLoginObject() {
        return mSharedPrefsHelper.get(SharedPrefsHelper.OBJECT_JSON, "");
    }

    public void setLoginObject(String objectJson) {
        mSharedPrefsHelper.put(SharedPrefsHelper.OBJECT_JSON, objectJson);
    }
}
