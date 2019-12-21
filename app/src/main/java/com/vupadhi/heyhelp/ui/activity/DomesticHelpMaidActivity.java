package com.vupadhi.heyhelp.ui.activity;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.app.constants.AppConstants;
import com.vupadhi.heyhelp.app.controller.ApplicationController;
import com.vupadhi.heyhelp.base.BaseAbstractActivity;
import com.vupadhi.heyhelp.models.ChildCareScreenModel;
import com.vupadhi.heyhelp.models.CookScreenModel;
import com.vupadhi.heyhelp.models.DomesticHelpMaidModel;
import com.vupadhi.heyhelp.models.ElderCareModel;
import com.vupadhi.heyhelp.models.HelpMaidScreenModel;
import com.vupadhi.heyhelp.models.SecurityGuardScreenModel;
import com.vupadhi.heyhelp.mvp.contract.activity.DomesticHelpMaidContract;
import com.vupadhi.heyhelp.mvp.presenter.activity.DomesticHelpMaidImpl;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.sharepref.UserSession;
import com.vupadhi.heyhelp.utils.Util;
import com.vupadhi.heyhelp.utils.Variables;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DomesticHelpMaidActivity extends BaseAbstractActivity<DomesticHelpMaidImpl> implements DomesticHelpMaidContract.IView, APIResponseCallback {
    ImageView imageView;
    LinearLayout linearLayout;
    LinearLayout linearLayout_telugu, linearLayout_hindi, linearLayout_english, linearLayout_male, linearLayout_female, linear_others;
    TextView male_text, female_text, telugu_text, hindi_text, english_text, others_text;

    int genderposition = -1, languageposition = -1;

    APIResponseCallback apiResponseCallback;
    UserSession userSession;
    String nClientid, nServiceid;
    DomesticHelpMaidModel domesticHelpMaidModel;
    Spinner spl;
    String time = "", timeid = "";
    TextView header_tv;
    int selected_position = -1;
    TextView sp_text;
    RelativeLayout relativelayoutt1;
    ImageView dropimg;
    int spinnerposition, telugucount = 0, hindicount = 0, englishcount = 0, otherscount = 0;

    JSONArray timejsonArray = new JSONArray();
    JSONArray genderjsonArray = new JSONArray();
    JSONArray langjsonArray = new JSONArray();

    ArrayList<String> genderlist, languageslist, genderlistids, languagelistids, spinnerlist, spinnerlistids, selectedlanglist, selectedlanglistids;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();

    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_domestic_help_maid, null);
        return view;
    }

    @Override
    public void setPresenter() {

        presenter = new DomesticHelpMaidImpl(this, this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        apiResponseCallback = this;
        userSession = new UserSession(this);

        genderlist = new ArrayList<>();
        languageslist = new ArrayList<>();
        genderlistids = new ArrayList<>();
        languagelistids = new ArrayList<>();
        spinnerlist = new ArrayList<>();
        spinnerlistids = new ArrayList<>();
        selectedlanglist = new ArrayList<>();
        selectedlanglistids = new ArrayList<>();

        Bundle b = getIntent().getExtras();
        nServiceid = b.getString("nServiceid");


        nClientid = userSession.getUserDetails().get(UserSession.KEY_nCLIENTID);

        imageView = findViewById(R.id.help_back_arrow);
        linearLayout = findViewById(R.id.help_submit_lay_out);
        linearLayout_telugu = findViewById(R.id.linear_telugu);
        linearLayout_english = findViewById(R.id.linear_english);
        linearLayout_hindi = findViewById(R.id.linear_hindi);
        linearLayout_male = findViewById(R.id.male_lay_out);
        linearLayout_female = findViewById(R.id.female_lay_out);
        linear_others = findViewById(R.id.linear_others);
        spl = findViewById(R.id.spinner);
        sp_text = findViewById(R.id.tv_spinnertext);
        relativelayoutt1 = findViewById(R.id.relativelayoutt1);
        dropimg = findViewById(R.id.dropimg);

        male_text = findViewById(R.id.male_text);
        female_text = findViewById(R.id.female_text);
        telugu_text = findViewById(R.id.telugu_text);
        hindi_text = findViewById(R.id.hindi_text);
        english_text = findViewById(R.id.english_text);
        others_text = findViewById(R.id.others_text);
        header_tv = findViewById(R.id.header_tv);

        relativelayoutt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spl.performClick();
            }
        });
        sp_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spl.performClick();
            }
        });
        dropimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spl.performClick();
            }
        });

        if (nServiceid.equalsIgnoreCase("1")) {
            header_tv.setText("Domestic Help/Maid");
        } else if (nServiceid.equalsIgnoreCase("2")) {
            header_tv.setText("Child Care");
        } else if (nServiceid.equalsIgnoreCase("3")) {
            header_tv.setText("Elder Care");
        } else if (nServiceid.equalsIgnoreCase("4")) {
            header_tv.setText("Cook");
        } else if (nServiceid.equalsIgnoreCase("5")) {
            header_tv.setText("Security Guard");
        }
        String url = nClientid + "&&nServiceID=" + nServiceid;

        Map<String, String> requestBody = new HashMap<>();
        presenter.domestic_help(DomesticHelpMaidActivity.this, apiResponseCallback, requestBody, url);


        linearLayout_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genderposition = 1;

                linearLayout_female.setBackgroundResource(R.drawable.selected_sky);
                linearLayout_male.setBackgroundResource(R.drawable.selected_white);
            }
        });
        linearLayout_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genderposition = 0;

                linearLayout_female.setBackgroundResource(R.drawable.selected_white);
                linearLayout_male.setBackgroundResource(R.drawable.selected_sky);
            }
        });
        linearLayout_telugu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                languageposition = 0;
                if (telugucount == 0) {

                    selectedlanglistids.add(languagelistids.get(0));

                    selectedlanglist.add(languageslist.get(0));
                    linearLayout_telugu.setBackgroundResource(R.drawable.selected_sky);
                    telugucount = 1;


                } else {

                    linearLayout_telugu.setBackgroundResource(R.drawable.selected_white);
                    telugucount = 0;
                    int foundposition = selectedlanglist.indexOf(languageslist.get(0));
                    selectedlanglist.remove(foundposition);
                    int foundposition1 = selectedlanglistids.indexOf(languagelistids.get(0));
                    selectedlanglistids.remove(foundposition1);
                }
