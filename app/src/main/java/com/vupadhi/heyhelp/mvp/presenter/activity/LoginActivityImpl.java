package com.vupadhi.heyhelp.mvp.presenter.activity;

import android.content.Context;

import com.vupadhi.heyhelp.mvp.base.BasePresenter;
import com.vupadhi.heyhelp.mvp.contract.activity.LoginActivityContract;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.network.models.UserApiModel;

import java.util.Map;

public class LoginActivityImpl extends BasePresenter implements LoginActivityContract.IPresenter{

    private LoginActivityContract.IView forgototp;
    private Context context;

    public LoginActivityImpl(LoginActivityContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.forgototp = forgototp;
    }

    @Override
    public void complaintAdmin(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

    }

    @Override
    public void login(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.login(NetworkConstants.RequestCode.LOGIN,requestBody);
    }
}
