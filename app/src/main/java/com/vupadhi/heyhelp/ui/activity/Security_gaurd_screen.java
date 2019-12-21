package com.vupadhi.heyhelp.ui.activity;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vupadhi.heyhelp.Adapter.SecurityGuardRecyclerAdapter;
import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.base.BaseAbstractActivity;
import com.vupadhi.heyhelp.models.SecurityGuardScreenModel;
import com.vupadhi.heyhelp.mvp.contract.activity.SecurityGuardScreenContract;
import com.vupadhi.heyhelp.mvp.presenter.activity.SecurityGuardScreenImpl;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.utils.Util;
import com.vupadhi.heyhelp.utils.Variables;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Security_gaurd_screen extends BaseAbstractActivity<SecurityGuardScreenImpl> implements SecurityGuardScreenContract.IView, APIResponseCallback {
    LinearLayout linearLayout, day_ll, night_ll;
    ImageView imageView;
    TextView day_text, night_text;
    RecyclerView security_rv;
    EditText others_et;
    int shiftposition = -1;


    APIResponseCallback apiResponseCallback;

    private ArrayList<SecurityGuardScreenModel.DataBean.SubservicelistBean> selectedList = new ArrayList<>();
    JSONArray selectedlistarray = new JSONArray();

    public static JSONArray listarray = new JSONArray();

    StringBuilder stringBuilder, stringBuilder1;
    String titles, ids;

    JSONArray shiftjsonArray = new JSONArray();

    SecurityGuardRecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();


    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_security_gaurd_screen, null);
        return view;
    }

    @Override
    public void setPresenter() {

        presenter = new SecurityGuardScreenImpl(this, this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        apiResponseCallback = this;

        SecurityGuardScreenModel.DataBean dataBean = Variables.securityarrayList;

        Variables.ServiceID=dataBean.getCommonlist().getNServiceID();


        linearLayout = findViewById(R.id.security_submit_lay_out);
        imageView = findViewById(R.id.security_back_but);

        day_text = findViewById(R.id.day_text);
        night_text = findViewById(R.id.night_text);
        security_rv = findViewById(R.id.security_rv);
        others_et = findViewById(R.id.others_et);
        day_ll = findViewById(R.id.day_ll);
        night_ll = findViewById(R.id.night_ll);

        day_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shiftposition = 0;
                day_ll.setBackgroundResource(R.drawable.selected_blue_line_shape);
                night_ll.setBackgroundResource(R.drawable.selected_white);
            }
        });
        night_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shiftposition = 1;
                night_ll.setBackgroundResource(R.drawable.selected_blue_line_shape);
                day_ll.setBackgroundResource(R.drawable.selected_white);
            }
        });

        day_text.setText(dataBean.getSubservicelist().get(0).getTypeList().get(0).getSName());
        night_text.setText(dataBean.getSubservicelist().get(0).getTypeList().get(1).getSName());


        try {
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(Security_gaurd_screen.this);
            //setting horizontal list
            mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            security_rv.setLayoutManager(mLayoutManager);
            security_rv.setItemAnimator(new DefaultItemAnimator());


            //Adding Adapter to recyclerView
            adapter = new SecurityGuardRecyclerAdapter(dataBean.getSubservicelist(), Security_gaurd_screen.this);
            security_rv.setAdapter(adapter);


        } catch (Exception e) {
            e.printStackTrace();
        }


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    shiftjsonArray = new JSONArray();
                    selectedlistarray = new JSONArray();


//            for (int i = 0; i < domesticHelpMaidModel.getData().getCommonlanguagelist().size(); i++) {
                    //Map<String, String> json = new HashMap<>();
                    JSONObject json2 = new JSONObject();
                    json2.put("sName", dataBean.getSubservicelist().get(0).getTypeList().get(shiftposition).getSName());
                    json2.put("nID", dataBean.getSubservicelist().get(0).getTypeList().get(shiftposition).getNID());

                    shiftjsonArray.put(json2);


                    JSONObject jjsonn1 = new JSONObject();
                    jjsonn1.put("nSubServiceID", dataBean.getSubservicelist().get((dataBean.getSubservicelist().size()) - 1).getNSubServiceID());
//                            json.put("nServiceID", selectedList.get(i).getNServiceID());
                    jjsonn1.put("sSubServiceName", dataBean.getSubservicelist().get((dataBean.getSubservicelist().size()) - 1).getSSubServiceName());
                    jjsonn1.put("sOthers", others_et.getText().toString());


                    selectedlistarray.put(jjsonn1);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                selectedList = adapter.getselectedList();
                listarray = new JSONArray();

                stringBuilder = new StringBuilder();
                stringBuilder1 = new StringBuilder();
                String selectedtitle;
                String selectedid;


                if (selectedList != null && selectedList.size() > 0) {


                    for (int i = 0; i < selectedList.size(); i++) {


                        listarray.put(selectedList.get(i).getNSubServiceID());

                        System.out.println("jsonarray" + listarray);

                        try {


                            JSONObject jjsonn = new JSONObject();
                            jjsonn.put("nSubServiceID", selectedList.get(i).getNSubServiceID());
//                            json.put("nServiceID", selectedList.get(i).getNServiceID());
                            jjsonn.put("sSubServiceName", selectedList.get(i).getSSubServiceName());
                            jjsonn.put("bMandatory", selectedList.get(i).getBMandatory());
                            jjsonn.put("bselection", "1");

                            selectedlistarray.put(jjsonn);


                            Log.d("requestarray", selectedlistarray.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        selectedtitle = selectedList.get(i).getSSubServiceName() + ",";
                        stringBuilder.append(selectedtitle);

                        selectedid = selectedList.get(i).getNSubServiceID() + ",";
                        stringBuilder1.append(selectedid);


                    }

                    titles = stringBuilder.toString();
                    titles = titles.substring(0, titles.length() - 1);


                    ids = stringBuilder1.toString();
                    ids = ids.substring(0, ids.length() - 1);


                    try {

                        if (shiftposition == -1) {

                            Toast.makeText(activity, "Please select shift", Toast.LENGTH_SHORT).show();

                        } else {
                            JSONObject requestBody = new JSONObject();
                            requestBody.put("nOrderID", dataBean.getCommonlist().getNOrderID());
                            requestBody.put("nServiceID", dataBean.getCommonlist().getNServiceID());
                            requestBody.put("subservicelist", selectedlistarray);
                            requestBody.put("nClientID", dataBean.getNClientID());
                            presenter.savedomesticservice_help(Security_gaurd_screen.this, apiResponseCallback, requestBody);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                } else {

                    Toast.makeText(activity, "Please select any service", Toast.LENGTH_SHORT).show();
                }

//                Intent intent = new Intent(Security_gaurd_screen.this, Placing_order_okay.class);
//                startActivity(intent);

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

                Toast.makeText(Security_gaurd_screen.this, "Please check your internet connection", Toast.LENGTH_LONG).show();


            } else if (NetworkConstants.RequestCode.SAVEDOMESTICSERVICE_HELP == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {

                    JSONObject jsonObject1 = jsonObject.getJSONObject("data");

                    Util.getInstance().cusToast(Security_gaurd_screen.this, jsonObject1.optString("servicemessage"));


//                    Intent intent = new Intent(Help_maid_screen.this, Placing_order_okay.class);
//                    startActivity(intent);
//                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_PLACING_ORDER_OKAY_SCREEN);

                    Intent intent=new Intent(Security_gaurd_screen.this, Profile_found_screen.class);
                    startActivity(intent);

                } else {

//                    Util.getInstance().cusToast(Security_gaurd_screen.this, jsonObject.optString("message"));
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