//                linearLayout_telugu.setBackgroundResource(R.drawable.selected_sky);
//                linearLayout_english.setBackgroundResource(R.drawable.selected_white);
//                linearLayout_hindi.setBackgroundResource(R.drawable.selected_white);
            }
        });
        linearLayout_hindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                languageposition = 1;
                if (hindicount == 0) {

                    selectedlanglistids.add(languagelistids.get(1));

                    selectedlanglist.add(languageslist.get(1));
                    linearLayout_hindi.setBackgroundResource(R.drawable.selected_sky);
                    hindicount = 1;


                } else {

                    linearLayout_hindi.setBackgroundResource(R.drawable.selected_white);
                    hindicount = 0;

                    int foundposition = selectedlanglist.indexOf(languageslist.get(1));
                    selectedlanglist.remove(foundposition);
                    int foundposition1 = selectedlanglistids.indexOf(languagelistids.get(1));
                    selectedlanglistids.remove(foundposition1);
                }

//
//                linearLayout_telugu.setBackgroundResource(R.drawable.selected_white);
//                linearLayout_english.setBackgroundResource(R.drawable.selected_white);
//                linearLayout_hindi.setBackgroundResource(R.drawable.selected_sky);
            }
        });
        linearLayout_english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                languageposition = 2;
                if (englishcount == 0) {

                    selectedlanglistids.add(languagelistids.get(2));

                    selectedlanglist.add(languageslist.get(2));
                    linearLayout_english.setBackgroundResource(R.drawable.selected_sky);
                    englishcount = 1;


                } else {

                    linearLayout_english.setBackgroundResource(R.drawable.selected_white);
                    englishcount = 0;
                    int foundposition = selectedlanglist.indexOf(languageslist.get(2));
                    selectedlanglist.remove(foundposition);
                    int foundposition1 = selectedlanglistids.indexOf(languagelistids.get(2));
                    selectedlanglistids.remove(foundposition1);
                }

