package com.dipalimvpsample.mvparchitecture.main;

import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;
import com.dipalimvpsample.mvparchitecture.R;
import com.dipalimvpsample.mvparchitecture.base.AbstractBaseActivity;
import com.dipalimvpsample.mvparchitecture.data.DataManager;
import com.dipalimvpsample.mvparchitecture.di.component.ActivityComponent;

import javax.inject.Inject;

public class MainActivity extends AbstractBaseActivity implements IMainActivityView {

    @Inject
    DataManager dataManager;

    @Inject
    Gson gson;

    @Inject
    IMainActivityPresenter<IMainActivityView> mPresenter;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
        }
        mPresenter.onAttach(this);
    }

    @Override
    public void getAllTimings(SampleDto dto) {

    }

    @Override
    public void setErrorMessage() {

    }
}
