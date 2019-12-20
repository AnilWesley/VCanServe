package com.vupadhi.heyhelp.mvp.presenter.fragment;

import android.content.Context;

import com.vupadhi.heyhelp.mvp.base.BasePresenter;
import com.vupadhi.heyhelp.mvp.contract.fragment.OffersfragmentContract;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.network.models.UserApiModel;

import java.util.Map;

public class OffersfragmentImpl extends BasePresenter implements OffersfragmentContract.IPresenter{

    public OffersfragmentImpl(Object view, Context context) {
        super(view, context);
    }


    @Override
    public void complaintAdmin(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

    }

    @Override
    public void promotions_offers(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody, String url) {

        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.promotions_offers(NetworkConstants.RequestCode.PROMOTIONS_OFFERS,requestBody,url);
    }
}