//                linearLayout_telugu.setBackgroundResource(R.drawable.selected_white);
//                linearLayout_english.setBackgroundResource(R.drawable.selected_sky);
//                linearLayout_hindi.setBackgroundResource(R.drawable.selected_white);
            }
        });

        linear_others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                languageposition = 3;
                if (otherscount == 0) {

                    selectedlanglistids.add(languagelistids.get(3));

                    selectedlanglist.add(languageslist.get(3));
                    linear_others.setBackgroundResource(R.drawable.selected_sky);
                    otherscount = 1;


                } else {

                    linear_others.setBackgroundResource(R.drawable.selected_white);
                    otherscount = 0;

                    int foundposition = selectedlanglist.indexOf(languageslist.get(3));
                    selectedlanglist.remove(foundposition);
                    int foundposition1 = selectedlanglistids.indexOf(languagelistids.get(3));
                    selectedlanglistids.remove(foundposition1);
                }

//
//                linearLayout_telugu.setBackgroundResource(R.drawable.selected_white);
//                linearLayout_english.setBackgroundResource(R.drawable.selected_white);
//                linearLayout_hindi.setBackgroundResource(R.drawable.selected_sky);
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

                if (time.equalsIgnoreCase("") || time.isEmpty()) {
                    Toast.makeText(activity, "Please select timings", Toast.LENGTH_SHORT).show();
                } else if (genderposition == -1) {
                    Toast.makeText(activity, "Please select gender", Toast.LENGTH_SHORT).show();
                } else if (languageposition == -1) {
                    Toast.makeText(activity, "Please select language", Toast.LENGTH_SHORT).show();
                } else {
                    callsaveDomestichelpWS();
                }

