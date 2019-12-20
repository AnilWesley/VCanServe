package com.vupadhi.heyhelp.mvp.presenter.activity;

import android.content.Context;

import com.vupadhi.heyhelp.mvp.base.BasePresenter;
import com.vupadhi.heyhelp.mvp.contract.activity.DeploymentNotificationScreenContract;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.network.models.UserApiModel;

import java.util.Map;

public class DeploymentNotifScreenImpl extends BasePresenter implements DeploymentNotificationScreenContract.IPresenter{

    private DeploymentNotificationScreenContract.IView forgototp;
    private Context context;

    public DeploymentNotifScreenImpl(DeploymentNotificationScreenContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.forgototp = forgototp;
    }

    @Override
    public void complaintAdmin(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

    }

    @Override
    public void deployment_notiflist(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody, String id) {
        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.deployment_notiflist(NetworkConstants.RequestCode.DEPLOYMENT_NOTIFLIST,requestBody,id);
    }
}
