package com.vupadhi.heyhelp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.vupadhi.heyhelp.Adapter.Mark_absent_adapter;
import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.app.constants.AppConstants;
import com.vupadhi.heyhelp.app.controller.ApplicationController;
import com.vupadhi.heyhelp.base.BaseAbstractActivity;
import com.vupadhi.heyhelp.models.MarkAbsentWorkersModel;
import com.vupadhi.heyhelp.mvp.contract.activity.MarkAbsentContract;
import com.vupadhi.heyhelp.mvp.presenter.activity.MarkAbsentImpl;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.sharepref.UserSession;
import com.vupadhi.heyhelp.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import ru.slybeaver.slycalendarview.SlyCalendarDialog;

public class Mark_absent extends BaseAbstractActivity<MarkAbsentImpl> implements MarkAbsentContract.IView, APIResponseCallback, SlyCalendarDialog.Callback {
    RecyclerView recyclerView;
    LinearLayout linearLayout;
    ImageView imageView;
    Mark_absent_adapter adapter;
    public static MarkAbsentWorkersModel markAbsentWorkersModel;
    APIResponseCallback apiResponseCallback;
    UserSession userSession;
    String nClientid;
    TextView nodata_tv;

    String firstdate, seconddate;

    ArrayList<String> firstdatelist;
    ArrayList<String> seconddatelist;
    public  static List<String> dateslist=new ArrayList<>();

    JSONArray dateslistarray = new JSONArray();
    JSONArray selectedlistarray = new JSONArray();

