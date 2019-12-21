package com.vupadhi.heyhelp.mvp.base;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vupadhi.heyhelp.app.controller.ApplicationController;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;


public abstract class BasePresenter<T> implements APIResponseCallback {

    protected T view;
    protected Context context;

    public BasePresenter(T view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void start(){
        ApplicationController.getInstance().setContext(context);
    }

    public void stop(){

    }
    public void launchLocatinScreen(@IBaseContract.PermissionIds int permissionId, @Nullable Object data){

    }

    public void onFragmentLaunch(@IBaseContract.PermissionIds int permissionId, @Nullable Object data){

    }

    @Override
    public void onSuccessResponse(@NetworkConstants.RequestCode int requestId, @NonNull String responseString, @Nullable Object object) {

    }

    @Override
    public void onFailureResponse(@NetworkConstants.RequestCode int requestId, @NonNull String errorString) {

    }
}