package com.vupadhi.heyhelp.mvp.presenter.activity;

import android.content.Context;

import com.vupadhi.heyhelp.mvp.base.BasePresenter;
import com.vupadhi.heyhelp.mvp.contract.activity.MarkAbsentContract;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.network.models.UserApiModel;

import org.json.JSONObject;

import java.util.Map;

public class MarkAbsentImpl extends BasePresenter implements MarkAbsentContract.IPresenter {

    private MarkAbsentContract.IView forgototp;
    private Context context;

    public MarkAbsentImpl(MarkAbsentContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.forgototp = forgototp;
    }

    @Override
    public void complaintAdmin(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

    }

    @Override
    public void saveworkerabsence_list(Context context, APIResponseCallback apiResponseCallback, JSONObject requestBody) {

        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.saveworkerabsence_list(NetworkConstants.RequestCode.SAVEWORKERABSENCE_LIST,requestBody);
    }

    @Override
    public void getworkerlist(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody, String id) {

        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.getworkerlist(NetworkConstants.RequestCode.GETWORKERLIST,requestBody,id);
    }
}
