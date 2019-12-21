package com.vupadhi.heyhelp.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.vupadhi.heyhelp.Adapter.CompanyLocationsRecyclerAdapter;
import com.vupadhi.heyhelp.Adapter.LocationsRecyclerAdapter;
import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.app.constants.AppConstants;
import com.vupadhi.heyhelp.app.controller.ApplicationController;
import com.vupadhi.heyhelp.base.BaseAbstractActivity;
import com.vupadhi.heyhelp.customfonts.CustomTextViewBold;
import com.vupadhi.heyhelp.customfonts.CustomTextViewSemiBold;
import com.vupadhi.heyhelp.models.Company_locModel;
import com.vupadhi.heyhelp.models.Individual_locModel;
import com.vupadhi.heyhelp.models.SignUpModel;
import com.vupadhi.heyhelp.mvp.contract.activity.SignUpActivityContract;
import com.vupadhi.heyhelp.mvp.presenter.activity.SignUpActivityImpl;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class SignupActivity extends BaseAbstractActivity<SignUpActivityImpl> implements SignUpActivityContract.IView, APIResponseCallback {
    private CustomTextViewSemiBold ctvsb_individual, ctvsb_company, ctvsb_login;
    private CustomTextViewBold ctvb_signup;
    private LinearLayout linear_individual, linear_company;
    private ImageView back_arrow;
    APIResponseCallback apiResponseCallback;
    RecyclerView location_rv, clocation_rv;
    EditText name_et, email_et, number_et, password_et, companyname_et, cname_et, cemail_et, cnumber_et, cpassword_et;
    int type = 1;
    String status = "individual";
    String loc_text = "", cloc_text = "";
    LocationsRecyclerAdapter adapter;
    CompanyLocationsRecyclerAdapter cadapter;
    Individual_locModel individual_locModel;
    int count;
    String locid = "";
    String clocid = "";
    Spinner spinner, cspinner;

    RelativeLayout relativelayoutt1, relativelayoutt2;
    TextView tv_spinnertext, tv_spinnertext1;
    ImageView dropimg, dropimg1;

    ArrayList<String> loclist, locids, cloclist, clocids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();

    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_signup, null);
        return view;
    }

    @Override
    public void setPresenter() {

        presenter = new SignUpActivityImpl(this, this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        apiResponseCallback = this;

        loclist = new ArrayList<>();
        locids = new ArrayList<>();
        cloclist = new ArrayList<>();
        clocids = new ArrayList<>();

        ctvb_signup = findViewById(R.id.ctvb_signup);
        ctvsb_individual = findViewById(R.id.ctvsb_individual);
        ctvsb_company = findViewById(R.id.ctvsb_company);
        linear_individual = findViewById(R.id.linear_individual);
        linear_company = findViewById(R.id.linear_company);
        ctvsb_login = findViewById(R.id.ctvsb_login);
        back_arrow = findViewById(R.id.back_arrow);
        name_et = findViewById(R.id.name_et);
        email_et = findViewById(R.id.email_et);
        number_et = findViewById(R.id.number_et);
        password_et = findViewById(R.id.password_et);
        companyname_et = findViewById(R.id.companyname_et);
        cname_et = findViewById(R.id.cname_et);
        cemail_et = findViewById(R.id.cemail_et);
        cnumber_et = findViewById(R.id.cnumber_et);
        cpassword_et = findViewById(R.id.cpassword_et);
        spinner = findViewById(R.id.spinner);
        cspinner = findViewById(R.id.cspinner);
        relativelayoutt1 = findViewById(R.id.relativelayoutt1);
        tv_spinnertext = findViewById(R.id.tv_spinnertext);
        dropimg = findViewById(R.id.dropimg);

        relativelayoutt2 = findViewById(R.id.relativelayoutt2);
        tv_spinnertext1 = findViewById(R.id.tv_spinnertext1);
        dropimg1 = findViewById(R.id.dropimg1);


        relativelayoutt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinner.performClick();
            }
        });
        tv_spinnertext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinner.performClick();
            }
        });
        dropimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinner.performClick();
            }
        });
        relativelayoutt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cspinner.performClick();
            }
        });
        tv_spinnertext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cspinner.performClick();
            }
        });
        dropimg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cspinner.performClick();
            }
        });

        ctvb_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (type == 1) {
                    if (isUserCredentialsValid()) {
                        Map<String, String> requestBody = new HashMap<>();
                        requestBody.put("nUserType", "100");
                        requestBody.put("sName", name_et.getText().toString());
                        requestBody.put("sEmail", email_et.getText().toString());
                        requestBody.put("sMobileNumber", number_et.getText().toString());
                        requestBody.put("sPassword", password_et.getText().toString());
                        requestBody.put("sLocationID", locid);
                        presenter.user_register(SignupActivity.this, apiResponseCallback, requestBody);
                    }
                } else if (type == 2) {
                    if (isUserCredentialsValid1()) {
                        Map<String, String> requestBody = new HashMap<>();
                        requestBody.put("nUserType", "101");
                        requestBody.put("sName", companyname_et.getText().toString());
                        requestBody.put("sContactPerson", cname_et.getText().toString());
                        requestBody.put("sEmail", cemail_et.getText().toString());
                        requestBody.put("sMobileNumber", cnumber_et.getText().toString());
                        requestBody.put("sPassword", cpassword_et.getText().toString());
                        requestBody.put("sLocationID", clocid);
                        presenter.user_register(SignupActivity.this, apiResponseCallback, requestBody);
                    }
                }


              /*  Bundle bundle = new Bundle();
                bundle.putString("tag", "signup");
                Intent intent = new Intent(getApplicationContext(), OtpActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);*/
            }
        });

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        ctvsb_individual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                type = 1;
                status = "individual";


                cname_et.setText("");
                cemail_et.setText("");
                cnumber_et.setText("");
                cpassword_et.setText("");

                ctvsb_company.setTextColor(getResources().getColor(R.color.new_color));
                ctvsb_individual.setTextColor(getResources().getColor(R.color.text_white));
                linear_individual.setVisibility(View.VISIBLE);
                linear_company.setVisibility(View.GONE);
            }
        });

        ctvsb_company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                type = 2;
                status = "company";

                name_et.setText("");
                email_et.setText("");
                number_et.setText("");
                password_et.setText("");
                companyname_et.setText("");

                Map<String, String> requestBody = new HashMap<>();
                presenter.user_company_locations(SignupActivity.this, apiResponseCallback, requestBody);

                ctvsb_company.setTextColor(getResources().getColor(R.color.text_white));
                ctvsb_individual.setTextColor(getResources().getColor(R.color.new_color));
                linear_individual.setVisibility(View.GONE);
                linear_company.setVisibility(View.VISIBLE);
            }
        });

        if (type == 1) {

            Map<String, String> requestBody = new HashMap<>();
            presenter.user_individual_locations(SignupActivity.this, apiResponseCallback, requestBody);
        } else if (type == 2) {
            Map<String, String> requestBody = new HashMap<>();
            presenter.user_company_locations(SignupActivity.this, apiResponseCallback, requestBody);
        }

        ctvsb_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
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

        String email = email_et.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (name_et.getText().toString().length() == 0) {
            //  etRegFirstName.setError( getString(R.string.enter_name));
            Util.getInstance().cusToast(context, "Please enter Name");

            //todo: Add shake animation
            return false;

        } else if (email_et.getText().toString().length() == 0) {
            //  etMobile.setError(getString(R.string.enter_mobile_number));
            Util.getInstance().cusToast(context, "Please enter Email Id");
            return false;


        } else if (!isValidEmaillId(email)) {
            // etEmailId.setError("Enter Mail Id");
            Util.getInstance().cusToast(context, "Enter Valid Email Id");

            return false;

        } else if (email.contains(".com.com")) {
            // etEmailId.setError("Enter Mail Id");
            Util.getInstance().cusToast(context, "Enter Valid Email Id");

            return false;

        } else if (number_et.getText().toString().length() == 0) {
            //  etMobile.setError(getString(R.string.enter_mobile_number));
            Util.getInstance().cusToast(context, "Please enter mobile number");
            return false;


        } else if (number_et.getText().toString().length() < 10) {
            //  etMobile.setError(getString(R.string.enter_mobile_number));
            Util.getInstance().cusToast(context, "Please enter valid mobile number");
            return false;


        }/*else if (deviceid_et.getText().toString().length() == 0) {
            //  etMobile.setError(getString(R.string.enter_mobile_number));
            Util.getInstance().cusToast(context, "Please enter your device id");
            return false;


        }*/ else if (password_et.getText().toString().length() == 0) {
            Util.getInstance().cusToast(context, "Please enter password");

            //  etPassword.setError(getString(R.string.enter_password));
            return false;
        } else if (password_et.getText().toString().length() < 6) {
            Util.getInstance().cusToast(context, "password should be minimum 6 letters");
            return false;
        } else if (loc_text.equalsIgnoreCase("") || loc_text.isEmpty()) {
            Util.getInstance().cusToast(context, "please select location");
            return false;
        } else {
            return true;
//            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_HOME_SCREEN);
            // Toast.makeText(getApplicationContext(), "user not registered with us!", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isUserCredentialsValid1() {

        String email = cemail_et.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (companyname_et.getText().toString().length() == 0) {
            //  etRegFirstName.setError( getString(R.string.enter_name));
            Util.getInstance().cusToast(context, "Please enter Company Name");

            //todo: Add shake animation
            return false;

        } else if (cname_et.getText().toString().length() == 0) {
            //  etMobile.setError(getString(R.string.enter_mobile_number));
            Util.getInstance().cusToast(context, "Please enter Name");
            return false;


        } else if (cemail_et.getText().toString().length() == 0) {
            //  etMobile.setError(getString(R.string.enter_mobile_number));
            Util.getInstance().cusToast(context, "Please enter Email Id");
            return false;


        } else if (!isValidEmaillId(email)) {
            // etEmailId.setError("Enter Mail Id");
            Util.getInstance().cusToast(context, "Enter Valid Email Id");

            return false;

        } else if (email.contains(".com.com")) {
            // etEmailId.setError("Enter Mail Id");
            Util.getInstance().cusToast(context, "Enter Valid Email Id");

            return false;

        } else if (cnumber_et.getText().toString().length() == 0) {
            //  etMobile.setError(getString(R.string.enter_mobile_number));
            Util.getInstance().cusToast(context, "Please enter mobile number");
            return false;


        } else if (cnumber_et.getText().toString().length() < 10) {
            //  etMobile.setError(getString(R.string.enter_mobile_number));
            Util.getInstance().cusToast(context, "Please enter valid mobile number");
            return false;


        }/*else if (deviceid_et.getText().toString().length() == 0) {
            //  etMobile.setError(getString(R.string.enter_mobile_number));
            Util.getInstance().cusToast(context, "Please enter your device id");
            return false;


        }*/ else if (cpassword_et.getText().toString().length() == 0) {
            Util.getInstance().cusToast(context, "Please enter password");

            //  etPassword.setError(getString(R.string.enter_password));
            return false;
        } else if (cpassword_et.getText().toString().length() < 6) {
            Util.getInstance().cusToast(context, "password should be minimum 6 characters");
            return false;
        } else if (cloc_text.equalsIgnoreCase("") || cloc_text.isEmpty()) {
            Util.getInstance().cusToast(context, "please select location");
            return false;
        } else {
            return true;
//            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_HOME_SCREEN);
            // Toast.makeText(getApplicationContext(), "user not registered with us!", Toast.LENGTH_LONG).show();
        }
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

                Toast.makeText(SignupActivity.this, "Check your internet connection", Toast.LENGTH_LONG).show();


            } else if (NetworkConstants.RequestCode.USER_INDIVIDUAL_LOCATIONS == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {
                    //  Util.getInstance().cusToast(SignupActivity.this, jsonObject.optString("message"));

                    individual_locModel = new Gson().fromJson(responseString, Individual_locModel.class);

                    loclist.add("Select location");
                    locids.add("");

                    for (int i = 0; i < individual_locModel.getData().getIndividualSelection().getSLocation().size(); i++) {

                        loclist.add(individual_locModel.getData().getIndividualSelection().getSLocation().get(i).getSLocationName());
                        locids.add(String.valueOf(individual_locModel.getData().getIndividualSelection().getSLocation().get(i).getSLocationID()));
                    }


                    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, loclist);

                    adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
                    spinner.setAdapter(adapter);

//                    CustomAdapter adapter = new CustomAdapter(this, loclist);
//
//                    spinner.setAdapter(adapter);


                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                            locid = locids.get(position);
                            loc_text = loclist.get(position);

                            tv_spinnertext.setText(loc_text);

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                } else {
                    Util.getInstance().cusToast(SignupActivity.this, "No locations found");
                }
            } else if (NetworkConstants.RequestCode.USER_COMPANY_LOCATIONS == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {
                    //  Util.getInstance().cusToast(SignupActivity.this, jsonObject.optString("message"));

                    Company_locModel company_locModel = new Gson().fromJson(responseString, Company_locModel.class);


                    cloclist.add("Select location");
                    clocids.add("");
                    for (int i = 0; i < company_locModel.getData().getCompanySelection().getSLocation().size(); i++) {

                        cloclist.add(company_locModel.getData().getCompanySelection().getSLocation().get(i).getSLocationName());
                        clocids.add(String.valueOf(company_locModel.getData().getCompanySelection().getSLocation().get(i).getSLocationID()));
                    }


                    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, loclist);

                    adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
                    cspinner.setAdapter(adapter);


                    cspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            clocid = locids.get(position);
                            cloc_text = loclist.get(position);

                            tv_spinnertext1.setText(cloc_text);

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                } else {
                    Util.getInstance().cusToast(SignupActivity.this, jsonObject.optString("message"));
                }
            } else if (NetworkConstants.RequestCode.USER_REGISTER == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {
                    //Util.getInstance().cusToast(VerificationActivity.this, jsonObject.optString("message"));

                    SignUpModel signUpModel = new Gson().fromJson(responseString, SignUpModel.class);

                    Toast.makeText(activity, "otp "+signUpModel.getData().getCustomer().getSOTPValue(), Toast.LENGTH_SHORT).show();

                    String nClientid = String.valueOf(signUpModel.getData().getCustomer().getNClientID());

                    Bundle b = new Bundle();
                    b.putString("tag", "signup");
                    b.putString("nClientid", nClientid);
                    b.putString("mobile", number_et.getText().toString());
                    b.putString("mobile1", cnumber_et.getText().toString());
                    b.putString("status", status);

                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_VERIFY_SCREEN, b);


                    // Util.getInstance().cusToast(VerificationActivity.this, reotpresponse);
                } else {

                    String message = "";
                    JSONObject jsonObject1 = jsonObject.getJSONObject("error");
                    JSONArray jsonArray = jsonObject1.getJSONArray("errors");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                        message = jsonObject2.getString("message");

                    }

                    Util.getInstance().cusToast(SignupActivity.this, message);
                }
            } else {
                //              Util.getInstance().cusToast(SignupActivity.this, jsonObject.optString("message"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }
}
