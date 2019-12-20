package com.vupadhi.heyhelp.mvp.presenter.activity;

import android.content.Context;

import com.vupadhi.heyhelp.mvp.base.BasePresenter;
import com.vupadhi.heyhelp.mvp.contract.activity.RateandReviewActivityContract;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.network.models.UserApiModel;

import org.json.JSONObject;

import java.util.Map;

public class RateandReviewActivityImpl extends BasePresenter implements RateandReviewActivityContract.IPresenter{

    private RateandReviewActivityContract.IView forgototp;
    private Context context;

    public RateandReviewActivityImpl(RateandReviewActivityContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.forgototp = forgototp;
    }

    @Override
    public void complaintAdmin(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

    }

    @Override
    public void savecustomer_rating(Context context, APIResponseCallback apiResponseCallback, JSONObject requestBody) {

        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.savecustomer_rating(NetworkConstants.RequestCode.SAVECUSTOMER_RATING,requestBody);
    }

    @Override
    public void rated_workers(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody, String id) {

        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.rated_workers(NetworkConstants.RequestCode.RATED_WORKERS,requestBody,id);
    }
}
