package com.vupadhi.heyhelp.ui.activity;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.vupadhi.heyhelp.adapter.ServiceRequestsRecyclerAdapter;
import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.base.BaseAbstractActivity;
import com.vupadhi.heyhelp.customfonts.CustomTextViewBold;
import com.vupadhi.heyhelp.models.MyServicerequestsModel;
import com.vupadhi.heyhelp.mvp.contract.activity.MyServiceRequestScreenContract;
import com.vupadhi.heyhelp.mvp.presenter.activity.MyServiceRequestScreenImpl;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.sharepref.UserSession;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class My_Service_request_screen extends BaseAbstractActivity<MyServiceRequestScreenImpl> implements MyServiceRequestScreenContract.IView, APIResponseCallback {
    CustomTextViewBold customTextViewBold_one,customTextViewBold_two;
    ImageView imageView;
    LinearLayout linearLayout;
    TextView services_tv;

    RecyclerView requests_rv;
    ServiceRequestsRecyclerAdapter adapter;
    APIResponseCallback apiResponseCallback;
    UserSession userSession;
    String nClientid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();


    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_my__service_request_screen, null);
        return view;
    }

    @Override
    public void setPresenter() {

        presenter = new MyServiceRequestScreenImpl(this, this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        apiResponseCallback = this;
        userSession = new UserSession(My_Service_request_screen.this);
        nClientid = userSession.getUserDetails().get(UserSession.KEY_nCLIENTID);

        imageView=findViewById(R.id.my_service_back_but);
        requests_rv=findViewById(R.id.requests_rv );
        linearLayout=findViewById(R.id.service_pay_now_lay_out);
        customTextViewBold_one=findViewById(R.id.my_service_but_text);
        customTextViewBold_two=findViewById(R.id.new_request_but_text);
        services_tv=findViewById(R.id.services_tv);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Map<String, String> requestBody = new HashMap<>();
        presenter.myservice_requests(My_Service_request_screen.this, apiResponseCallback, requestBody, nClientid);

        customTextViewBold_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(My_Service_request_screen.this, HomeScreenActivity.class);
                intent.putExtra("newrequest","newrequest");
                startActivity(intent);
            }
        });
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(My_Service_request_screen.this, Package_selection_screen.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {

                Toast.makeText(My_Service_request_screen.this, "Please check your internet connection", Toast.LENGTH_LONG).show();


            } else if (NetworkConstants.RequestCode.MYSERVICE_REQUESTS == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {

                    MyServicerequestsModel myServicerequestsModel = new Gson().fromJson(responseString, MyServicerequestsModel.class);

                    services_tv.setVisibility(View.GONE);
                    requests_rv.setVisibility(View.VISIBLE);

                    if (myServicerequestsModel.getData().getStatusVM().size()>0) {

                        services_tv.setVisibility(View.GONE);
                        requests_rv.setVisibility(View.VISIBLE);

                        LinearLayoutManager mLayoutManager = new LinearLayoutManager(My_Service_request_screen.this);
                        //setting horizontal list
                        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        requests_rv.setLayoutManager(mLayoutManager);
                        requests_rv.setItemAnimator(new DefaultItemAnimator());


                        //Adding Adapter to recyclerView
                        adapter = new ServiceRequestsRecyclerAdapter(myServicerequestsModel, My_Service_request_screen.this);
                        requests_rv.setAdapter(adapter);

                    }else {
                        services_tv.setVisibility(View.VISIBLE);
                        requests_rv.setVisibility(View.GONE);
                    }

                } else {

                    services_tv.setVisibility(View.VISIBLE);
                    requests_rv.setVisibility(View.GONE);
  //                  Util.getInstance().cusToast(My_Service_request_screen.this, jsonObject.optString("message"));
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
