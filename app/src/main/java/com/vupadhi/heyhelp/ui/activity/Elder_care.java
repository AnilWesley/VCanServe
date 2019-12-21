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

import com.vupadhi.heyhelp.adapter.ElderCareRecyclerAdapter;
import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.base.BaseAbstractActivity;
import com.vupadhi.heyhelp.models.ElderCareModel;
import com.vupadhi.heyhelp.mvp.contract.activity.ElderCareContract;
import com.vupadhi.heyhelp.mvp.presenter.activity.ElderCareImpl;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.utils.Util;
import com.vupadhi.heyhelp.utils.Variables;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Elder_care extends BaseAbstractActivity<ElderCareImpl> implements ElderCareContract.IView, APIResponseCallback {
    ImageView imageView;
    LinearLayout linearLayout;
    LinearLayout elder_lay_out_male, elder_lay_out_female;
    TextView male_text, female_text;
    RecyclerView eldercarelist_rv;
    ElderCareRecyclerAdapter adapter;
    int genderposition = -1;
    EditText others_et, elder_age_et;

    private ArrayList<ElderCareModel.DataBean.SubservicelistBean> selectedList = new ArrayList<>();
    JSONArray selectedlistarray = new JSONArray();

    public static JSONArray listarray = new JSONArray();

    StringBuilder stringBuilder, stringBuilder1;
    String titles, ids;

    JSONArray genderjsonArray = new JSONArray();

    APIResponseCallback apiResponseCallback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();


    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_elder_care, null);
        return view;
    }

    @Override
    public void setPresenter() {

        presenter = new ElderCareImpl(this, this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        apiResponseCallback = this;

        ElderCareModel.DataBean dataBean = Variables.eldercarearrayList;

        Variables.ServiceID = dataBean.getCommonlist().getNServiceID();


        imageView = findViewById(R.id.elder_back_but);
        linearLayout = findViewById(R.id.elder_submit_lay_out);
        elder_lay_out_male = findViewById(R.id.elder_lay_out_male);
        elder_lay_out_female = findViewById(R.id.elder_lay_out_female);

        male_text = findViewById(R.id.male_text);
        female_text = findViewById(R.id.female_text);
        others_et = findViewById(R.id.others_et);
        elder_age_et = findViewById(R.id.elder_age_et);

//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.showSoftInput(elder_age_et, InputMethodManager.SHOW_IMPLICIT);

        eldercarelist_rv = findViewById(R.id.eldercarelist_rv);

        if (dataBean.getSubservicelist().get(1).getTypeList() != null || !dataBean.getSubservicelist().get(1).getTypeList().isEmpty()) {
            male_text.setText(dataBean.getSubservicelist().get(1).getTypeList().get(0).getSName());
            female_text.setText(dataBean.getSubservicelist().get(1).getTypeList().get(1).getSName());
        }

        elder_lay_out_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genderposition = 1;

                elder_lay_out_female.setBackgroundResource(R.drawable.selected_blue_line_shape);
                elder_lay_out_male.setBackgroundResource(R.drawable.selected_white);
            }
        });

        elder_lay_out_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genderposition = 0;

                elder_lay_out_female.setBackgroundResource(R.drawable.selected_white);
                elder_lay_out_male.setBackgroundResource(R.drawable.selected_blue_line_shape);
            }
        });

        try {
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(Elder_care.this);
            //setting horizontal list
            mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            eldercarelist_rv.setLayoutManager(mLayoutManager);
            eldercarelist_rv.setItemAnimator(new DefaultItemAnimator());


            //Adding Adapter to recyclerView
            adapter = new ElderCareRecyclerAdapter(dataBean.getSubservicelist(), Elder_care.this);
            eldercarelist_rv.setAdapter(adapter);


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

                    genderjsonArray = new JSONArray();
                    selectedlistarray = new JSONArray();


//            for (int i = 0; i < domesticHelpMaidModel.getData().getCommonlanguagelist().size(); i++) {
                    //Map<String, String> json = new HashMap<>();
                    JSONObject json2 = new JSONObject();
                    json2.put("sName", dataBean.getSubservicelist().get(1).getTypeList().get(genderposition).getSName());
                    json2.put("nID", dataBean.getSubservicelist().get(1).getTypeList().get(genderposition).getNID());

                    genderjsonArray.put(json2);


                    //Map<String, String> json = new HashMap<>();
                    JSONObject json = new JSONObject();
                    json.put("nSubServiceID", dataBean.getSubservicelist().get(0).getNSubServiceID());
//                            json.put("nServiceID", selectedList.get(i).getNServiceID());
                    json.put("sSubServiceName", dataBean.getSubservicelist().get(0).getSSubServiceName());
                    json.put("sOthers", elder_age_et.getText().toString());

                    selectedlistarray.put(0, json);

                    JSONObject jsonn = new JSONObject();
                    jsonn.put("nSubServiceID", dataBean.getSubservicelist().get(1).getNSubServiceID());
//                            json.put("nServiceID", selectedList.get(i).getNServiceID());
                    jsonn.put("sSubServiceName", dataBean.getSubservicelist().get(1).getSSubServiceName());
                    jsonn.put("bMandatory", dataBean.getSubservicelist().get(1).getBMandatory());
                    jsonn.put("bselection", "1");
                    jsonn.put("typeList", genderjsonArray);

                    selectedlistarray.put(1, jsonn);

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

                        if (genderposition == -1) {

                            Toast.makeText(activity, "Please select gender", Toast.LENGTH_SHORT).show();

                        } else if (elder_age_et.getText().toString().length() == 0) {
                            Toast.makeText(activity, "Please enter elder age", Toast.LENGTH_SHORT).show();
                        } else {
                            JSONObject requestBody = new JSONObject();
                            requestBody.put("nOrderID", dataBean.getCommonlist().getNOrderID());
                            requestBody.put("nServiceID", dataBean.getCommonlist().getNServiceID());
                            requestBody.put("subservicelist", selectedlistarray);
                            requestBody.put("nClientID", dataBean.getNClientID());
                            presenter.savedomesticservice_help(Elder_care.this, apiResponseCallback, requestBody);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                } else {

                    Toast.makeText(activity, "Please select any service", Toast.LENGTH_SHORT).show();
                }


//                Intent intent = new Intent(Elder_care.this, Placing_order_okay.class);
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

                Toast.makeText(Elder_care.this, "Please check your internet connection", Toast.LENGTH_LONG).show();


            } else if (NetworkConstants.RequestCode.SAVEDOMESTICSERVICE_HELP == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {

                    JSONObject jsonObject1 = jsonObject.getJSONObject("data");

                    Util.getInstance().cusToast(Elder_care.this, jsonObject1.optString("servicemessage"));


//                    Intent intent = new Intent(Help_maid_screen.this, Placing_order_okay.class);
//                    startActivity(intent);
//                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_PLACING_ORDER_OKAY_SCREEN);
                    Intent intent = new Intent(Elder_care.this, Profile_found_screen.class);
                    startActivity(intent);

                } else {

                    //                  Util.getInstance().cusToast(Elder_care.this, jsonObject.optString("message"));
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
