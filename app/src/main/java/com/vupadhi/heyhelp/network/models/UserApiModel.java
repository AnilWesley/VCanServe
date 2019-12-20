package com.vupadhi.heyhelp.network.models;

import android.content.Context;

import com.android.volley.Request;
import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.network.volley.APIHandler;


import org.json.JSONObject;

import java.util.Map;

/**
 * Created by PRASAD on 09-Dec-17.
 */

public class UserApiModel extends BaseApiModel {

    public UserApiModel(Context context, APIResponseCallback apiResponseCallback) {
        super(context);
        this.context = context;
        this.apiResponseCallback = apiResponseCallback;
    }

    public void user_individual_locations(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.GET,
                NetworkConstants.URL.USER_INDIVIDUAL_LOCATIONS, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void user_company_locations(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.GET,
                NetworkConstants.URL.USER_COMPANY_LOCATIONS, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void user_locations(@NetworkConstants.RequestCode int requestId, Map<String, String> params,String id) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.GET,
                NetworkConstants.URL.USER_LOCATIONS+id, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void user_register(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.USER_REGISTER, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void verify_otp(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.VERIFY_OTP, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void login(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.LOGIN, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void homescreen(@NetworkConstants.RequestCode int requestId, Map<String, String> params, String id) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.GET,
                NetworkConstants.URL.HOMESCREEN + id, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI2();
    }

    public void domestic_help(@NetworkConstants.RequestCode int requestId, Map<String, String> params, String url) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.GET,
                NetworkConstants.URL.DOMESTIC_HELP + url, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI2();
    }

    public void package_selection(@NetworkConstants.RequestCode int requestId, Map<String, String> params, String id) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.GET,
                NetworkConstants.URL.PACKAGE_SELECTION + id, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI2();
    }

    public void myservice_requests(@NetworkConstants.RequestCode int requestId, Map<String, String> params, String id) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.GET,
                NetworkConstants.URL.MYSERVICE_REQUESTS + id, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI2();
    }

    public void my_profile(@NetworkConstants.RequestCode int requestId, Map<String, String> params, String id) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.GET,
                NetworkConstants.URL.MY_PROFILE + id, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI2();
    }

    public void profilefound_serreq(@NetworkConstants.RequestCode int requestId, Map<String, String> params, String id) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.GET,
                NetworkConstants.URL.PROFILEFOUND_SERREQ + id, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI2();
    }

    public void pendingpay_inform(@NetworkConstants.RequestCode int requestId, Map<String, String> params, String id) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.GET,
                NetworkConstants.URL.PENDINGPAY_INFORM + id, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI2();
    }

    public void paidhistory_inform(@NetworkConstants.RequestCode int requestId, Map<String, String> params, String id) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.GET,
                NetworkConstants.URL.PAIDHISTORY_INFORM + id, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI2();
    }

    public void getworkerlist(@NetworkConstants.RequestCode int requestId, Map<String, String> params, String id) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.GET,
                NetworkConstants.URL.GETWORKERLIST + id, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI2();
    }

    public void raisetickets(@NetworkConstants.RequestCode int requestId, Map<String, String> params, String id) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.GET,
                NetworkConstants.URL.RAISETICKETS + id, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI2();
    }

    public void ticketstatus(@NetworkConstants.RequestCode int requestId, Map<String, String> params, String id) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.GET,
                NetworkConstants.URL.TICKETSTATUS + id, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI2();
    }

    public void feedback_questions(@NetworkConstants.RequestCode int requestId, Map<String, String> params, String id) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.GET,
                NetworkConstants.URL.FEEDBACK_QUESTIONS + id, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI2();
    }

    public void rated_workers(@NetworkConstants.RequestCode int requestId, Map<String, String> params, String id) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.GET,
                NetworkConstants.URL.RATED_WORKERS + id, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI2();
    }

    public void about_us(@NetworkConstants.RequestCode int requestId, Map<String, String> params, String id) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.GET,
                NetworkConstants.URL.ABOUT_US + id, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI2();
    }

    public void contact_us(@NetworkConstants.RequestCode int requestId, Map<String, String> params, String id) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.GET,
                NetworkConstants.URL.CONTACT_US + id, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI2();
    }

    public void promotions_offers(@NetworkConstants.RequestCode int requestId, Map<String, String> params, String url) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.GET,
                NetworkConstants.URL.PROMOTIONS_OFFERS + url, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI2();
    }

    public void payment_method_details(@NetworkConstants.RequestCode int requestId, Map<String, String> params, String id) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.GET,
                NetworkConstants.URL.PAYMENT_METHOD_DETAILS + id, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI2();
    }

    public void deployment_notiflist(@NetworkConstants.RequestCode int requestId, Map<String, String> params, String id) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.GET,
                NetworkConstants.URL.DEPLOYMENT_NOTIFLIST + id, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI2();
    }

    public void user_signout(@NetworkConstants.RequestCode int requestId, Map<String, String> params, String id) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.GET,
                NetworkConstants.URL.USER_SIGNOUT + id, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI2();
    }

    public void terms_cond(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.GET,
                NetworkConstants.URL.TERMS_COND, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI2();
    }

    public void resend_otp(@NetworkConstants.RequestCode int requestId, Map<String, String> params, String id) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.GET,
                NetworkConstants.URL.RESEND_OTP + id, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI2();
    }


    public void retreive_package(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.RETREIVE_PACKAGE, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI2();
    }

    public void saveraisetickets(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.SAVERAISETICKETS, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI2();
    }

    public void forgot_password(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.FORGOT_PASSWORD, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI2();
    }

    public void change_password(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.CHANGE_PASSWORD, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI2();
    }

    public void savefeedback(@NetworkConstants.RequestCode int requestId, JSONObject params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.SAVEFEEDBACK, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI3();
    }

    public void saveworkerabsence_list(@NetworkConstants.RequestCode int requestId, JSONObject params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.SAVEWORKERABSENCE_LIST, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI3();
    }

    public void savecustomer_rating(@NetworkConstants.RequestCode int requestId, JSONObject params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.SAVECUSTOMER_RATING, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI3();
    }

    public void apply_coupon(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.APPLY_COUPON, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI3();
    }

    public void savedomestic_help(@NetworkConstants.RequestCode int requestId, JSONObject params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.SAVEDOMESTIC_HELP, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI3();
    }

    public void update_profile(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.UPDATE_PROFILE, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI2();
    }

    public void savedomesticservice_help(@NetworkConstants.RequestCode int requestId, JSONObject params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.SAVEDOMESTICSERVICE_HELP, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI3();
    }


    @Override
    public void onAPISuccessResponse(int requestId, String responseString) {
        super.onAPISuccessResponse(requestId, responseString);
        apiResponseCallback.onSuccessResponse(requestId, responseString, "");
    }

    @Override
    public void onAPIFailureResponse(int requestId, String errorString) {
        super.onAPIFailureResponse(requestId, errorString);
        apiResponseCallback.onSuccessResponse(requestId, errorString, "");
    }

}