//                Intent intent = new Intent(DomesticHelpMaidActivity.this, Help_maid_screen.class);
//                startActivity(intent);
            }
        });



       /* sp1.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(DomesticHelpMaidActivity.this,
                                sp1.getSelectedItem().toString(),
                                Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );*/

    }


    private void callsaveDomestichelpWS() {
        try {

            timejsonArray = new JSONArray();
            genderjsonArray = new JSONArray();
            langjsonArray = new JSONArray();
//            for (int i = 0; i < domesticHelpMaidModel.getData().getCommontimingslist().size(); i++) {
            //Map<String, String> json = new HashMap<>();
            JSONObject json = new JSONObject();
            json.put("bselection", "1");
            json.put("name", time);
            json.put("c_ID", timeid);

            timejsonArray.put(json);
//            }

//            for (int i = 0; i < domesticHelpMaidModel.getData().getCommonlanguagelist().size(); i++) {
            //Map<String, String> json = new HashMap<>();
            JSONObject json1 = new JSONObject();
            json1.put("bselection", "1");
            json1.put("name", genderlist.get(genderposition));
            json1.put("c_ID", genderlistids.get(genderposition));

            genderjsonArray.put(json1);
//            }
            for (int i = 0; i < selectedlanglist.size(); i++) {
                //Map<String, String> json = new HashMap<>();
                JSONObject json2 = new JSONObject();
                json2.put("bselection", "1");
                json2.put("name", selectedlanglist.get(i));
                json2.put("c_ID", selectedlanglistids.get(i));

                langjsonArray.put(json2);
            }


            JSONObject requestBody = new JSONObject();
            requestBody.put("nClientID", nClientid);
            requestBody.put("nServiceID", nServiceid);
            requestBody.put("commontimingslist", timejsonArray);
            requestBody.put("commongenderlist", genderjsonArray);
            requestBody.put("commonlanguagelist", langjsonArray);
            presenter.savedomestic_help(DomesticHelpMaidActivity.this, apiResponseCallback, requestBody);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {

                Toast.makeText(DomesticHelpMaidActivity.this, "Please check your internet connection", Toast.LENGTH_LONG).show();


            } else if (NetworkConstants.RequestCode.DOMESTIC_HELP == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {

                    domesticHelpMaidModel = new Gson().fromJson(responseString, DomesticHelpMaidModel.class);


                    JSONObject datajsonObject = jsonObject.getJSONObject("data");

                    JSONArray jsonArray = datajsonObject.getJSONArray("commongenderlist");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String id = jsonObject1.getString("c_ID");
                        String servicename = jsonObject1.getString("name");

                        genderlist.add(servicename);
                        genderlistids.add(id);
                    }

                    TextView[] tv = new TextView[2];
                    tv[0] = male_text;
                    tv[1] = female_text;


                    for (int i = 0; i < genderlist.size(); i++) {
                        tv[i].setText(genderlist.get(i));
                    }


                    JSONArray jsonArray1 = datajsonObject.getJSONArray("commonlanguagelist");
                    for (int i = 0; i < jsonArray1.length(); i++) {
                        JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                        String id = jsonObject1.getString("c_ID");
                        String name = jsonObject1.getString("name");

                        languageslist.add(name);
                        languagelistids.add(id);
                    }

                    TextView[] tv1 = new TextView[4];
                    tv1[0] = telugu_text;
                    tv1[1] = hindi_text;
                    tv1[2] = english_text;
                    tv1[3] = others_text;


                    for (int i = 0; i < languageslist.size(); i++) {
                        tv1[i].setText(languageslist.get(i));
                    }


                    JSONArray jsonArray2 = datajsonObject.getJSONArray("commontimingslist");

                    spinnerlist.clear();
                    spinnerlistids.clear();
                    spinnerlist.add("Select");
                    spinnerlistids.add("");
//                    pricetypeids.clear();

                    String sm_time = userSession.getTimeDetails().get(UserSession.KEY_TIME);


                    for (int i = 0; i < jsonArray2.length(); i++) {
                        JSONObject jsonObject1 = jsonArray2.getJSONObject(i);

                        String id = jsonObject1.getString("c_ID");
                        String name = jsonObject1.getString("name");


                      /*  if (sm_time != null) {

                            if (name.equalsIgnoreCase(sm_time)) {

                                selected_position = i;
                            }
                        }
*/

                        spinnerlist.add(name);
                        spinnerlistids.add(id);

                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, spinnerlist);

                    adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
                    spl.setAdapter(adapter);

                   /* if (sm_time != null) {

                        //  sp_text.setText(selected_position+1);

                        spl.setSelection(selected_position + 1);

                    }*/

                    spl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                            if (position > 0) {
                                spinnerposition = position;

                                time = spinnerlist.get(position);
                                timeid = spinnerlistids.get(position);

                                sp_text.setText(time);

   //                             Toast.makeText(context, "" + spinnerlist.get(position), Toast.LENGTH_SHORT).show();

   //                             userSession.StoreTimeDetails(time);
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                } else {

                    Util.getInstance().cusToast(DomesticHelpMaidActivity.this, jsonObject.optString("message"));
                }
            } else if (NetworkConstants.RequestCode.SAVEDOMESTIC_HELP == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {
                    //Util.getInstance().cusToast(VerificationActivity.this, jsonObject.optString("message"));


                    Bundle b = new Bundle();
////                    b.putString("helpMaidScreenModel", helpMaidScreenModel.getData().toString());
//                    b.putParcelable("helpMaidScreenModel",helpMaidScreenModel.getData());
                    if (nServiceid.equalsIgnoreCase("1")) {

                        HelpMaidScreenModel helpMaidScreenModel = new Gson().fromJson(responseString, HelpMaidScreenModel.class);

                        Variables.helpmaidarrayList = helpMaidScreenModel.getData();

                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_HELP_MAID_SCREEN, b);

                    } else if (nServiceid.equalsIgnoreCase("2")) {

                        ChildCareScreenModel childCareScreenModel = new Gson().fromJson(responseString, ChildCareScreenModel.class);

                        Variables.childcarearrayList = childCareScreenModel.getData();

                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_CHILDCARE_SCREEN, b);

                    } else if (nServiceid.equalsIgnoreCase("3")) {

                        ElderCareModel elderCareModel = new Gson().fromJson(responseString, ElderCareModel.class);

                        Variables.eldercarearrayList = elderCareModel.getData();

                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_ELDERCARE_SCREEN, b);

                    } else if (nServiceid.equalsIgnoreCase("4")) {

                        CookScreenModel cookScreenModel = new Gson().fromJson(responseString, CookScreenModel.class);

                        Variables.cookarrayList = cookScreenModel.getData();

                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_COOK_SCREEN, b);

                    } else if (nServiceid.equalsIgnoreCase("5")) {

                        SecurityGuardScreenModel securityGuardScreenModel = new Gson().fromJson(responseString, SecurityGuardScreenModel.class);

                        Variables.securityarrayList = securityGuardScreenModel.getData();

                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_SECURITY_GUARD_SCREEN, b);

                    }

                } else {
                    //                  Util.getInstance().cusToast(DomesticHelpMaidActivity.this, jsonObject.optString("message"));

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
