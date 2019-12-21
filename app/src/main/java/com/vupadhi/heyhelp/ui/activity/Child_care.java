package com.vupadhi.heyhelp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
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

import com.vupadhi.heyhelp.adapter.ChildCareRecyclerAdapter;
import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.base.BaseAbstractActivity;
import com.vupadhi.heyhelp.dao.AdapterCallback;
import com.vupadhi.heyhelp.models.ChildCareScreenModel;
import com.vupadhi.heyhelp.mvp.contract.activity.ChildCareContract;
import com.vupadhi.heyhelp.mvp.presenter.activity.ChildCareImpl;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.utils.Util;
import com.vupadhi.heyhelp.utils.Variables;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Child_care extends BaseAbstractActivity<ChildCareImpl> implements ChildCareContract.IView, APIResponseCallback {
    ImageView imageView;
    LinearLayout linearLayout_boy, linearLayout_girl;
    LinearLayout child_care_submit_lay_out;

    TextView boy_text, girl_text, others_text, tv_spinnertext;
    EditText others_et;
    Spinner spinner_age;
    String agetext, ageid = "";
    int genderposition = -1;

    RelativeLayout relativelayoutt1;

    ImageView dropimg;

    RecyclerView babylist_rv;

    ChildCareRecyclerAdapter Childcareadapter;
    APIResponseCallback apiResponseCallback;

    ArrayList<String> spinnerlist, spinnerlistids;

    ArrayList<String> subserviceslist;

    JSONArray selectedlistarray = new JSONArray();

    private ArrayList<ChildCareScreenModel.DataBean.SubservicelistBean> selectedList = new ArrayList<>();


    public static JSONArray listarray = new JSONArray();

    StringBuilder stringBuilder, stringBuilder1;
    String titles, ids;

    JSONArray agetypelistjsonArray = new JSONArray();
    JSONArray genderjsonArray = new JSONArray();

    AdapterCallback adapterCallback;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();


    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.child_care, null);
        return view;
    }

    @Override
    public void setPresenter() {

        presenter = new ChildCareImpl(this, this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        apiResponseCallback = this;
        spinnerlist = new ArrayList<>();
        spinnerlistids = new ArrayList<>();
        subserviceslist = new ArrayList<>();

        ChildCareScreenModel.DataBean dataBean = Variables.childcarearrayList;

        Variables.ServiceID = dataBean.getCommonlist().getNServiceID();

        imageView = findViewById(R.id.child_back_but);
        child_care_submit_lay_out = findViewById(R.id.child_care_submit_lay_out);
        linearLayout_boy = findViewById(R.id.boy_lay_out);
        linearLayout_girl = findViewById(R.id.girl_lay_out);

        boy_text = findViewById(R.id.boy_text);
        girl_text = findViewById(R.id.girl_text);
        others_text = findViewById(R.id.others_text);
        others_et = findViewById(R.id.others_et);
        spinner_age = findViewById(R.id.spinner_age);
        babylist_rv = findViewById(R.id.babylist_rv);
        tv_spinnertext = findViewById(R.id.tv_spinnertext);
        relativelayoutt1 = findViewById(R.id.relativelayoutt1);
        dropimg = findViewById(R.id.dropimg);

        tv_spinnertext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner_age.performClick();
            }
        });
        relativelayoutt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner_age.performClick();

            }
        });
        dropimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinner_age.performClick();

            }
        });

        boy_text.setText(dataBean.getSubservicelist().get(1).getTypeList().get(0).getSName());
        girl_text.setText(dataBean.getSubservicelist().get(1).getTypeList().get(1).getSName());



        linearLayout_girl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genderposition = 1;

                linearLayout_girl.setBackgroundResource(R.drawable.selected_blue_line_shape);
                linearLayout_boy.setBackgroundResource(R.drawable.selected_white);
            }
        });
        linearLayout_boy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genderposition = 0;

                linearLayout_girl.setBackgroundResource(R.drawable.selected_white);
                linearLayout_boy.setBackgroundResource(R.drawable.selected_blue_line_shape);
            }
        });


        try {
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(Child_care.this);
            //setting horizontal list
            mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            babylist_rv.setLayoutManager(mLayoutManager);
            babylist_rv.setItemAnimator(new DefaultItemAnimator());


            //Adding Adapter to recyclerView
            Childcareadapter = new ChildCareRecyclerAdapter(dataBean.getSubservicelist(), Child_care.this, adapterCallback);
            babylist_rv.setAdapter(Childcareadapter);


        } catch (Exception e) {
            e.printStackTrace();
        }


        spinnerlist.clear();
        spinnerlistids.clear();
        spinnerlist.add("Select");
        spinnerlistids.add("");


        for (int i = 0; i < dataBean.getSubservicelist().get(0).getTypeList().size(); i++) {

            String id = dataBean.getSubservicelist().get(0).getTypeList().get(i).getNID();
            String name = dataBean.getSubservicelist().get(0).getTypeList().get(i).getSName();


            spinnerlist.add(name);
            spinnerlistids.add(id);

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, spinnerlist);

        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner_age.setAdapter(adapter);


        spinner_age.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if (position > 0) {

                    agetext = spinnerlist.get(position);
                    ageid = spinnerlistids.get(position);

                    tv_spinnertext.setText(agetext);

                    Toast.makeText(context, "" + spinnerlist.get(position), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        child_care_submit_lay_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    agetypelistjsonArray = new JSONArray();
                    genderjsonArray = new JSONArray();
                    selectedlistarray = new JSONArray();

                    JSONObject json1 = new JSONObject();
                    json1.put("sName", agetext);
                    json1.put("nID", ageid);

                    agetypelistjsonArray.put(json1);
//            }

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
                    json.put("bMandatory", dataBean.getSubservicelist().get(0).getBMandatory());
                    json.put("bselection", "1");
                    json.put("typeList", agetypelistjsonArray);

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

                selectedList = Childcareadapter.getselectedList();

                Log.d("selectedlist..", selectedList.toString());
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
                        } else if (ageid.isEmpty() || ageid.equalsIgnoreCase("")) {
                            Toast.makeText(activity, "Please select age", Toast.LENGTH_SHORT).show();
                        } else {

                            JSONObject requestBody = new JSONObject();
                            requestBody.put("nOrderID", dataBean.getCommonlist().getNOrderID());
                            requestBody.put("nServiceID", dataBean.getCommonlist().getNServiceID());
                            requestBody.put("subservicelist", selectedlistarray);
                            requestBody.put("nClientID", dataBean.getNClientID());
                            presenter.savedomesticservice_help(Child_care.this, apiResponseCallback, requestBody);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                } else {

                    Toast.makeText(activity, "Please select any service", Toast.LENGTH_SHORT).show();
                }

               /* Intent intent = new Intent(Child_care.this, Placing_order_okay.class);
                startActivity(intent);*/
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

                Toast.makeText(Child_care.this, "Please check your internet connection", Toast.LENGTH_LONG).show();


            } else if (NetworkConstants.RequestCode.SAVEDOMESTICSERVICE_HELP == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {

                    JSONObject jsonObject1 = jsonObject.getJSONObject("data");

                    Util.getInstance().cusToast(Child_care.this, jsonObject1.optString("servicemessage"));


//                    Intent intent = new Intent(Help_maid_screen.this, Placing_order_okay.class);
//                    startActivity(intent);
//                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_PLACING_ORDER_OKAY_SCREEN);
                    Intent intent = new Intent(Child_care.this, Profile_found_screen.class);
                    startActivity(intent);

                } else {

//                    Util.getInstance().cusToast(Child_care.this, jsonObject.optString("message"));
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
