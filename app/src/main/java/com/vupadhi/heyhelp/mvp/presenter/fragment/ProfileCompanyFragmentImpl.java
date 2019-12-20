package com.vupadhi.heyhelp.mvp.presenter.fragment;

import android.content.Context;

import com.vupadhi.heyhelp.mvp.base.BasePresenter;
import com.vupadhi.heyhelp.mvp.contract.fragment.ProfileCompanyFragmentContract;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.network.models.UserApiModel;

import java.util.Map;

public class ProfileCompanyFragmentImpl extends BasePresenter implements ProfileCompanyFragmentContract.IPresenter{

    public ProfileCompanyFragmentImpl(Object view, Context context) {
        super(view, context);
    }


    @Override
    public void complaintAdmin(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

    }

    @Override
    public void my_profile(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody, String id) {
        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.my_profile(NetworkConstants.RequestCode.MY_PROFILE,requestBody,id);
    }
}
