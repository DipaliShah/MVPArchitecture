package com.dipalimvpsample.mvparchitecture.application;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.dipalimvpsample.mvparchitecture.data.DataManager;
import com.dipalimvpsample.mvparchitecture.di.component.ApplicationComponent;
import com.dipalimvpsample.mvparchitecture.di.component.DaggerApplicationComponent;
import com.dipalimvpsample.mvparchitecture.di.module.ApplicationModule;

import javax.inject.Inject;

public class MVPApplication extends Application {
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Inject
    DataManager dataManager;
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

}

