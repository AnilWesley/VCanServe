package com.vupadhi.heyhelp.mvp.presenter.activity;

import android.content.Context;

import com.vupadhi.heyhelp.mvp.base.BasePresenter;
import com.vupadhi.heyhelp.mvp.contract.activity.PaymentDetailsScreenContract;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.network.models.UserApiModel;

import java.util.Map;

public class PaymentDetailsScreenImpl extends BasePresenter implements PaymentDetailsScreenContract.IPresenter{

    private PaymentDetailsScreenContract.IView forgototp;
    private Context context;

    public PaymentDetailsScreenImpl(PaymentDetailsScreenContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.forgototp = forgototp;
    }

    @Override
    public void complaintAdmin(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

    }

    @Override
    public void retreive_package(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.retreive_package(NetworkConstants.RequestCode.RETREIVE_PACKAGE,requestBody);
    }

    @Override
    public void apply_coupon(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.apply_coupon(NetworkConstants.RequestCode.APPLY_COUPON,requestBody);
    }

    @Override
    public void package_selection(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody,String id) {

        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.package_selection(NetworkConstants.RequestCode.PACKAGE_SELECTION,requestBody,id);
    }
}
