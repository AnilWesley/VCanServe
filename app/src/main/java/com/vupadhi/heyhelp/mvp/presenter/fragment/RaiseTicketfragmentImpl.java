package com.vupadhi.heyhelp.mvp.presenter.fragment;

import android.content.Context;

import com.vupadhi.heyhelp.mvp.base.BasePresenter;
import com.vupadhi.heyhelp.mvp.contract.fragment.RaiseTicketFragmentContract;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.network.models.UserApiModel;

import java.util.Map;

public class RaiseTicketfragmentImpl extends BasePresenter implements RaiseTicketFragmentContract.IPresenter{

    public RaiseTicketfragmentImpl(Object view, Context context) {
        super(view, context);
    }


    @Override
    public void complaintAdmin(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

    }

    @Override
    public void raisetickets(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody, String id) {

        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.raisetickets(NetworkConstants.RequestCode.RAISETICKETS, requestBody, id);
    }

    @Override
    public void saveraisetickets(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.saveraisetickets(NetworkConstants.RequestCode.SAVERAISETICKETS, requestBody);
    }
}
