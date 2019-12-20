package com.vupadhi.heyhelp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.vupadhi.heyhelp.Adapter.PaymentMethodRecyclerAdapter;
import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.base.BaseAbstractActivity;
import com.vupadhi.heyhelp.models.PaymentMethodModel;
import com.vupadhi.heyhelp.mvp.contract.activity.PaymentMethodScreenContract;
import com.vupadhi.heyhelp.mvp.presenter.activity.PaymentMethodScreenImpl;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.sharepref.UserSession;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Payment_method_screen extends BaseAbstractActivity<PaymentMethodScreenImpl> implements PaymentMethodScreenContract.IView, APIResponseCallback {
    ImageView imageView;
    LinearLayout linearLayout;
    RatingBar ratingBar;
    RecyclerView services_rv;
    PaymentMethodModel paymentMethodModel;
    PaymentMethodRecyclerAdapter adapter;
    APIResponseCallback apiResponseCallback;
    UserSession userSession;
    String nClientid;


    String servicetype, timings, gender, language, amount;

    TextView servicetype_tv, timings_tv, gender_tv, language_tv, amount_text, premium_tv, premiumamt_tv, ultprm_tv, ultprmamt_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();


    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_payment_method_screen, null);
        return view;
    }

    @Override
    public void setPresenter() {

        presenter = new PaymentMethodScreenImpl(this, this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        apiResponseCallback = this;
        userSession = new UserSession(Payment_method_screen.this);
        nClientid = userSession.getUserDetails().get(UserSession.KEY_nCLIENTID);


        imageView = findViewById(R.id.pay_method_back_but);
        linearLayout = findViewById(R.id.pay_method_lay_out);
        ratingBar = findViewById(R.id.pay_method_rating_bar);
        services_rv = findViewById(R.id.services_rv);

        servicetype_tv = findViewById(R.id.servicetype_tv);
        timings_tv = findViewById(R.id.timings_tv);
        gender_tv = findViewById(R.id.gender_tv);
        language_tv = findViewById(R.id.language_tv);
        amount_text = findViewById(R.id.amount_text);

        premium_tv = findViewById(R.id.premium_tv);
        premiumamt_tv = findViewById(R.id.premiumamt_tv);
        ultprm_tv = findViewById(R.id.ultprm_tv);
        ultprmamt_tv = findViewById(R.id.ultprmamt_tv);

        ratingBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingBar.setStepSize(4);
                ratingBar.setBackgroundColor(R.style.RatingBar);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Payment_method_screen.this, Register_okay_screen.class);
                intent.putExtra("premiumamount",paymentMethodModel.getData().getRegistrationFee().getCommonAllpackages().getPremiumPackage().getPackageAmount());
                intent.putExtra("ultrapremiumamount",paymentMethodModel.getData().getRegistrationFee().getCommonAllpackages().getUltraPremiumPackage().getPackageAmount());
                startActivity(intent);
            }
        });


        Map<String, String> requestBody = new HashMap<>();
        presenter.payment_method_details(Payment_method_screen.this, apiResponseCallback, requestBody, nClientid);

    }


    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {


        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {

                Toast.makeText(Payment_method_screen.this, "Please check your internet connection", Toast.LENGTH_LONG).show();


            } else if (NetworkConstants.RequestCode.PAYMENT_METHOD_DETAILS == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {

                    paymentMethodModel = new Gson().fromJson(responseString, PaymentMethodModel.class);

                    JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                    JSONObject jsonObject2 = jsonObject1.getJSONObject("registrationFee");

                    servicetype = jsonObject2.getString("serviceType");
                    timings = jsonObject2.getString("timings");
                    gender = jsonObject2.getString("gender");
                    language = jsonObject2.getString("languages");
                    amount = jsonObject2.getString("sAmount");

                    servicetype_tv.setText(servicetype);
                    timings_tv.setText(timings);
                    gender_tv.setText(gender);
                    language_tv.setText(language);
                    amount_text.setText(amount);


                    premium_tv.setText(paymentMethodModel.getData().getRegistrationFee().getCommonAllpackages().getPremiumPackage().getPackagename());
                    premiumamt_tv.setText(paymentMethodModel.getData().getRegistrationFee().getCommonAllpackages().getPremiumPackage().getPackageAmount());
                    ultprm_tv.setText(paymentMethodModel.getData().getRegistrationFee().getCommonAllpackages().getUltraPremiumPackage().getPackagename());
                    ultprmamt_tv.setText(paymentMethodModel.getData().getRegistrationFee().getCommonAllpackages().getUltraPremiumPackage().getPackageAmount());

                    if (paymentMethodModel.getData().getRegistrationFee().getServicesInclude().size() > 0) {

                        LinearLayoutManager mLayoutManager = new LinearLayoutManager(Payment_method_screen.this);
                        //setting horizontal list
                        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        services_rv.setLayoutManager(mLayoutManager);
                        services_rv.setItemAnimator(new DefaultItemAnimator());


                        //Adding Adapter to recyclerView
                        adapter = new PaymentMethodRecyclerAdapter(paymentMethodModel, Payment_method_screen.this);
                        services_rv.setAdapter(adapter);
                    }


                } else {

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
