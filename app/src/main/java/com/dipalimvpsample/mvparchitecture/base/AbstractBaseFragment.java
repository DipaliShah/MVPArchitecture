package com.dipalimvpsample.mvparchitecture.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.dipalimvpsample.mvparchitecture.R;
import com.dipalimvpsample.mvparchitecture.data.DataManager;
import com.dipalimvpsample.mvparchitecture.di.component.ActivityComponent;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Mitesh on 10/25/2016.
 * Base fragment
 */

public abstract class AbstractBaseFragment extends Fragment implements BaseView {
    protected View abstractBaseFragmentView;
    @Inject
    DataManager mDataManager;
    private AbstractBaseActivity mActivity;
    private Unbinder mUnBinder;
    private ProgressDialog mProgressDialog;

    private LayoutInflater inflater;
    private ViewGroup container;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        abstractBaseFragmentView = inflater.inflate(getContentView(), container, false);
        ButterKnife.bind(this, abstractBaseFragmentView);
        onViewReady(savedInstanceState);
        return abstractBaseFragmentView;
    }

    @CallSuper
    protected void onViewReady(Bundle savedInstanceState) {
        resolveDaggerDependency();
        //To be used by child activities
    }

    protected void resolveDaggerDependency() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AbstractBaseActivity) {
            AbstractBaseActivity activity = (AbstractBaseActivity) context;
            this.mActivity = activity;
            activity.onFragmentAttached();
        }
    }

    public ActivityComponent getActivityComponent() {
        if (mActivity != null) {
            return mActivity.getActivityComponent();
        }
        return null;
    }

    public AbstractBaseActivity getBaseActivity() {
        return mActivity;
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    public void showthrow(Throwable t) {
        hideDialog();
        showAlertDialog(t.getMessage());
    }

    @Override
    public void showErrorToast() {
        hideDialog();
        showToast(getActivity().getString(R.string.some_error));
    }

    protected void showAlertDialog(String msg) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle(null);
        dialogBuilder.setIcon(R.mipmap.ic_launcher);
        dialogBuilder.setMessage(msg);
        dialogBuilder.setPositiveButton(getString(R.string.dialog_ok_btn), (dialog, which) -> dialog.cancel());

        dialogBuilder.setCancelable(false);
        dialogBuilder.show();
    }


    @Override
    public void showProgressDialog(String title, String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setOwnerActivity(getActivity());
            //mProgressDialog.setTitle(title);
            //mProgressDialog.setIcon(R.mipmap.ic_launcher);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(false);
        }
        if (!mProgressDialog.isShowing()) {
            //mProgressDialog.setMessage(message);
            mProgressDialog.setMessage(getString(R.string.please_wait_for_second));
            mProgressDialog.show();
        }
    }

    @Override
    public void hideDialog() {
        if (mProgressDialog != null) {
            Activity activity = mProgressDialog.getOwnerActivity();
            if (mProgressDialog.isShowing() && !activity.isFinishing())
                mProgressDialog.dismiss();
        }

    }

    @Override
    public boolean isNetworkConnected() {
        return mActivity != null && mActivity.isNetworkConnected();
    }


    @Override
    public void noInternetConnectionAvailable() {
        hideDialog();
        abstractBaseFragmentView = inflater.inflate(R.layout.view_no_internet, container, false);
        Button btnRetry = abstractBaseFragmentView.findViewById(R.id.btn_retry);
        btnRetry.setOnClickListener(view -> {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(AbstractBaseFragment.this);
            ft.attach(AbstractBaseFragment.this);
            ft.commit();
        });
        abstractBaseFragmentView.invalidate();
    }


    public void noDataAvailable() {
        hideDialog();
        abstractBaseFragmentView = inflater.inflate(R.layout.activity_no_search_result, container, false);
        abstractBaseFragmentView.invalidate();
    }


    protected void showToast(String mToastMsg) {
        hideDialog();
        Toast.makeText(getActivity(), mToastMsg, Toast.LENGTH_LONG).show();
    }


    @Override
    public void onDetach() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        hideDialog();
        mProgressDialog = null;
        super.onDetach();
    }

    public abstract int getContentView();

    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }
}


