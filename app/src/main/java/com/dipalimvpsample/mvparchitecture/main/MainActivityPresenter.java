package com.dipalimvpsample.mvparchitecture.main;



import com.dipalimvpsample.mvparchitecture.api.IMyWebAPI;
import com.dipalimvpsample.mvparchitecture.base.BasePresenter;
import com.dipalimvpsample.mvparchitecture.data.DataManager;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Dipali on 3/14/2018.
 */

public class MainActivityPresenter<V extends IMainActivityView> extends BasePresenter<V> implements IMainActivityPresenter<V> {
    private IMyWebAPI mApiService;

    @Inject
    public MainActivityPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void getAllPanchangTimings(double latitude, double longitude, String date, double zone) {
        if (getMvpView().isNetworkConnected()) {
            if (mApiService == null)
                mApiService = provideNewApiService();
            Single<SampleDto> listSingle = mApiService.sunTimings(latitude, longitude, date,zone);
            //Pattern 1.
            listSingle.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<SampleDto>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onSuccess(SampleDto panchangResult) {
                            getMvpView().getAllTimings(panchangResult);
                        }

                        @Override
                        public void onError(Throwable e) {
                            getMvpView().setErrorMessage();
                        }
                    });
            
            // Pattern 2. Or we can use generic subsribe method of BasePresenter as following
            
            subscribeSingle(listSingle,panchangResult ->  getMvpView().getAllTimings(panchangResult),
                           error -> getMvpView().setErrorMessage());
        }
    }
}
