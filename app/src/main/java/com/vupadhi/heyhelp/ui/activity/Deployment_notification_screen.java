package com.vupadhi.heyhelp.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.vupadhi.heyhelp.Adapter.DeploymentNotifRecyclerAdapter;
import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.base.BaseAbstractActivity;
import com.vupadhi.heyhelp.customfonts.CustomTextViewSemiBold;
import com.vupadhi.heyhelp.models.DeploymentNotifModel;
import com.vupadhi.heyhelp.mvp.contract.activity.DeploymentNotificationScreenContract;
import com.vupadhi.heyhelp.mvp.presenter.activity.DeploymentNotifScreenImpl;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.sharepref.UserSession;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Deployment_notification_screen extends BaseAbstractActivity<DeploymentNotifScreenImpl> implements DeploymentNotificationScreenContract.IView, APIResponseCallback {
    ImageView imageView;
    CustomTextViewSemiBold textView;
    RecyclerView notif_rv;
    DeploymentNotifModel deploymentNotifModel;
    DeploymentNotifRecyclerAdapter adapter;

    APIResponseCallback apiResponseCallback;
    UserSession userSession;
    String nClientid;
    TextView nodata_tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();

    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_deployment_notification_screen, null);
        return view;
    }

    @Override
    public void setPresenter() {

        presenter = new DeploymentNotifScreenImpl(this, this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        imageView = findViewById(R.id.deployment_back_but);
        textView = findViewById(R.id.pay_now_text);
        notif_rv = findViewById(R.id.notif_rv);
        nodata_tv = findViewById(R.id.nodata_tv);
        apiResponseCallback = this;
        userSession = new UserSession(Deployment_notification_screen.this);
        nClientid = userSession.getUserDetails().get(UserSession.KEY_nCLIENTID);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Deployment_notification_screen.this, Package_selection_screen.class);
                startActivity(intent);
            }
        });

        Map<String, String> requestBody = new HashMap<>();
        presenter.deployment_notiflist(Deployment_notification_screen.this, apiResponseCallback, requestBody, nClientid);

    }


    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {

                Toast.makeText(Deployment_notification_screen.this, "Please check your internet connection", Toast.LENGTH_LONG).show();


            } else if (NetworkConstants.RequestCode.DEPLOYMENT_NOTIFLIST == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {

                    deploymentNotifModel = new Gson().fromJson(responseString, DeploymentNotifModel.class);

                    nodata_tv.setVisibility(View.GONE);
                    notif_rv.setVisibility(View.VISIBLE);
                    if (deploymentNotifModel.getData().getNotification().size() > 0) {
                        nodata_tv.setVisibility(View.GONE);
                        notif_rv.setVisibility(View.VISIBLE);

                        LinearLayoutManager mLayoutManager = new LinearLayoutManager(Deployment_notification_screen.this);
                        //setting horizontal list
                        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        notif_rv.setLayoutManager(mLayoutManager);
                        notif_rv.setItemAnimator(new DefaultItemAnimator());

                        //Adding Adapter to recyclerView
                        adapter = new DeploymentNotifRecyclerAdapter(deploymentNotifModel, Deployment_notification_screen.this);
                        notif_rv.setAdapter(adapter);
                    }else {

                        nodata_tv.setVisibility(View.VISIBLE);
                        notif_rv.setVisibility(View.GONE);
                    }


                } else {

                    nodata_tv.setVisibility(View.VISIBLE);
                    notif_rv.setVisibility(View.GONE);
                    //                 Util.getInstance().cusToast(getActivity(), jsonObject.optString("message"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }
}
