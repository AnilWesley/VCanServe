package com.vupadhi.heyhelp.ui.activity;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.app.constants.AppConstants;
import com.vupadhi.heyhelp.app.controller.ApplicationController;
import com.vupadhi.heyhelp.base.BaseAbstractActivity;
import com.vupadhi.heyhelp.customfonts.CustomTextViewBold;
import com.vupadhi.heyhelp.mvp.contract.activity.ResetPasswordActivityContract;
import com.vupadhi.heyhelp.mvp.presenter.activity.ResetPasswordActivityImpl;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.sharepref.UserSession;
import com.vupadhi.heyhelp.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ResetPasswordActivity extends BaseAbstractActivity<ResetPasswordActivityImpl> implements ResetPasswordActivityContract.IView, APIResponseCallback {
    private ImageView back_arrow;
    private CustomTextViewBold ctvb_submit;
    EditText password_et, confirmpassword_et;
    APIResponseCallback apiResponseCallback;
    String confirmpawd, newpwd,nClientid;
    UserSession userSession;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();


    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_reset_password, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new ResetPasswordActivityImpl(this, this);


    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        apiResponseCallback = this;
        userSession = new UserSession(ResetPasswordActivity.this);
        nClientid = userSession.getUserDetails().get(UserSession.KEY_nCLIENTID);

        ctvb_submit = findViewById(R.id.ctvb_submit);
        back_arrow = findViewById(R.id.back_arrow);
        password_et = findViewById(R.id.password_et);
        confirmpassword_et = findViewById(R.id.confirmpassword_et);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ctvb_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                confirmpawd = confirmpassword_et.getText().toString();
                newpwd = password_et.getText().toString();


                if (isUserCredentialsValid()) {
                    Map<String, String> requestBody = new HashMap<>();

                    requestBody.put("nRegisteredID", nClientid);
                    requestBody.put("sPassword", password_et.getText().toString().trim());
                    requestBody.put("cnfPassword", confirmpassword_et.getText().toString().trim());
                    presenter.change_password(ResetPasswordActivity.this, apiResponseCallback, requestBody);

                }

               /* Bundle bundle = new Bundle();
                bundle.putString("tag", "reset");
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);*/

            }
        });

    }

    private boolean isUserCredentialsValid() {
        if (password_et.getText().toString().trim().length() == 0) {
            Util.getInstance().cusToast(context, "Please enter new password");
            return false;

        } else if (password_et.getText().toString().trim().length() < 6) {
            password_et.requestFocus();
            Util.getInstance().cusToast(context,"password should be minimum 6 characters");
            return false;

        } else if (confirmpassword_et.getText().toString().trim().length() == 0) {
            confirmpassword_et.requestFocus();
            Util.getInstance().cusToast(context, "Please enter confirm password");
            return false;


        }else if (password_et.getText().toString().trim().length() != confirmpassword_et.getText().toString().trim().length()) {
            confirmpassword_et.requestFocus();
            // newpassedt.setError(getString(R.string.password_not_matched));
            Util.getInstance().cusToast(context,"password not matched");
            return false;


        }
        else if (!newpwd.equalsIgnoreCase( confirmpawd)) {
            confirmpassword_et.requestFocus();
            // newpassedt.setError(getString(R.string.password_not_matched));
            Util.getInstance().cusToast(context, "password not matched");
            return false;


        } else
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

                Toast.makeText(ResetPasswordActivity.this, "Please check your internet connection", Toast.LENGTH_LONG).show();


            }  else if (NetworkConstants.RequestCode.CHANGE_PASSWORD == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {

                    JSONObject jsonObject1=jsonObject.getJSONObject("data");

                    Util.getInstance().cusToast(ResetPasswordActivity.this, jsonObject1.optString("status"));

                    try {
                        userSession.logoutUser();

                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_LOGIN_SCREEN);
                        finishAffinity();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {

                    String message = "";
                    JSONObject jsonObject1=jsonObject.getJSONObject("error");
                    JSONArray jsonArray=jsonObject1.getJSONArray("errors");
                    for (int i = 0; i <jsonArray.length() ; i++) {
                        JSONObject jsonObject2=jsonArray.getJSONObject(i);
                        message=jsonObject2.getString("message");

                    }

                    Util.getInstance().cusToast(ResetPasswordActivity.this, message);
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