    public static int position1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_mark_absent, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new MarkAbsentImpl(this, this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        apiResponseCallback = this;
        userSession = new UserSession(Mark_absent.this);
        nClientid = userSession.getUserDetails().get(UserSession.KEY_nCLIENTID);

        firstdatelist = new ArrayList<>();
        seconddatelist = new ArrayList<>();
//        dateslist = new ArrayList<>();


        linearLayout = findViewById(R.id.mark_absent__submit_lay_out);
        recyclerView = findViewById(R.id.mark_absent_recycle_view);
        imageView = findViewById(R.id.mark_back_but);
        nodata_tv = findViewById(R.id.nodata_tv);
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

                    dateslistarray = new JSONArray();
                    selectedlistarray = new JSONArray();

                    for (int i = 0; i < markAbsentWorkersModel.getData().getWorkerList().size(); i++) {
                       /* String dates = firstdatelist.get(i) + "," + seconddatelist.get(i);

                        Log.d("dates...", dates);*/

                        JSONObject json1 = new JSONObject();
                        json1.put("dtAbsentDate", dateslist.get(i));

                        dateslistarray.put(json1);

                        if (dateslist.get(i).equalsIgnoreCase("0")) {
                            Toast.makeText(activity, "Select absent dates for all workers", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(activity, "dates selected", Toast.LENGTH_SHORT).show();


                            JSONObject json = new JSONObject();
                            json.put("nWorkerID", markAbsentWorkersModel.getData().getWorkerList().get(i).getNWorkerID());
                            json.put("nPartnerID", markAbsentWorkersModel.getData().getWorkerList().get(i).getNPartnerID());
                            json.put("datelist",dateslistarray);

                            selectedlistarray.put(json);
                        }

                    }

                    JSONObject requestBody = new JSONObject();
                    requestBody.put("workerlist", selectedlistarray);
                    requestBody.put("nClientID", nClientid);
                    presenter.saveworkerabsence_list(Mark_absent.this, apiResponseCallback, requestBody);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


        Map<String, String> requestBody = new HashMap<>();
        presenter.getworkerlist(Mark_absent.this, apiResponseCallback, requestBody, nClientid);

    }


    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
//                String result = data.getStringExtra("datelist");
//
//                dateslist = Arrays.asList(result.trim().split(","));
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {

                Toast.makeText(Mark_absent.this, "Please check your internet connection", Toast.LENGTH_LONG).show();


            } else if (NetworkConstants.RequestCode.GETWORKERLIST == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {

                    markAbsentWorkersModel = new Gson().fromJson(responseString, MarkAbsentWorkersModel.class);



                    nodata_tv.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);

                    if (markAbsentWorkersModel.getData().getWorkerList().size() > 0) {


                        for (int i = 0; i < Mark_absent.markAbsentWorkersModel.getData().getWorkerList().size(); i++) {

                            dateslist.add("0");

                        }

                        nodata_tv.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);

                        LinearLayoutManager mLayoutManager = new LinearLayoutManager(Mark_absent.this);
                        //setting horizontal list
                        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());


                        //Adding Adapter to recyclerView
                        adapter = new Mark_absent_adapter(markAbsentWorkersModel, Mark_absent.this);
                        recyclerView.setAdapter(adapter);
                        adapter.setOnItemClickListener(new Mark_absent_adapter.OnitemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                switch (view.getId()) {
                                    case R.id.calndr_img:

                                        Intent i = new Intent(Mark_absent.this, Select_absent_date_screen.class);
                                        startActivityForResult(i, 1);
                                        position1 = position;

                                      /*  new SlyCalendarDialog()
                                                .setSingle(false)
                                                .setFirstMonday(false)
                                                .setCallback(Mark_absent.this)
                                                .show(getSupportFragmentManager(), "TAG_SLYCALENDAR");
*/
                                }

                            }
                        });

                    } else {
                        nodata_tv.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    }

                } else {

                    nodata_tv.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                    //                  Util.getInstance().cusToast(Mark_absent.this, jsonObject.optString("message"));
                }
            } else if (NetworkConstants.RequestCode.SAVEWORKERABSENCE_LIST == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {

                    JSONObject jsonObject1=jsonObject.getJSONObject("data");

            //        Util.getInstance().cusToast(Mark_absent.this, jsonObject1.optString("status"));


                    Toast.makeText(activity, "Absence submitted successfully", Toast.LENGTH_SHORT).show();

                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_HOME_SCREEN, new Bundle());

                } else {

                    String message = "";
                    JSONObject jsonObject1 = jsonObject.getJSONObject("error");
                    JSONArray jsonArray = jsonObject1.getJSONArray("errors");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                        message = jsonObject2.getString("message");

                    }

                    Util.getInstance().cusToast(Mark_absent.this, message);


                    //                  Util.getInstance().cusToast(Mark_absent.this, jsonObject.optString("message"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }

    @Override
    public void onCancelled() {

    }

    @Override
    public void onDataSelected(Calendar firstDate, Calendar secondDate, int hours, int minutes) {

      /*  Calendar c = Calendar.getInstance();
        int cyear = c.get(Calendar.YEAR);
        int cmonth = c.get(Calendar.MONTH);
        int cday = c.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog   mDatePicker =new DatePickerDialog(Mark_absent.this, new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday)
            {
//                ed_date.setText(new StringBuilder().append(year).append("-").append(month+1).append("-").append(day));
//                int month_k=selectedmonth+1;

            }
        },cyear, cmonth, cday);
        mDatePicker.setTitle("Please select date");
        // TODO Hide Future Date Here
        mDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis());

        // TODO Hide Past Date Here
        //  mDatePicker.getDatePicker().setMinDate(System.currentTimeMillis());

        mDatePicker.show();
*/
        if (secondDate == null) {

            Toast.makeText(activity, "please select second date", Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(this, new SimpleDateFormat(getString(R.string.timeFormat), Locale.getDefault()).format(firstDate.getTime()), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, new SimpleDateFormat(getString(R.string.timeFormat), Locale.getDefault()).format(secondDate.getTime()), Toast.LENGTH_SHORT).show();

            firstdate = new SimpleDateFormat(getString(R.string.timeFormat), Locale.getDefault()).format(firstDate.getTime());
            seconddate = new SimpleDateFormat(getString(R.string.timeFormat), Locale.getDefault()).format(secondDate.getTime());
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("EE, dd MMM HH:mm");
        try {
            Date d = dateFormat.parse(firstdate);
            String finaldate = (new SimpleDateFormat("dd/MM/yyyy")).format(d);

            System.out.println("finaldate" + finaldate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
/*
        SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");
        try {
            String date1=formatter1.format(firstdate);
            System.out.println("Current Date = "+date1);

        } catch (Exception e) {
            e.printStackTrace();
        }*/

//        Format f = new SimpleDateFormat("dd/MMM/yyyy");
//        String strDate = f.format(firstDate);
//        System.out.println("Current Date = "+strDate);
        // Display date with day name in a short format


       /* for (int i=0;i<markAbsentWorkersModel.getData().getWorkerList().size();i++) {
            if (position1==i) {
                firstdatelist.add(firstdate);
                seconddatelist.add(seconddate);
            }
        }*/

        firstdatelist.add(position1, firstdate);
        seconddatelist.add(position1, seconddate);
      /*  if (firstDate != null) {
            if (secondDate == null) {
                firstDate.set(Calendar.HOUR_OF_DAY, hours);
                firstDate.set(Calendar.MINUTE, minutes);
                Toast.makeText(
                        this,
                        new SimpleDateFormat(getString(R.string.timeFormat), Locale.getDefault()).format(firstDate.getTime()),
                        Toast.LENGTH_LONG

                ).show();
            } else {
                Toast.makeText(
                        this,
                        getString(
                                R.string.period,
                                new SimpleDateFormat(getString(R.string.dateFormat), Locale.getDefault()).format(firstDate.getTime()),
                                new SimpleDateFormat(getString(R.string.timeFormat), Locale.getDefault()).format(secondDate.getTime())
                        ),
                        Toast.LENGTH_LONG

                ).show();
            }
        }*/
    }
}
