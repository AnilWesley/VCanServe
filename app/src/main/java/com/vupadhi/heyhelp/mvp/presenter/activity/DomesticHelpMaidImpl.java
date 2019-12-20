package com.vupadhi.heyhelp.mvp.presenter.activity;

import android.content.Context;

import com.vupadhi.heyhelp.mvp.base.BasePresenter;
import com.vupadhi.heyhelp.mvp.contract.activity.DomesticHelpMaidContract;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.network.models.UserApiModel;

import org.json.JSONObject;

import java.util.Map;

public class DomesticHelpMaidImpl extends BasePresenter implements DomesticHelpMaidContract.IPresenter{


    private DomesticHelpMaidContract.IView forgototp;
    private Context context;

    public DomesticHelpMaidImpl(DomesticHelpMaidContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.forgototp = forgototp;
    }

    @Override
    public void complaintAdmin(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

    }

    @Override
    public void domestic_help(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody,String url) {
        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.domestic_help(NetworkConstants.RequestCode.DOMESTIC_HELP,requestBody,url);
    }

    @Override
    public void savedomestic_help(Context context, APIResponseCallback apiResponseCallback, JSONObject requestBody) {

        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.savedomestic_help(NetworkConstants.RequestCode.SAVEDOMESTIC_HELP,requestBody);
    }


}
