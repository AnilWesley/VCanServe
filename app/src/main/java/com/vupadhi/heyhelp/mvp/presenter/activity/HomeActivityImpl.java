package com.vupadhi.heyhelp.mvp.presenter.activity;

import android.content.Context;

import com.vupadhi.heyhelp.mvp.base.BasePresenter;
import com.vupadhi.heyhelp.mvp.contract.activity.HomeScreenActivityContract;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.network.models.UserApiModel;

import java.util.Map;

public class HomeActivityImpl extends BasePresenter implements HomeScreenActivityContract.IPresenter{

    private HomeScreenActivityContract.IView forgototp;
    private Context context;

    public HomeActivityImpl(HomeScreenActivityContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.forgototp = forgototp;
    }

    @Override
    public void complaintAdmin(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

    }

    @Override
    public void user_locations(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody, String id) {

        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.user_locations(NetworkConstants.RequestCode.USER_LOCATIONS,requestBody,id);
    }

    @Override
    public void user_signout(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody, String id) {

        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.user_signout(NetworkConstants.RequestCode.USER_SIGNOUT,requestBody,id);
    }

    @Override
    public void my_profile(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody, String id) {
        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.my_profile(NetworkConstants.RequestCode.MY_PROFILE,requestBody,id);
    }


}
