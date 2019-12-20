package com.vupadhi.heyhelp.ui.activity;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.base.BaseAbstractActivity;
import com.vupadhi.heyhelp.mvp.contract.activity.AboutUsContract;
import com.vupadhi.heyhelp.mvp.presenter.activity.AboutUsImpl;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.sharepref.UserSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AboutUsActivity extends BaseAbstractActivity<AboutUsImpl> implements AboutUsContract.IView, APIResponseCallback {
    private ImageView back_arrow;
    TextView aboutus_tv, policy_tv, pricing_tv, termscon_tv, faqs_tv;
    APIResponseCallback apiResponseCallback;
    UserSession userSession;
    String nClientid, aboutus, policy, pricing, termscond, faqs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //       setContentView(R.layout.activity_about_us);
        presenter.start();


    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_about_us, null);
        return view;
    }

    @Override
    public void setPresenter() {

        presenter = new AboutUsImpl(this, this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        apiResponseCallback = this;
        userSession = new UserSession(AboutUsActivity.this);
        nClientid = userSession.getUserDetails().get(UserSession.KEY_nCLIENTID);


        back_arrow = findViewById(R.id.back_arrow);
        policy_tv = findViewById(R.id.policy_tv);
        pricing_tv = findViewById(R.id.pricing_tv);
        termscon_tv = findViewById(R.id.termscon_tv);
        faqs_tv = findViewById(R.id.faqs_tv);
        aboutus_tv = findViewById(R.id.aboutus_tv);

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Map<String, String> requestBody = new HashMap<>();
        presenter.about_us(AboutUsActivity.this, apiResponseCallback, requestBody, nClientid);


    }


    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {

                Toast.makeText(AboutUsActivity.this, "Please check your internet connection", Toast.LENGTH_LONG).show();


            } else if (NetworkConstants.RequestCode.ABOUT_US == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {

                    JSONObject jsonObject1 = jsonObject.getJSONObject("data");

                    JSONArray jsonArray = jsonObject1.getJSONArray("masterContentReqVMList");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject2 = jsonArray.getJSONObject(i);

                        aboutus = jsonObject2.getString("sAboutUs");
                        policy = jsonObject2.getString("sPolicy");
                        pricing = jsonObject2.getString("sPricing");
                        termscond = jsonObject2.getString("sTermsandCond");
                        faqs = jsonObject2.getString("sFAQs");

                    }

                    aboutus_tv.setText(aboutus);
                    policy_tv.setText(policy);
                    pricing_tv.setText(pricing);
                    termscon_tv.setText(termscond);
                    faqs_tv.setText(faqs);


                } else {

                    //                  Util.getInstance().cusToast(Mark_absent.this, jsonObject.optString("message"));
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
