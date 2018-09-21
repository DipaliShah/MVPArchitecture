/*
 * Copyright (c) 2016 Filippo Engidashet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dipalimvpsample.mvparchitecture.base;


import com.dipalimvpsample.mvparchitecture.api.IMyWebAPI;
import com.dipalimvpsample.mvparchitecture.api.RetrofitSingleton;
import com.dipalimvpsample.mvparchitecture.data.DataManager;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 *
 */
public class BasePresenter<V extends BaseView> implements MvpPresenter<V> {
    private final CompositeDisposable mCompositeDisposable;
    private final DataManager mDataManager;
    private V mBaseView;

    @Inject
    public BasePresenter(DataManager dataManager,
                         CompositeDisposable compositeDisposable) {
        this.mDataManager = dataManager;
        this.mCompositeDisposable = compositeDisposable;
    }

    protected <T> void subscribe(Observable<T> observable, Consumer<? super T> onAction, Consumer<Throwable> onError) {
        mCompositeDisposable.add(observable.subscribeOn(Schedulers.io())
                //   .single(observable)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onAction, onError));

    }

    protected <T> void subscribeSingle(Single<T> observable, Consumer<? super T> onAction, Consumer<Throwable> onError) {
        mCompositeDisposable.add(observable.subscribeOn(Schedulers.io())
                //   .single(observable)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onAction, onError));

    }

    @Override
    public void onAttach(V mvpView) {
        mBaseView = mvpView;
    }

    @Override
    public void onDetach() {
        mCompositeDisposable.dispose();
        mBaseView = null;
    }

    public boolean isViewAttached() {
        return mBaseView != null;
    }

    public V getMvpView() {
        return mBaseView;
    }

    protected IMyWebAPI provideNewApiService() {
        RetrofitSingleton retrofitSingleton = RetrofitSingleton.getInstance();
        return retrofitSingleton.provideApiService();
    }

}
