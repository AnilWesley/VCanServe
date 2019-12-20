package com.vupadhi.heyhelp.mvp.presenter.fragment;

import android.content.Context;

import com.vupadhi.heyhelp.mvp.base.BasePresenter;
import com.vupadhi.heyhelp.mvp.contract.fragment.PendingPaymentfragmentContract;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.network.models.UserApiModel;

import java.util.Map;

public class PendingPaymentfragmentImpl extends BasePresenter implements PendingPaymentfragmentContract.IPresenter{

    public PendingPaymentfragmentImpl(Object view, Context context) {
        super(view, context);
    }


    @Override
    public void complaintAdmin(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

    }

    @Override
    public void pendingpay_inform(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody, String id) {

        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.pendingpay_inform(NetworkConstants.RequestCode.PENDINGPAY_INFORM,requestBody,id);
    }
}
