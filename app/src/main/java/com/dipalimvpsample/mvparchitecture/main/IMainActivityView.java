package com.dipalimvpsample.mvparchitecture.main;


import com.dipalimvpsample.mvparchitecture.base.BaseView;

/**
 * Created by Dipali on 3/14/2018.
 */

public interface IMainActivityView extends BaseView {

    void getAllTimings(SampleDto dto);

    void setErrorMessage();
}
