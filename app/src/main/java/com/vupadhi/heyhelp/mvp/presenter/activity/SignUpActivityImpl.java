package com.vupadhi.heyhelp.mvp.presenter.activity;

import android.content.Context;

import com.vupadhi.heyhelp.mvp.base.BasePresenter;
import com.vupadhi.heyhelp.mvp.contract.activity.SignUpActivityContract;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.network.models.UserApiModel;

import java.util.Map;

public class SignUpActivityImpl extends BasePresenter implements SignUpActivityContract.IPresenter{

    private SignUpActivityContract.IView forgototp;
    private Context context;

    public SignUpActivityImpl(SignUpActivityContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.forgototp = forgototp;
    }

    @Override
    public void complaintAdmin(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

    }

    @Override
    public void user_individual_locations(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.user_individual_locations(NetworkConstants.RequestCode.USER_INDIVIDUAL_LOCATIONS,requestBody);
    }

    @Override
    public void user_company_locations(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.user_company_locations(NetworkConstants.RequestCode.USER_COMPANY_LOCATIONS,requestBody);
    }

    @Override
    public void user_register(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.user_register(NetworkConstants.RequestCode.USER_REGISTER,requestBody);
    }
}
