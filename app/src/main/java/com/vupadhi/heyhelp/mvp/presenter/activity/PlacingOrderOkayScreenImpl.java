package com.vupadhi.heyhelp.mvp.presenter.activity;

import android.content.Context;

import com.vupadhi.heyhelp.mvp.base.BasePresenter;
import com.vupadhi.heyhelp.mvp.contract.activity.PlacingOrderOkayContract;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;

import java.util.Map;

public class PlacingOrderOkayScreenImpl extends BasePresenter implements PlacingOrderOkayContract.IPresenter{

    private PlacingOrderOkayContract.IView forgototp;
    private Context context;

    public PlacingOrderOkayScreenImpl(PlacingOrderOkayContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.forgototp = forgototp;
    }

    @Override
    public void complaintAdmin(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

    }
}
