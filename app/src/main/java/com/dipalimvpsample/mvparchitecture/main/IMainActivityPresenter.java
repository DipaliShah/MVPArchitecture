package com.dipalimvpsample.mvparchitecture.main;


import com.dipalimvpsample.mvparchitecture.base.MvpPresenter;

/**
 * Created by Dipali on 3/14/2018.
 */

public interface IMainActivityPresenter<V extends IMainActivityView>
        extends MvpPresenter<V> {
    void getAllPanchangTimings(double lat, double Lng, String date, double zone);
}
