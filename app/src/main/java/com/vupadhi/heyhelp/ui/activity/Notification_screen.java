package com.vupadhi.heyhelp.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.base.BaseAbstractActivity;
import com.vupadhi.heyhelp.mvp.contract.activity.NotificationScreenContract;
import com.vupadhi.heyhelp.mvp.presenter.activity.NotificationScreenImpl;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;

public class Notification_screen extends BaseAbstractActivity<NotificationScreenImpl> implements NotificationScreenContract.IView, APIResponseCallback {
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();


    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_notification_screen, null);
        return view;
    }

    @Override
    public void setPresenter() {

        presenter = new NotificationScreenImpl(this, this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        imageView=findViewById(R.id.notification_back_but);
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
