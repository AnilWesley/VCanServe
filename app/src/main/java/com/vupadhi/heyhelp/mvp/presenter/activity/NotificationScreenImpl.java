package com.vupadhi.heyhelp.mvp.presenter.activity;

import android.content.Context;

import com.vupadhi.heyhelp.mvp.base.BasePresenter;
import com.vupadhi.heyhelp.mvp.contract.activity.AboutUsContract;
import com.vupadhi.heyhelp.mvp.contract.activity.NotificationScreenContract;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;

import java.util.Map;

public class NotificationScreenImpl extends BasePresenter implements NotificationScreenContract.IPresenter{

    private AboutUsContract.IView forgototp;
    private Context context;

    public NotificationScreenImpl(NotificationScreenContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.forgototp = forgototp;
    }

    @Override
    public void complaintAdmin(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

    }
}
