package com.vupadhi.heyhelp.ui.activity;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.base.BaseAbstractActivity;
import com.vupadhi.heyhelp.models.CookScreenModel;
import com.vupadhi.heyhelp.mvp.contract.activity.CookScreenContract;
import com.vupadhi.heyhelp.mvp.presenter.activity.CookScreenImpl;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.utils.Util;
import com.vupadhi.heyhelp.utils.Variables;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Cook_screen extends BaseAbstractActivity<CookScreenImpl> implements CookScreenContract.IView, APIResponseCallback {
    ImageView imageView;
    LinearLayout linearLayout, veg_ll, nonveg_ll, northinadian_ll, southindian_ll;
    APIResponseCallback apiResponseCallback;
    TextView veg_text, nonveg_text, northindian_text, southindian_text;
    int foodposition=-1, religionposition=-1;

    EditText exp_et,cusines_et;

    JSONArray selectedlistarray = new JSONArray();



    JSONArray foodtypejsonArray = new JSONArray();
    JSONArray religiontypejsonArray = new JSONArray();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();


    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_cook_screen, null);
        return view;
    }

    @Override
    public void setPresenter() {

        presenter = new CookScreenImpl(this, this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        apiResponseCallback = this;

        CookScreenModel.DataBean dataBean = Variables.cookarrayList;

        Variables.ServiceID=dataBean.getCommonlist().getNServiceID();


        imageView = findViewById(R.id.cook_back_but);

        veg_text = findViewById(R.id.veg_text);
        nonveg_text = findViewById(R.id.nonveg_text);
        northindian_text = findViewById(R.id.northindian_text);
        southindian_text = findViewById(R.id.southindian_text);
        veg_ll = findViewById(R.id.veg_ll);
        nonveg_ll = findViewById(R.id.nonveg_ll);
        northinadian_ll = findViewById(R.id.northinadian_ll);
        southindian_ll = findViewById(R.id.southindian_ll);

        exp_et = findViewById(R.id.exp_et);
        cusines_et = findViewById(R.id.cusines_et);

        linearLayout = findViewById(R.id.cook_submit_lay_out);

        veg_text.setText(dataBean.getSubservicelist().get(0).getTypeList().get(0).getSName());
        nonveg_text.setText(dataBean.getSubservicelist().get(0).getTypeList().get(1).getSName());

        northindian_text.setText(dataBean.getSubservicelist().get(1).getTypeList().get(0).getSName());
        southindian_text.setText(dataBean.getSubservicelist().get(1).getTypeList().get(1).getSName());

        veg_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                foodposition = 0;
                veg_ll.setBackgroundResource(R.drawable.selected_blue_line_shape);
                nonveg_ll.setBackgroundResource(R.drawable.selected_white);
            }
        });
        nonveg_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                foodposition = 1;
                nonveg_ll.setBackgroundResource(R.drawable.selected_blue_line_shape);
                veg_ll.setBackgroundResource(R.drawable.selected_white);
            }
        });

        northinadian_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                religionposition = 0;

                northinadian_ll.setBackgroundResource(R.drawable.selected_blue_line_shape);
                southindian_ll.setBackgroundResource(R.drawable.selected_white);
            }
        });
        southindian_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                religionposition = 1;

                southindian_ll.setBackgroundResource(R.drawable.selected_blue_line_shape);
                northinadian_ll.setBackgroundResource(R.drawable.selected_white);
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

                try {

                    foodtypejsonArray=new JSONArray();
                    religiontypejsonArray=new JSONArray();
                    selectedlistarray=new JSONArray();

                    JSONObject json1 = new JSONObject();
                    json1.put("sName", dataBean.getSubservicelist().get(0).getTypeList().get(foodposition).getSName());
                    json1.put("nID", dataBean.getSubservicelist().get(0).getTypeList().get(foodposition).getSName());

                    foodtypejsonArray.put(json1);
//            }

//            for (int i = 0; i < domesticHelpMaidModel.getData().getCommonlanguagelist().size(); i++) {
                    //Map<String, String> json = new HashMap<>();
                    JSONObject json2 = new JSONObject();
                    json2.put("sName", dataBean.getSubservicelist().get(1).getTypeList().get(religionposition).getSName());
                    json2.put("nID", dataBean.getSubservicelist().get(1).getTypeList().get(religionposition).getNID());

                    religiontypejsonArray.put(json2);


                    //Map<String, String> json = new HashMap<>();
                    JSONObject json = new JSONObject();
                    json.put("nSubServiceID", dataBean.getSubservicelist().get(0).getNSubServiceID());
//                            json.put("nServiceID", selectedList.get(i).getNServiceID());
                    json.put("sSubServiceName", dataBean.getSubservicelist().get(0).getSSubServiceName());
                    json.put("bMandatory",  dataBean.getSubservicelist().get(0).getBMandatory());
                    json.put("bselection", "1");
                    json.put("typeList", foodtypejsonArray);

                    selectedlistarray.put(0, json);

                    JSONObject jsonn = new JSONObject();
                    jsonn.put("nSubServiceID", dataBean.getSubservicelist().get(1).getNSubServiceID());
//                            json.put("nServiceID", selectedList.get(i).getNServiceID());
                    jsonn.put("sSubServiceName", dataBean.getSubservicelist().get(1).getSSubServiceName());
                    jsonn.put("bMandatory",  dataBean.getSubservicelist().get(1).getBMandatory());
                    jsonn.put("bselection", "1");
                    jsonn.put("typeList", religiontypejsonArray);

                    selectedlistarray.put(1, jsonn);

                    JSONObject jjsonn1 = new JSONObject();
                    jjsonn1.put("nSubServiceID", dataBean.getSubservicelist().get(2).getNSubServiceID());
//                            json.put("nServiceID", selectedList.get(i).getNServiceID());
                    jjsonn1.put("sSubServiceName", dataBean.getSubservicelist().get(2).getSSubServiceName());
                    jjsonn1.put("sOthers",cusines_et.getText().toString());


                    selectedlistarray.put(jjsonn1);

                    JSONObject jjsonn2 = new JSONObject();
                    jjsonn2.put("nSubServiceID", dataBean.getSubservicelist().get(3).getNSubServiceID());
//                            json.put("nServiceID", selectedList.get(i).getNServiceID());
                    jjsonn2.put("sSubServiceName", dataBean.getSubservicelist().get(3).getSSubServiceName());
                    jjsonn2.put("sOthers",exp_et.getText().toString());


                    selectedlistarray.put(jjsonn2);

                } catch (Exception e) {
                    e.printStackTrace();
                }


                    try {

                        if (foodposition==-1)
                        {
                            Toast.makeText(activity, "Please select foodtype", Toast.LENGTH_SHORT).show();
                        }else if (religionposition==-1){

                            Toast.makeText(activity, "Please select religiontype", Toast.LENGTH_SHORT).show();
                        }else if (exp_et.getText().toString().length()==0)
                        {
                            Toast.makeText(activity, "Please enter experience", Toast.LENGTH_SHORT).show();

                        }else {

                            JSONObject requestBody = new JSONObject();
                            requestBody.put("nOrderID", dataBean.getCommonlist().getNOrderID());
                            requestBody.put("nServiceID", dataBean.getCommonlist().getNServiceID());
                            requestBody.put("subservicelist", selectedlistarray);
                            requestBody.put("nClientID", dataBean.getNClientID());
                            presenter.savedomesticservice_help(Cook_screen.this, apiResponseCallback, requestBody);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


//                Intent intent = new Intent(Cook_screen.this, Placing_order_okay.class);
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

                Toast.makeText(Cook_screen.this, "Please check your internet connection", Toast.LENGTH_LONG).show();


            } else if (NetworkConstants.RequestCode.SAVEDOMESTICSERVICE_HELP == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {

                    JSONObject jsonObject1 = jsonObject.getJSONObject("data");

                    Util.getInstance().cusToast(Cook_screen.this, jsonObject1.optString("servicemessage"));


//                    Intent intent = new Intent(Help_maid_screen.this, Placing_order_okay.class);
//                    startActivity(intent);
//                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_PLACING_ORDER_OKAY_SCREEN);

                    Intent intent = new Intent(Cook_screen.this, Profile_found_screen.class);
                    startActivity(intent);


                } else {

                    Util.getInstance().cusToast(Cook_screen.this, jsonObject.optString("message"));
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
