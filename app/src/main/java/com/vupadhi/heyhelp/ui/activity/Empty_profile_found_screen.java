package com.vupadhi.heyhelp.ui.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.base.BaseAbstractActivity;
import com.vupadhi.heyhelp.mvp.contract.activity.EmptyProfileFoundScreenContract;
import com.vupadhi.heyhelp.mvp.presenter.activity.EmptyProfileFoundScreenImpl;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;

public class Empty_profile_found_screen extends BaseAbstractActivity<EmptyProfileFoundScreenImpl> implements EmptyProfileFoundScreenContract.IView, APIResponseCallback {
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();


    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_empty_profile_found_screen, null);
        return view;
    }

    @Override
    public void setPresenter() {

        presenter = new EmptyProfileFoundScreenImpl(this, this);

    }
    @Override
    protected void initializeViews() {
        super.initializeViews();

        imageView=findViewById(R.id.empty_back_but);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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
