package com.vupadhi.heyhelp.mvp.presenter.activity;

import android.content.Context;

import com.vupadhi.heyhelp.mvp.base.BasePresenter;
import com.vupadhi.heyhelp.mvp.contract.activity.FeedbackDomesticHelpScreenContract;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.network.models.UserApiModel;

import org.json.JSONObject;

import java.util.Map;

public class FeedbackDomesticHelpScreenImpl extends BasePresenter implements FeedbackDomesticHelpScreenContract.IPresenter{

    private FeedbackDomesticHelpScreenContract.IView forgototp;
    private Context context;

    public FeedbackDomesticHelpScreenImpl(FeedbackDomesticHelpScreenContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.forgototp = forgototp;
    }

    @Override
    public void complaintAdmin(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

    }

    @Override
    public void feedback_questions(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody, String id) {

        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.feedback_questions(NetworkConstants.RequestCode.FEEDBACK_QUESTIONS,requestBody,id);
    }

    @Override
    public void savefeedback(Context context, APIResponseCallback apiResponseCallback, JSONObject requestBody) {
        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.savefeedback(NetworkConstants.RequestCode.SAVEFEEDBACK,requestBody);
    }
}
