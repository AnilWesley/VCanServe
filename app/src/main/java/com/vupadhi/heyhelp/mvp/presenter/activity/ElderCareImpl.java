package com.vupadhi.heyhelp.mvp.presenter.activity;

import android.content.Context;

import com.vupadhi.heyhelp.mvp.base.BasePresenter;
import com.vupadhi.heyhelp.mvp.contract.activity.ElderCareContract;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.network.models.UserApiModel;

import org.json.JSONObject;

import java.util.Map;

public class ElderCareImpl extends BasePresenter implements ElderCareContract.IPresenter{

    private ElderCareContract.IView forgototp;
    private Context context;

    public ElderCareImpl(ElderCareContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.forgototp = forgototp;
    }

    @Override
    public void complaintAdmin(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

    }
    @Override
    public void savedomesticservice_help(Context context, APIResponseCallback apiResponseCallback, JSONObject requestBody) {

        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.savedomesticservice_help(NetworkConstants.RequestCode.SAVEDOMESTICSERVICE_HELP,requestBody);
    }
}
