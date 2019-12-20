package com.vupadhi.heyhelp.ui.activity;



import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.app.constants.AppConstants;
import com.vupadhi.heyhelp.app.controller.ApplicationController;
import com.vupadhi.heyhelp.base.BaseAbstractActivity;
import com.vupadhi.heyhelp.customfonts.CustomTextViewBold;
import com.vupadhi.heyhelp.mvp.contract.activity.ForgotPasswordContract;
import com.vupadhi.heyhelp.mvp.presenter.activity.ForgotPasswordActivityimpl;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ForgotPasswordActivity extends BaseAbstractActivity<ForgotPasswordActivityimpl> implements ForgotPasswordContract.IView, APIResponseCallback {
    private ImageView back_arrow;
    private CustomTextViewBold ctvb_submit;
    EditText email_et,mobile_et;
    APIResponseCallback apiResponseCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();

    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_forgot_password, null);
        return view;
    }

    @Override
    public void setPresenter() {

        presenter = new ForgotPasswordActivityimpl(this, this);


    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        apiResponseCallback=this;
        ctvb_submit = findViewById(R.id.ctvb_submit);
        back_arrow = findViewById(R.id.back_arrow);
        email_et = findViewById(R.id.email_et);
        mobile_et = findViewById(R.id.mobile_et);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        ctvb_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (isUserCredentialsValid()) {


                    if (mobile_et.getText().toString().length()>0) {
                        Map<String, String> requestBody = new HashMap<>();
                        requestBody.put("sMobileNumber", mobile_et.getText().toString().trim());
                        presenter.forgot_password(ForgotPasswordActivity.this, apiResponseCallback, requestBody);
                    }else if (email_et.getText().toString().length()>0){
                        Map<String, String> requestBody = new HashMap<>();
                        requestBody.put("sEmail", email_et.getText().toString().trim());
                        presenter.forgot_password(ForgotPasswordActivity.this, apiResponseCallback, requestBody);

                    }


                }


               /* Bundle bundle = new Bundle();
                bundle.putString("tag", "forget");
                Intent intent = new Intent(getApplicationContext(), OtpActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
*/
            }
        });

    }

    public boolean isValidEmaillId(String email) {

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    private boolean isUserCredentialsValid() {
        if (mobile_et.getText().toString().length() == 0 && email_et.getText().toString().length() == 0) {

            Toast.makeText(ForgotPasswordActivity.this, "Please enter mobile number or emailid", Toast.LENGTH_SHORT).show();

            return false;
        }else if (mobile_et.getText().toString().length()>0) {
            if (mobile_et.getText().toString().length() < 10) {
                Toast.makeText(ForgotPasswordActivity.this, "Please enter valid mobile number", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else  if (email_et.getText().toString().length() > 0) {
            if (!isValidEmaillId(email_et.getText().toString())) {
                // etEmailId.setError("Enter Mail Id");
                Util.getInstance().cusToast(context, "Enter Valid Email Id");
                return false;
            } else if (email_et.getText().toString().contains(".com.com")) {
                // etEmailId.setError("Enter Mail Id");
                Util.getInstance().cusToast(context, "Enter Valid Email Id");
                return false;
            }
            return true;
        } else
            return true;

        return true;
    }



    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {


        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {

                Toast.makeText(ForgotPasswordActivity.this, "Please check your internet connection", Toast.LENGTH_LONG).show();


            }  else if (NetworkConstants.RequestCode.FORGOT_PASSWORD == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {

                    JSONObject jsonObject1=jsonObject.getJSONObject("data");

                    JSONObject jsonObject2=jsonObject1.getJSONObject("password");


                    Util.getInstance().cusToast(ForgotPasswordActivity.this, jsonObject2.optString("message"));
                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_LOGIN_SCREEN);

                } else {

                    Util.getInstance().cusToast(ForgotPasswordActivity.this, jsonObject.optString("message"));
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
