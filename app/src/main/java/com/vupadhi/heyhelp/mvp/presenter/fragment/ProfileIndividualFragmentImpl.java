package com.vupadhi.heyhelp.mvp.presenter.fragment;

import android.content.Context;

import com.vupadhi.heyhelp.mvp.base.BasePresenter;
import com.vupadhi.heyhelp.mvp.contract.fragment.ProfileIndividualFragmentContract;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.network.models.UserApiModel;

import java.util.Map;

public class ProfileIndividualFragmentImpl extends BasePresenter implements ProfileIndividualFragmentContract.IPresenter{

    public ProfileIndividualFragmentImpl(Object view, Context context) {
        super(view, context);
    }


    @Override
    public void complaintAdmin(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

    }

    @Override
    public void update_profile(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.update_profile(NetworkConstants.RequestCode.UPDATE_PROFILE,requestBody);
    }

    @Override
    public void my_profile(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody, String id) {
        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.my_profile(NetworkConstants.RequestCode.MY_PROFILE,requestBody,id);
    }
}
