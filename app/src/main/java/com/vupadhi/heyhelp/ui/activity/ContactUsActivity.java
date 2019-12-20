package com.vupadhi.heyhelp.ui.activity;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.base.BaseAbstractActivity;
import com.vupadhi.heyhelp.mvp.contract.activity.ContactusContract;
import com.vupadhi.heyhelp.mvp.presenter.activity.ContactUsImpl;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.sharepref.UserSession;
import com.vupadhi.heyhelp.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ContactUsActivity extends BaseAbstractActivity<ContactUsImpl> implements ContactusContract.IView, APIResponseCallback {
    private ImageView back_arrow;
    TextView contactus_tv;
    LinearLayout call_ll, email_ll;

    APIResponseCallback apiResponseCallback;
    UserSession userSession;
    String nClientid,contactustext, contactnum, email;

    int REQUEST_PHONE_CALL=100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();


    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_contact_us, null);
        return view;
    }

    @Override
    public void setPresenter() {

        presenter = new ContactUsImpl(this, this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        apiResponseCallback = this;
        userSession = new UserSession(ContactUsActivity.this);
        nClientid = userSession.getUserDetails().get(UserSession.KEY_nCLIENTID);


        back_arrow = findViewById(R.id.back_arrow);
        contactus_tv = findViewById(R.id.contactus_tv);
        call_ll = findViewById(R.id.call_ll);
        email_ll = findViewById(R.id.email_ll);

        call_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (ContextCompat.checkSelfPermission(ContactUsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ContactUsActivity.this, new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
                }
                else
                {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" +contactnum));
                    context.startActivity(intent);
                }


            }
        });

        email_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject of email");
                intent.putExtra(Intent.EXTRA_TEXT, "Body of email");
                intent.setData(Uri.parse(email)); // or just "mailto:" for blank
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                startActivity(intent);*/

                final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{email});
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Hello There");
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Add Message here");


                emailIntent.setType("message/rfc822");

                try {
                    startActivity(Intent.createChooser(emailIntent,
                            "Send email using..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(ContactUsActivity.this,
                            "No email clients installed.",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Map<String, String> requestBody = new HashMap<>();
        presenter.contact_us(ContactUsActivity.this, apiResponseCallback, requestBody, nClientid);


    }


    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {

                Toast.makeText(ContactUsActivity.this, "Please check your internet connection", Toast.LENGTH_LONG).show();


            } else if (NetworkConstants.RequestCode.CONTACT_US == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {

                    JSONObject jsonObject1 = jsonObject.getJSONObject("data");

                    JSONArray jsonArray = jsonObject1.getJSONArray("contactUsReqVMList");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject2 = jsonArray.getJSONObject(i);

                        contactustext = jsonObject2.getString("sContactUs");
                        email = jsonObject2.getString("sEmail");
                        contactnum = jsonObject2.getString("sContactNo");


                    }

                    contactus_tv.setText(contactustext);



                } else {

                    Util.getInstance().cusToast(ContactUsActivity.this, jsonObject.optString("message"));
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
