package com.dipalimvpsample.mvparchitecture.data;

import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by niravj on 2/14/2018.
 */

@Singleton
public class SharedPrefsHelper {

    public static String PREF_KEY_ACCESS_TOKEN = "access-token";
    public static final String IS_FIRST_TIME = "is_first_time";
    public static final String OBJECT_JSON = "object_json";

    public static final String LOCATION_NAME = "location_name";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String SEARCH_KEYWORD = "search_keyword";


    private SharedPreferences mSharedPreferences;

    @Inject
    public SharedPrefsHelper(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }

    public void put(String key, String value) {
        mSharedPreferences.edit().putString(key, value).apply();
    }

    public void put(String key, int value) {
        mSharedPreferences.edit().putInt(key, value).apply();
    }

    public void put(String key, float value) {
        mSharedPreferences.edit().putFloat(key, value).apply();
    }

    public void put(String key, boolean value) {
        mSharedPreferences.edit().putBoolean(key, value).apply();
    }

    public String get(String key, String defaultValue) {
        return mSharedPreferences.getString(key, defaultValue);
    }

    public Integer get(String key, int defaultValue) {
        return mSharedPreferences.getInt(key, defaultValue);
    }

    public Float get(String key, float defaultValue) {
        return mSharedPreferences.getFloat(key, defaultValue);
    }

    public Boolean get(String key, boolean defaultValue) {
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    public void writeToSharedPreferences(String key, Double value) {
        mSharedPreferences.edit().putLong(key, Double.doubleToRawLongBits(value)).apply();
    }

    public double readDoubleFromSharedPreferences(String key) {
        return Double.longBitsToDouble(mSharedPreferences.getLong(key, 0));
    }

    public void deleteSavedData(String key) {
        mSharedPreferences.edit().remove(key).apply();
    }
}