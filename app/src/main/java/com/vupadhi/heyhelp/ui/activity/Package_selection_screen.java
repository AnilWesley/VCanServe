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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.vupadhi.heyhelp.Adapter.PremiumPkgRecyclerAdapter;
import com.vupadhi.heyhelp.Adapter.UltraPremiumPkgRecyclerAdapter;
import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.app.constants.AppConstants;
import com.vupadhi.heyhelp.app.controller.ApplicationController;
import com.vupadhi.heyhelp.base.BaseAbstractActivity;
import com.vupadhi.heyhelp.models.PackageSelectionModel;
import com.vupadhi.heyhelp.mvp.contract.activity.PackageSelectionScreenContract;
import com.vupadhi.heyhelp.mvp.presenter.activity.PackageSelectionScreenImpl;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.sharepref.UserSession;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Package_selection_screen extends BaseAbstractActivity<PackageSelectionScreenImpl> implements PackageSelectionScreenContract.IView, APIResponseCallback {
    ImageView imageView;
    RadioButton radioButton_one, radioButton_two;
    LinearLayout linearLayout_one, linearLayout_two;
    RecyclerView premiumpkg_rv, ultrapremiumpkg_rv;
    PremiumPkgRecyclerAdapter premiumPkgRecyclerAdapter;
    UltraPremiumPkgRecyclerAdapter ultraPremiumPkgRecyclerAdapter;
    PackageSelectionModel packageSelectionModel;
    APIResponseCallback apiResponseCallback;
    String nClientid, premiumamount, ultrapremiumamount;
    int type;
    RadioButton radio_but, radio_but1;

    CheckBox cb;
    TextView termscon_tv, prmamt_tv, ultraprmamt_tv;

    UserSession userSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();

    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_package_selection_screen, null);
        return view;
    }

    @Override
    public void setPresenter() {

        presenter = new PackageSelectionScreenImpl(this, this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        apiResponseCallback = this;
        userSession = new UserSession(Package_selection_screen.this);
        nClientid = userSession.getUserDetails().get(UserSession.KEY_nCLIENTID);

        premiumamount = getIntent().getStringExtra("premiumamount");
        ultrapremiumamount = getIntent().getStringExtra("ultrapremiumamount");


        imageView = findViewById(R.id.selection_back_but);
        radio_but = findViewById(R.id.radio_but);
        radio_but1 = findViewById(R.id.radio_but1);
        radioButton_one = findViewById(R.id.radio_but_one);
        radioButton_two = findViewById(R.id.radio_but_two);
        linearLayout_one = findViewById(R.id.cancel_lay_out);
        linearLayout_two = findViewById(R.id.procced_pay_lay_out);

        premiumpkg_rv = findViewById(R.id.premiumpkg_rv);
        ultrapremiumpkg_rv = findViewById(R.id.ultrapremiumpkg_rv);
        prmamt_tv = findViewById(R.id.prmamt_tv);
        ultraprmamt_tv = findViewById(R.id.ultraprmamt_tv);

        cb = findViewById(R.id.cb);
        termscon_tv = findViewById(R.id.termscon_tv);

        prmamt_tv.setText(premiumamount);
        ultraprmamt_tv.setText(ultrapremiumamount);

        termscon_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Package_selection_screen.this, TermsandCondActivity.class);
                startActivity(i);
            }
        });

        radio_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radio_but.setChecked(true);
                radio_but1.setChecked(false);
            }
        });

        radio_but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radio_but.setChecked(false);
                radio_but1.setChecked(true);
            }
        });


        Map<String, String> requestBody = new HashMap<>();
        presenter.package_selection(Package_selection_screen.this, apiResponseCallback, requestBody, nClientid);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        linearLayout_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        linearLayout_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cb.isChecked()) {

                    if (radio_but.isChecked()) {

                        Bundle b = new Bundle();
                        b.putString("packageID", "1");
                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_PAYMENT_DETAILS_SCREEN, b);
                    } else {
                        Bundle b = new Bundle();
                        b.putString("packageID", "2");
                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_PAYMENT_DETAILS_SCREEN, b);
                    }
                } else {
                    Toast.makeText(activity, "Please select terms and conditions", Toast.LENGTH_SHORT).show();
                }

//                    Intent intent = new Intent(Package_selection_screen.this, Payment_details_screen.class);
//                startActivity(intent);
            }
        });
        radioButton_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*boolean isCheckable=true;
                if (isCheckable){*/
                radioButton_one.setChecked(true);
                radioButton_two.setChecked(false);
                /*}*/
            }
        });
        radioButton_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*boolean isCheckable=true;
                if (isCheckable){*/
                radioButton_two.setChecked(true);
                radioButton_one.setChecked(false);
                /*}*/
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

                Toast.makeText(Package_selection_screen.this, "Please check your internet connection", Toast.LENGTH_LONG).show();


            } else if (NetworkConstants.RequestCode.PACKAGE_SELECTION == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {

                    JSONObject jsonObject1 = jsonObject.getJSONObject("data");

                    packageSelectionModel = new Gson().fromJson(responseString, PackageSelectionModel.class);

                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(Package_selection_screen.this);
                    //setting horizontal list
                    mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    premiumpkg_rv.setLayoutManager(mLayoutManager);
                    premiumpkg_rv.setItemAnimator(new DefaultItemAnimator());


                    //Adding Adapter to recyclerView
                    premiumPkgRecyclerAdapter = new PremiumPkgRecyclerAdapter(packageSelectionModel, Package_selection_screen.this);
                    premiumpkg_rv.setAdapter(premiumPkgRecyclerAdapter);
                    premiumPkgRecyclerAdapter.setOnItemClickListener(new PremiumPkgRecyclerAdapter.OnitemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {


                        }
                    });


                    LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(Package_selection_screen.this);
                    //setting horizontal list
                    mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
                    ultrapremiumpkg_rv.setLayoutManager(mLayoutManager1);
                    ultrapremiumpkg_rv.setItemAnimator(new DefaultItemAnimator());


                    //Adding Adapter to recyclerView
                    ultraPremiumPkgRecyclerAdapter = new UltraPremiumPkgRecyclerAdapter(packageSelectionModel, Package_selection_screen.this);
                    ultrapremiumpkg_rv.setAdapter(ultraPremiumPkgRecyclerAdapter);
                    ultraPremiumPkgRecyclerAdapter.setOnItemClickListener(new UltraPremiumPkgRecyclerAdapter.OnitemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {


                        }
                    });


                } else {

                    //   Util.getInstance().cusToast(Package_selection_screen.this, jsonObject.optString("message"));
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
