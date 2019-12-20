package com.vupadhi.heyhelp.mvp.presenter.activity;

import android.content.Context;

import com.vupadhi.heyhelp.mvp.base.BasePresenter;
import com.vupadhi.heyhelp.mvp.contract.activity.TermsandCondContract;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.network.models.UserApiModel;

import java.util.Map;

public class TermsandCondImpl extends BasePresenter implements TermsandCondContract.IPresenter{

    private TermsandCondContract.IView forgototp;
    private Context context;

    public TermsandCondImpl(TermsandCondContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.forgototp = forgototp;
    }

    @Override
    public void complaintAdmin(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

    }

    @Override
    public void terms_cond(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.terms_cond(NetworkConstants.RequestCode.TERMS_COND,requestBody);
    }
}
