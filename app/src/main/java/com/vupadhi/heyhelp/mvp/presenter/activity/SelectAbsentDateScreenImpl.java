package com.vupadhi.heyhelp.mvp.presenter.activity;

import android.content.Context;

import com.vupadhi.heyhelp.mvp.base.BasePresenter;
import com.vupadhi.heyhelp.mvp.contract.activity.SelectAbsentDateScreenContract;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;

import java.util.Map;

public class SelectAbsentDateScreenImpl extends BasePresenter implements SelectAbsentDateScreenContract.IPresenter{

    private SelectAbsentDateScreenContract.IView forgototp;
    private Context context;

    public SelectAbsentDateScreenImpl(SelectAbsentDateScreenContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.forgototp = forgototp;
    }

    @Override
    public void complaintAdmin(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

    }
}
