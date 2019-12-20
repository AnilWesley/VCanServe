package com.vupadhi.heyhelp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vupadhi.heyhelp.Adapter.HelpMaidRecyclerAdapter;
import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.base.BaseAbstractActivity;
import com.vupadhi.heyhelp.models.HelpMaidScreenModel;
import com.vupadhi.heyhelp.mvp.contract.activity.HelpMaidScreenContract;
import com.vupadhi.heyhelp.mvp.presenter.activity.HelpMaidScreenImpl;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.utils.Util;
import com.vupadhi.heyhelp.utils.Variables;
import com.suke.widget.SwitchButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Help_maid_screen extends BaseAbstractActivity<HelpMaidScreenImpl> implements HelpMaidScreenContract.IView, APIResponseCallback {
    ImageView imageView;
    LinearLayout linearLayout, submit_ll;
    RecyclerView helpmaidlist_rv;
    SwitchButton switch_button_one, switch_button_two, switch_button_three, switch_button_four, switch_button_five, switch_button_six, switch_button_seven;

    JSONObject jsonObject;
    HelpMaidScreenModel helpMaidScreenModel;
    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8;

    ArrayList<String> serviceidlist, subservicenamelist, bmandatorylist;
    String serviceid, subservicename, bmandatory;
    int servicenameposition;
    HelpMaidRecyclerAdapter adapter;
    APIResponseCallback apiResponseCallback;
    JSONArray selectedlistarray = new JSONArray();

    private ArrayList<HelpMaidScreenModel.DataBean.SubservicelistBean> selectedList = new ArrayList<>();


    public static JSONArray listarray = new JSONArray();

    StringBuilder stringBuilder, stringBuilder1;
    String titles, ids;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();


    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_help_maid_screen, null);
        return view;
    }

    @Override
    public void setPresenter() {

        presenter = new HelpMaidScreenImpl(this, this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        apiResponseCallback = this;
        serviceidlist = new ArrayList<>();
        subservicenamelist = new ArrayList<>();
        bmandatorylist = new ArrayList<>();
        imageView = findViewById(R.id.domestic_back_but);
        helpmaidlist_rv = findViewById(R.id.helpmaidlist_rv);
        submit_ll = findViewById(R.id.domestic_submit_lay_out);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv5 = findViewById(R.id.tv5);
        tv6 = findViewById(R.id.tv6);
        tv7 = findViewById(R.id.tv7);
        tv8 = findViewById(R.id.tv8);

        switch_button_one = findViewById(R.id.switch_button_one);
        switch_button_two = findViewById(R.id.switch_button_two);
        switch_button_three = findViewById(R.id.switch_button_three);
        switch_button_four = findViewById(R.id.switch_button_four);
        switch_button_five = findViewById(R.id.switch_button_five);
        switch_button_six = findViewById(R.id.switch_button_six);
        switch_button_seven = findViewById(R.id.switch_button_seven);

      /*//  Bundle b=getIntent().getExtras();
        Intent intent=getIntent();
      HelpMaidScreenModel.DataBean  data = intent.getParcelableExtra("helpMaidScreenModel");
        System.out.println("databean"+data);
*/

        HelpMaidScreenModel.DataBean dataBean = Variables.helpmaidarrayList;


        Variables.ServiceID=dataBean.getCommonlist().getNServiceID();

        try {
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(Help_maid_screen.this);
            //setting horizontal list
            mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            helpmaidlist_rv.setLayoutManager(mLayoutManager);
            helpmaidlist_rv.setItemAnimator(new DefaultItemAnimator());


            //Adding Adapter to recyclerView
            adapter = new HelpMaidRecyclerAdapter(dataBean.getSubservicelist(), Help_maid_screen.this);
            helpmaidlist_rv.setAdapter(adapter);


        } catch (Exception e) {
            e.printStackTrace();
        }


     /*   switch_button_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                servicenameposition = 0;
                switch_button_one.setChecked(true);
            }
        });
*/

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        submit_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                selectedList = adapter.getselectedList();
                listarray = new JSONArray();
                selectedlistarray=new JSONArray();

                stringBuilder = new StringBuilder();
                stringBuilder1 = new StringBuilder();
                String selectedtitle;
                String selectedid;


                if (selectedList != null && selectedList.size() > 0) {



                    for (int i = 0; i < selectedList.size(); i++) {


                        listarray.put(selectedList.get(i).getNSubServiceID());

                        System.out.println("jsonarray" + listarray);

                        try {

                            //Map<String, String> json = new HashMap<>();
                            JSONObject json = new JSONObject();
                            json.put("nSubServiceID", selectedList.get(i).getNSubServiceID());
                            json.put("nServiceID", selectedList.get(i).getNServiceID());
                            json.put("sSubServiceName", selectedList.get(i).getSSubServiceName());
                            json.put("bMandatory", selectedList.get(i).getBMandatory());
                            json.put("bselection", "1");

                            selectedlistarray.put(json);

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

                        JSONObject requestBody = new JSONObject();
                        requestBody.put("nOrderID", dataBean.getCommonlist().getNOrderID());
                        requestBody.put("nServiceID", dataBean.getCommonlist().getNServiceID());
                        requestBody.put("subservicelist", selectedlistarray);
                        requestBody.put("nClientID", dataBean.getNClientID());
                        presenter.savedomesticservice_help(Help_maid_screen.this, apiResponseCallback, requestBody);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                } else {

                    Toast.makeText(activity, "Please select any service", Toast.LENGTH_SHORT).show();
                }


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

                Toast.makeText(Help_maid_screen.this, "Please check your internet connection", Toast.LENGTH_LONG).show();


            } else if (NetworkConstants.RequestCode.SAVEDOMESTICSERVICE_HELP == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {

                    JSONObject jsonObject1 = jsonObject.getJSONObject("data");

                    Util.getInstance().cusToast(Help_maid_screen.this, jsonObject1.optString("servicemessage"));


//                    Intent intent = new Intent(Help_maid_screen.this, Placing_order_okay.class);
//                    startActivity(intent);
//                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_PLACING_ORDER_OKAY_SCREEN);
                    Intent intent=new Intent(Help_maid_screen.this, Profile_found_screen.class);
                    startActivity(intent);

                } else {

 //                   Util.getInstance().cusToast(Help_maid_screen.this, jsonObject.optString("message"));
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
