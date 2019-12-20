package com.vupadhi.heyhelp.mvp.presenter.fragment;

import android.content.Context;

import com.vupadhi.heyhelp.mvp.base.BasePresenter;
import com.vupadhi.heyhelp.mvp.contract.fragment.PaidHistoryfragmentContract;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.network.models.UserApiModel;

import java.util.Map;

public class PaidHistoryfragmentImpl extends BasePresenter implements PaidHistoryfragmentContract.IPresenter{

    public PaidHistoryfragmentImpl(Object view, Context context) {
        super(view, context);
    }


    @Override
    public void complaintAdmin(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

    }

    @Override
    public void paidhistory_inform(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody, String id) {
        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.paidhistory_inform(NetworkConstants.RequestCode.PAIDHISTORY_INFORM,requestBody,id);
    }
}
