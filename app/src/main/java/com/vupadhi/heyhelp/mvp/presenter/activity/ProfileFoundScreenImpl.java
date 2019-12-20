package com.vupadhi.heyhelp.mvp.presenter.activity;

import android.content.Context;

import com.vupadhi.heyhelp.mvp.base.BasePresenter;
import com.vupadhi.heyhelp.mvp.contract.activity.ProfileFoundScreenContract;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.network.models.UserApiModel;

import java.util.Map;

public class ProfileFoundScreenImpl extends BasePresenter implements ProfileFoundScreenContract.IPresenter{

    private ProfileFoundScreenContract.IView forgototp;
    private Context context;

    public ProfileFoundScreenImpl(ProfileFoundScreenContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.forgototp = forgototp;
    }

    @Override
    public void complaintAdmin(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

    }

    @Override
    public void profilefound_serreq(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody,String id) {

        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.profilefound_serreq(NetworkConstants.RequestCode.PROFILEFOUND_SERREQ,requestBody,id);
    }
}
