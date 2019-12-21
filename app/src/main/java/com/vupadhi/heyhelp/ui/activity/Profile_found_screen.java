package com.vupadhi.heyhelp.ui.activity;

import android.content.Intent;
import android.os.Handler;
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
import com.vupadhi.heyhelp.adapter.Profile_found_adapter;
import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.app.constants.AppConstants;
import com.vupadhi.heyhelp.app.controller.ApplicationController;
import com.vupadhi.heyhelp.base.BaseAbstractActivity;
import com.vupadhi.heyhelp.models.ProfilesModel;
import com.vupadhi.heyhelp.mvp.contract.activity.ProfileFoundScreenContract;
import com.vupadhi.heyhelp.mvp.presenter.activity.ProfileFoundScreenImpl;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.sharepref.UserSession;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Profile_found_screen extends BaseAbstractActivity<ProfileFoundScreenImpl> implements ProfileFoundScreenContract.IView, APIResponseCallback {
    LinearLayout linearLayout;
    ImageView imageView;
    RecyclerView profilerecycle_view;
    Profile_found_adapter adapter;
    UserSession userSession;
    String nClientid;
    APIResponseCallback apiResponseCallback;
    TextView nodata_tv;
    LinearLayout noprofile_ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();


    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_profile_found_screen, null);
        return view;
    }

    @Override
    public void setPresenter() {

        presenter = new ProfileFoundScreenImpl(this, this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        apiResponseCallback=this;

        userSession = new UserSession(Profile_found_screen.this);
        nClientid = userSession.getUserDetails().get(UserSession.KEY_nCLIENTID);


        Map<String, String> requestBody = new HashMap<>();
        presenter.profilefound_serreq(Profile_found_screen.this, apiResponseCallback, requestBody, nClientid);



        imageView = findViewById(R.id.found_back_but);
        nodata_tv = findViewById(R.id.nodata_tv);
        linearLayout = findViewById(R.id.found_submit_lay_out);
        noprofile_ll = findViewById(R.id.noprofile_ll);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile_found_screen.this, Profile_found_okay_screen.class);
                startActivity(intent);
            }
        });
        profilerecycle_view = findViewById(R.id.profilerecycle_view);

    }


    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {

                Toast.makeText(Profile_found_screen.this, "Please check your internet connection", Toast.LENGTH_LONG).show();


            } else if (NetworkConstants.RequestCode.PROFILEFOUND_SERREQ == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {

//                    JSONObject jsonObject1=jsonObject.getJSONObject("data");

                    linearLayout.setVisibility(View.VISIBLE);


                    ProfilesModel profilesModel = new Gson().fromJson(responseString, ProfilesModel.class);

                    noprofile_ll.setVisibility(View.GONE);
                    profilerecycle_view.setVisibility(View.VISIBLE);


                    if (profilesModel.getData().getSetlist().size()>0) {

                        noprofile_ll.setVisibility(View.GONE);
                        profilerecycle_view.setVisibility(View.VISIBLE);
                        linearLayout.setVisibility(View.VISIBLE);


                        LinearLayoutManager mLayoutManager = new LinearLayoutManager(Profile_found_screen.this);
                        //setting horizontal list
                        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        profilerecycle_view.setLayoutManager(mLayoutManager);
                        profilerecycle_view.setItemAnimator(new DefaultItemAnimator());


                        //Adding Adapter to recyclerView
                        adapter = new Profile_found_adapter(profilesModel, Profile_found_screen.this);
                        profilerecycle_view.setAdapter(adapter);
                    }else {

                        linearLayout.setVisibility(View.GONE);

                        Handler handler = new Handler();
                        handler.postDelayed( new Runnable() {
                            @Override
                            public void run() {

                                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_HOME_SCREEN,new Bundle());

                            }
                        }, 3000 );

                        noprofile_ll.setVisibility(View.VISIBLE);
                        profilerecycle_view.setVisibility(View.GONE);

    //                    Toast.makeText(activity, profilesModel.getData().getProfilenotfoundMsg(), Toast.LENGTH_SHORT).show();

                    }

                } else {

                    linearLayout.setVisibility(View.GONE);

                    Handler handler = new Handler();
                    handler.postDelayed( new Runnable() {
                        @Override
                        public void run() {

                            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_HOME_SCREEN,new Bundle());

                        }
                    }, 3000 );

                    noprofile_ll.setVisibility(View.VISIBLE);
                    profilerecycle_view.setVisibility(View.GONE);

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
