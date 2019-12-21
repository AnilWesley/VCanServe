package com.vupadhi.heyhelp.ui.activity;



import android.os.Bundle;
import android.os.CountDownTimer;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.app.constants.AppConstants;
import com.vupadhi.heyhelp.app.controller.ApplicationController;
import com.vupadhi.heyhelp.base.BaseAbstractActivity;
import com.vupadhi.heyhelp.customfonts.CustomTextViewBold;
import com.vupadhi.heyhelp.mvp.contract.activity.OtpActivityContract;
import com.vupadhi.heyhelp.mvp.presenter.activity.OTPActivityImpl;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OtpActivity extends BaseAbstractActivity<OTPActivityImpl> implements OtpActivityContract.IView, APIResponseCallback {
    private ImageView back_arrow;
    private CustomTextViewBold ctvb_submit;
    String nClientid,mobile,status;
    APIResponseCallback apiResponseCallback;
    EditText et1,et2,et3,et4;
    String OTP,resultstring;
    TextView mobile_text;

    LinearLayout resend_ll;
    TextView timetext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       presenter.start();

    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_otp, null);
        return view;
    }

    @Override
    public void setPresenter() {

        presenter = new OTPActivityImpl(this, this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        apiResponseCallback=this;

        Bundle bundle = getIntent().getExtras();
        nClientid=bundle.getString("nClientid");
        status=bundle.getString("status");
        resultstring=bundle.getString("tag");

        if (resultstring.equalsIgnoreCase("login")) {
            mobile = bundle.getString("mobile");
        }else {

            if (status.equalsIgnoreCase("individual")) {
                mobile = bundle.getString("mobile");
            } else {
                mobile = bundle.getString("mobile1");
            }
        }
        ctvb_submit = findViewById(R.id.ctvb_submit);
        back_arrow = findViewById(R.id.back_arrow);
        mobile_text = findViewById(R.id.mobile_text);
        resend_ll = findViewById(R.id.resend_ll);
        timetext = findViewById(R.id.timetext);

        mobile_text.setText(mobile);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });




        ctvb_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OTP=et1.getText().toString().trim()+et2.getText().toString().trim()+et3.getText().toString().trim()+et4.getText().toString().trim();


                //               if (resultstring.equalsIgnoreCase("signup")) {

                if (OTP.equalsIgnoreCase("")||OTP.isEmpty()){
                    Toast.makeText(activity, "Please enter otp", Toast.LENGTH_SHORT).show();
                }else {
                    Map<String, String> requestBody = new HashMap<>();
                    requestBody.put("nRegisteredID", nClientid);
                    requestBody.put("sMobileNumber", mobile);
                    requestBody.put("sOTPValue", OTP);
                    presenter.verify_otp(OtpActivity.this, apiResponseCallback, requestBody);
                }
//                    startActivity(new Intent(OtpActivity.this, LoginActivity.class));
 //               }
                /*else if (resultstring.equalsIgnoreCase("forget")) {
                    startActivity(new Intent(OtpActivity.this, ResetPasswordActivity.class));
                }*/
             }
        });




        et1.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (et1.getText().toString().length() == 1)     //size as per your requirement
                {
                    et2.requestFocus();

                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {

            }

        });


        et2.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (et2.getText().toString().length() == 1)     //size as per your requirement
                {
                    et3.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                if (s.length() == 0) {
                    et1.requestFocus();
//                    et1.setSelection(1);
                }
            }

        });
        et3.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (et3.getText().toString().length() == 1)     //size as per your requirement
                {
                    et4.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                if (s.length() == 0) {
                    et2.requestFocus();
  //                  et2.setSelection(1);
                }
            }

        });

        et4.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
              /*  if (et4.getText().toString().length() == 1)     //size as per your requirement
                {
                    et5.requestFocus();
                }*/
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

                if (s.length() == 0) {
                    et3.requestFocus();
//                    et3.setSelection(1);
                    // et3.getText().clearSpans();

                }
            }
        });




        new CountDownTimer(20000, 1000) {

            public void onTick(long millisUntilFinished) {

                timetext.setText("  in 00:" + millisUntilFinished / 1000);

                //here you can have your logic to set text to edittext
            }

            public void onFinish() {

                resend_ll.setClickable(true);
                timetext.setText("");
                timetext.setVisibility(View.GONE);

                resend_ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                            Map<String, String> requestBody = new HashMap<>();
                            presenter.resend_otp(OtpActivity.this, apiResponseCallback, requestBody,mobile);



                    }
                });
            }

        }.start();


    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

        Log.d("responseVerification", responseString);

        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {

                Toast.makeText(OtpActivity.this,"Please check your internet connection",Toast.LENGTH_LONG).show();


            } else if (NetworkConstants.RequestCode.VERIFY_OTP == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {


                    Util.getInstance().cusToast(OtpActivity.this, jsonObject.optString("message"));
//                    RegisterPOJO registerPOJO = new Gson().fromJson(responseString, RegisterPOJO.class);
                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_LOGIN_SCREEN);

                } else {
                    et4.setText("");
                    et3.setText("");
                    et2.setText("");
                    et1.setText("");

                    String message = "";
                    JSONObject jsonObject1 = jsonObject.getJSONObject("error");
                    JSONArray jsonArray = jsonObject1.getJSONArray("errors");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                        message = jsonObject2.getString("message");

                    }

                    Util.getInstance().cusToast(OtpActivity.this, message);
//                    Util.getInstance().cusToast(OtpActivity.this, jsonObject.optString("message"));
                }
            } else if (NetworkConstants.RequestCode.RESEND_OTP == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {

                    et4.setText("");
                    et3.setText("");
                    et2.setText("");
                    et1.setText("");
                    JSONObject jsonObject1=jsonObject.getJSONObject("data");

                    Util.getInstance().cusToast(OtpActivity.this, jsonObject1.optString("status"));
                    Util.getInstance().cusToast(OtpActivity.this, jsonObject1.optString("sOTPValue"));

                }
            } else {
                String message = "";
                JSONObject jsonObject1 = jsonObject.getJSONObject("error");
                JSONArray jsonArray = jsonObject1.getJSONArray("errors");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                    message = jsonObject2.getString("message");

                }

                Util.getInstance().cusToast(OtpActivity.this, message);
       //         Util.getInstance().cusToast(OtpActivity.this, jsonObject.optString("message"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }
}
