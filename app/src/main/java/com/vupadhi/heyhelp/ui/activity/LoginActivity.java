package com.vupadhi.heyhelp.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.app.constants.AppConstants;
import com.vupadhi.heyhelp.app.controller.ApplicationController;
import com.vupadhi.heyhelp.base.BaseAbstractActivity;
import com.vupadhi.heyhelp.customfonts.CustomTextViewBold;
import com.vupadhi.heyhelp.customfonts.CustomTextViewSemiBold;
import com.vupadhi.heyhelp.models.LoginModel;
import com.vupadhi.heyhelp.mvp.contract.activity.LoginActivityContract;
import com.vupadhi.heyhelp.mvp.presenter.activity.LoginActivityImpl;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.sharepref.UserSession;
import com.vupadhi.heyhelp.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseAbstractActivity<LoginActivityImpl> implements LoginActivityContract.IView, APIResponseCallback {
    private CustomTextViewSemiBold ctvsb_signup, forgot_password;
    private CustomTextViewBold ctvb_login;
    EditText mobile_et, password_et;
    APIResponseCallback apiResponseCallback;
    UserSession userSession;

    ArrayList<String> homenameslist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();


    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_login, null);
        return view;
    }

    @Override
    public void setPresenter() {

        presenter = new LoginActivityImpl(this, this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        userSession = new UserSession(this);

        apiResponseCallback = this;
        forgot_password = findViewById(R.id.forgot_password);
        ctvb_login = findViewById(R.id.ctvb_login);
        mobile_et = findViewById(R.id.mobile_et);
        password_et = findViewById(R.id.password_et);
        ctvsb_signup = findViewById(R.id.ctvsb_signup);
        ctvsb_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });
        ctvb_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isUserCredentialsValid()) {
                    Map<String, String> requestBody = new HashMap<>();
                    requestBody.put("sMobileNumber", mobile_et.getText().toString());
                    requestBody.put("sPassword", password_et.getText().toString());
                    requestBody.put("appType","C");

                    presenter.login(LoginActivity.this, apiResponseCallback, requestBody);
                }
//                startActivity(new Intent(LoginActivity.this, HomeScreenActivity.class));
            }
        });
        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
            }
        });


    }

    private boolean isUserCredentialsValid() {
        if (mobile_et.getText().toString().length() == 0) {
//            mobilenumtext.setError("Enter mobile number");
            Util.getInstance().cusToast(context, "Enter mobilenumber");

            return false;
        } else if (mobile_et.getText().toString().length() < 10) {
//            mobilenumtext.setError("Enter mobile number");
            Util.getInstance().cusToast(context, "Enter valid mobilenumber");

            return false;
        } else if (password_et.getText().toString().length() == 0) {
            // passwordtext.setError("Enter password");
            Util.getInstance().cusToast(context, "Enter password");

            return false;
        } else if (password_et.getText().length() < 5) {
            Util.getInstance().cusToast(context, "Password must be 6 characters");
            return false;


        }

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

                Toast.makeText(LoginActivity.this, "Please check your internet connection", Toast.LENGTH_LONG).show();


            } else if (NetworkConstants.RequestCode.LOGIN == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {


                    LoginModel loginModel = new Gson().fromJson(responseString, LoginModel.class);
                    //    public void StoreUserDetails(String userid, String name, String mobileno, String email, String password, String center_id, String nationality) {
                    String nClientID = loginModel.getData().getNClientID();
                    String nUserType = loginModel.getData().getNUserType();
                    String name = loginModel.getData().getUsername();
                    String location = loginModel.getData().getLocationName();
//                    String emailId = loginModel.getEmail();
                   String mobile = loginModel.getData().getSMobileNumber();
//                    String profileImg = loginModel.getImage();
//                    String roletype = loginModel.getRoleType();

                    String access_token = loginModel.getData().getAccessToken();

                    Boolean islogin = true;

                /*    JSONArray jsonArray=jsonObject.getJSONArray("lowerMenuBar");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        String name=jsonObject1.getString("name");

                        homenameslist.add(name);
                    }*/

                if (nUserType.equalsIgnoreCase("111"))
                {
                    Toast.makeText(activity, "Please verify otp and login", Toast.LENGTH_SHORT).show();
                    Bundle b=new Bundle();
                    b.putString("tag","login");
                    b.putString("mobile",mobile);
                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_VERIFY_SCREEN,b);
                }else {

                    userSession.StoreUserDetails(nClientID, nUserType,name,location, islogin);
                    userSession.setDeviceToken(access_token);

                    Log.d("token", access_token);

                    Bundle b = new Bundle();
//                    b.putString("homelist",homenameslist.toString());


//                                                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
//                                                    startActivity(i);
                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_HOME_SCREEN, b);
                    Util.getInstance().cusToast(LoginActivity.this, "User logged in successfully");
                }

                } else {

                    String message = "";
                    JSONObject jsonObject1 = jsonObject.getJSONObject("error");
                    JSONArray jsonArray = jsonObject1.getJSONArray("errors");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                        message = jsonObject2.getString("message");

                    }

                    Util.getInstance().cusToast(LoginActivity.this, message);
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
