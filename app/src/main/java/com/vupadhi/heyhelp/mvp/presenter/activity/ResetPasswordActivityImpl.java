package com.vupadhi.heyhelp.mvp.presenter.activity;

import android.content.Context;

import com.vupadhi.heyhelp.mvp.base.BasePresenter;
import com.vupadhi.heyhelp.mvp.contract.activity.ResetPasswordActivityContract;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.network.models.UserApiModel;

import java.util.Map;

public class ResetPasswordActivityImpl extends BasePresenter implements ResetPasswordActivityContract.IPresenter{

    private ResetPasswordActivityContract.IView forgototp;
    private Context context;

    public ResetPasswordActivityImpl(ResetPasswordActivityContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.forgototp = forgototp;
    }

    @Override
    public void complaintAdmin(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

    }

    @Override
    public void change_password(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.change_password(NetworkConstants.RequestCode.CHANGE_PASSWORD,requestBody);
    }
}
