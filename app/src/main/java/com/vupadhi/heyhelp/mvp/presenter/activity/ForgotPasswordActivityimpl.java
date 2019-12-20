package com.vupadhi.heyhelp.mvp.presenter.activity;

import android.content.Context;

import com.vupadhi.heyhelp.mvp.base.BasePresenter;
import com.vupadhi.heyhelp.mvp.contract.activity.ForgotPasswordContract;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.network.models.UserApiModel;

import java.util.Map;

public class ForgotPasswordActivityimpl extends BasePresenter implements ForgotPasswordContract.IPresenter{

    private ForgotPasswordContract.IView forgototp;
    private Context context;

    public ForgotPasswordActivityimpl(ForgotPasswordContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.forgototp = forgototp;
    }

    @Override
    public void complaintAdmin(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

    }

    @Override
    public void forgot_password(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.forgot_password(NetworkConstants.RequestCode.FORGOT_PASSWORD,requestBody);
    }
}
