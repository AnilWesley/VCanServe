package com.vupadhi.heyhelp.mvp.presenter.activity;

import android.content.Context;

import com.vupadhi.heyhelp.mvp.base.BasePresenter;
import com.vupadhi.heyhelp.mvp.contract.activity.PackageSelectionScreenContract;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.network.models.UserApiModel;

import java.util.Map;

public class PackageSelectionScreenImpl extends BasePresenter implements PackageSelectionScreenContract.IPresenter{

    private PackageSelectionScreenContract.IView forgototp;
    private Context context;

    public PackageSelectionScreenImpl(PackageSelectionScreenContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.forgototp = forgototp;
    }

    @Override
    public void complaintAdmin(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

    }

    @Override
    public void package_selection(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody,String id) {

        UserApiModel userApiModel = new UserApiModel(context,apiResponseCallback);
        userApiModel.package_selection(NetworkConstants.RequestCode.PACKAGE_SELECTION,requestBody,id);
    }
}
