package com.vupadhi.heyhelp.mvp.presenter.activity;

import android.content.Context;

import com.vupadhi.heyhelp.mvp.base.BasePresenter;
import com.vupadhi.heyhelp.mvp.contract.activity.OtpActivityContract;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.network.models.UserApiModel;

import java.util.Map;

public class OTPActivityImpl extends BasePresenter implements OtpActivityContract.IPresenter{

    private OtpActivityContract.IView forgototp;
    private Context context;

    public OTPActivityImpl(OtpActivityContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.forgototp = forgototp;
    }

    @Override
    public void complaintAdmin(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

    }

    @Override
    public void verify_otp(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.verify_otp(NetworkConstants.RequestCode.VERIFY_OTP,requestBody);
    }

    @Override
    public void resend_otp(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody, String id) {

        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.resend_otp(NetworkConstants.RequestCode.RESEND_OTP,requestBody,id);
    }
}
