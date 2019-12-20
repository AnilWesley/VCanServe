package com.vupadhi.heyhelp.mvp.presenter.activity;

import android.content.Context;

import com.vupadhi.heyhelp.mvp.base.BasePresenter;
import com.vupadhi.heyhelp.mvp.contract.activity.AboutUsContract;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.network.models.UserApiModel;

import java.util.Map;

public class AboutUsImpl extends BasePresenter implements AboutUsContract.IPresenter{

    private AboutUsContract.IView forgototp;
    private Context context;

    public AboutUsImpl(AboutUsContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.forgototp = forgototp;
    }

    @Override
    public void complaintAdmin(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

    }

    @Override
    public void about_us(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody, String id) {

        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.about_us(NetworkConstants.RequestCode.ABOUT_US,requestBody,id);
    }
}
