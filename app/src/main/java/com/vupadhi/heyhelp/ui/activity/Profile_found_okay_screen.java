package com.vupadhi.heyhelp.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.base.BaseAbstractActivity;
import com.vupadhi.heyhelp.mvp.contract.activity.ProfileFoundOkayScreenContract;
import com.vupadhi.heyhelp.mvp.presenter.activity.ProfileFoundOkayScreenImpl;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;

public class Profile_found_okay_screen extends BaseAbstractActivity<ProfileFoundOkayScreenImpl> implements ProfileFoundOkayScreenContract.IView, APIResponseCallback {
LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();
      // setContentView(R.layout.activity_profile_found_okay_screen);

    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_profile_found_okay_screen, null);
        return view;
    }

    @Override
    public void setPresenter() {

        presenter = new ProfileFoundOkayScreenImpl(this, this);

    }
    @Override
    protected void initializeViews() {
        super.initializeViews();

        linearLayout=findViewById(R.id.profile_okay_lay_out);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(Profile_found_okay_screen.this,Payment_method_screen.class);
                startActivity(i);
    //            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_PAYMENT_METHOD_SCREEN);
            }
        });

    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }
}
