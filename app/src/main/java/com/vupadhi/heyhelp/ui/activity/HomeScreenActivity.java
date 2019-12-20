package com.vupadhi.heyhelp.ui.activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.vupadhi.heyhelp.base.BaseAbstractActivity;
import com.vupadhi.heyhelp.mvp.contract.activity.HomeScreenActivityContract;
import com.vupadhi.heyhelp.mvp.presenter.activity.HomeActivityImpl;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.sharepref.UserSession;
import com.vupadhi.heyhelp.ui.Fragments.HomeFragment;
import com.vupadhi.heyhelp.ui.Fragments.OffersFragment;
import com.vupadhi.heyhelp.ui.Fragments.ProfileCompanyFragment;
import com.vupadhi.heyhelp.ui.Fragments.ProfileIndividualFragment;
import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.customfonts.CustomTextViewBold;
import com.vupadhi.heyhelp.customfonts.CustomTextViewSemiBold;
import com.vupadhi.heyhelp.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeScreenActivity extends BaseAbstractActivity<HomeActivityImpl> implements HomeScreenActivityContract.IView, APIResponseCallback {
    ImageView imageView;
    NavigationView nav_menu;
    CustomTextViewSemiBold ctvsb_my_service_request, ctvsb_my_profile, ctvsb_payments,
            ctvsb_mark_absent, ctvsb_ticket, ctvsb_feedback, ctvsb_rateus, ctvsb_aboutus,
            ctvsb_contactus, ctvsb_changepwd, ctvsb_signout;
    Fragment fragment = null;
    FragmentTransaction fragmentTransaction;
    private ImageView iv_menu, back_arrow;
    private DrawerLayout drawer_layout;
    private LinearLayout linear_profile, linear_offers, linear_home;
    private CustomTextViewBold ctvb_profile, ctvb_offers, ctvb_home;
    private ImageView iv_home, iv_offers, iv_profile, img_downarrow;
    TextView loc_text;

    RelativeLayout loc_ll;
    Spinner spinner;

    String sloc, slocid, name, username, userlocation;

    String homelist, nUserType, nClientid;
    UserSession userSession;
    APIResponseCallback apiResponseCallback;

    List<String> homelistdata;
    public static String newrequest = "";

    ArrayList<String> loclist, loclistids;
    TextView welcome_text;

    private void switchFragment(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();

    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_home_screen, null);
        return view;
    }

    @Override
    public void setPresenter() {

        presenter = new HomeActivityImpl(this, this);

    }

    @Override
    protected void initializeViews() {

        super.initializeViews();

        apiResponseCallback = this;

        userSession = new UserSession(this);
        nUserType = userSession.getUserDetails().get(UserSession.KEY_nUSERTYPE);
        nClientid = userSession.getUserDetails().get(UserSession.KEY_nCLIENTID);
        username = userSession.getUserDetails().get(UserSession.KEY_NAME);
        userlocation = userSession.getUserDetails().get(UserSession.KEY_LOCATION);
        loclist = new ArrayList<>();
        loclistids = new ArrayList<>();



        Intent intent = getIntent();

        if (intent.getExtras() != null) {
            newrequest = intent.getStringExtra("newrequest");

        }



       /* Bundle b=getIntent().getExtras();
        homelist = b.getString("homelist");
        homelist = homelist.substring(1, homelist.length() - 1);
        List<String> myList = new ArrayList<String>(Arrays.asList(homelist.trim().split("\\s*,\\s*")));
        homelistdata = myList;*/

        ctvsb_my_service_request = findViewById(R.id.ctvsb_my_service_request);
        ctvsb_my_profile = findViewById(R.id.ctvsb_my_profile);
        ctvsb_payments = findViewById(R.id.ctvsb_payments);
        ctvsb_mark_absent = findViewById(R.id.ctvsb_mark_absent);
        ctvsb_ticket = findViewById(R.id.ctvsb_ticket);
        ctvsb_feedback = findViewById(R.id.ctvsb_feedback);
        ctvsb_signout = findViewById(R.id.ctvsb_signout);
        ctvsb_changepwd = findViewById(R.id.ctvsb_changepwd);

        linear_profile = findViewById(R.id.linear_profile);
        linear_offers = findViewById(R.id.linear_offers);
        linear_home = findViewById(R.id.linear_home);
        welcome_text = findViewById(R.id.welcome_text);
        loc_text = findViewById(R.id.loc_text);

        loc_ll = findViewById(R.id.loc_ll);
        spinner = findViewById(R.id.spinner);
        img_downarrow = findViewById(R.id.img_downarrow);

        iv_home = findViewById(R.id.iv_home);
        iv_offers = findViewById(R.id.iv_offers);
        iv_profile = findViewById(R.id.iv_profile);

        ctvb_profile = findViewById(R.id.ctvb_profile);
        ctvb_offers = findViewById(R.id.ctvb_offers);
        ctvb_home = findViewById(R.id.ctvb_home);


        ctvsb_contactus = findViewById(R.id.ctvsb_contactus);
        ctvsb_rateus = findViewById(R.id.ctvsb_rateus);
        ctvsb_aboutus = findViewById(R.id.ctvsb_aboutus);
        back_arrow = findViewById(R.id.back_arrow);
        iv_menu = findViewById(R.id.iv_menu);
        drawer_layout = findViewById(R.id.drawer_layout);
        nav_menu = findViewById(R.id.nav_menu);
        imageView = findViewById(R.id.notification_img_but);

        fragment = new HomeFragment();
        switchFragment(fragment);


        welcome_text.setText("Welcome " + username);
        loc_text.setText(userlocation);


        /*loc_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinner.performClick();
            }
        });

        img_downarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinner.performClick();


            }
        });*/

        Map<String, String> requestBody = new HashMap<>();
        presenter.user_locations(HomeScreenActivity.this, apiResponseCallback, requestBody, nUserType);


/*        Intent intent = getIntent();
        if (intent.getExtras() != null) {
//            String home = intent.getStringExtra("hello");
//            if (home.equalsIgnoreCase("hiii")) {
                fragment = new ProfileIndividualFragment();
                switchFragment(fragment);

                iv_home.setImageResource(R.drawable.home_gray);
                ctvb_home.setTextColor(getResources().getColor(R.color.text_gray));

                iv_offers.setImageResource(R.drawable.offer_gray);
                ctvb_offers.setTextColor(getResources().getColor(R.color.text_gray));

                iv_profile.setImageResource(R.drawable.profile_black);
                ctvb_profile.setTextColor(getResources().getColor(R.color.text_black));

 //           }
        }*/
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreenActivity.this, Notification_screen.class);
                startActivity(intent);
            }
        });
        linear_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newrequest = "homefrg";

                fragment = new HomeFragment();
                switchFragment(fragment);

                iv_home.setImageResource(R.drawable.home_black);
                ctvb_home.setTextColor(getResources().getColor(R.color.text_black));

                iv_offers.setImageResource(R.drawable.offer_gray);
                ctvb_offers.setTextColor(getResources().getColor(R.color.text_gray));

                iv_profile.setImageResource(R.drawable.profile_gray);
                ctvb_profile.setTextColor(getResources().getColor(R.color.text_gray));

            }
        });

        linear_offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new OffersFragment();
                switchFragment(fragment);

                iv_home.setImageResource(R.drawable.home_gray);
                ctvb_home.setTextColor(getResources().getColor(R.color.text_gray));

                iv_offers.setImageResource(R.drawable.offer_black);
                ctvb_offers.setTextColor(getResources().getColor(R.color.text_black));

                iv_profile.setImageResource(R.drawable.profile_gray);
                ctvb_profile.setTextColor(getResources().getColor(R.color.text_gray));

            }
        });

        linear_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                fragment = new ProfileIndividualFragment();
//                switchFragment(fragment);

                if (nUserType.equalsIgnoreCase("100")) {
                    fragment = new ProfileIndividualFragment();
                    switchFragment(fragment);

                } else {

                    fragment = new ProfileCompanyFragment();
                    switchFragment(fragment);

                }

                iv_home.setImageResource(R.drawable.home_gray);
                ctvb_home.setTextColor(getResources().getColor(R.color.text_gray));

                iv_offers.setImageResource(R.drawable.offer_gray);
                ctvb_offers.setTextColor(getResources().getColor(R.color.text_gray));

                iv_profile.setImageResource(R.drawable.profile_black);
                ctvb_profile.setTextColor(getResources().getColor(R.color.text_black));

            }
        });


     /*   linear_profile.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                fragment = new ProfileCompanyFragment();
                switchFragment(fragment);

                iv_home.setImageResource(R.drawable.home_gray);
                ctvb_home.setTextColor(getResources().getColor(R.color.text_gray));

                iv_offers.setImageResource(R.drawable.offer_gray);
                ctvb_offers.setTextColor(getResources().getColor(R.color.text_gray));

                iv_profile.setImageResource(R.drawable.profile_black);
                ctvb_profile.setTextColor(getResources().getColor(R.color.text_black));
                return true;
            }
        });*/

        ctvsb_aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_layout.closeDrawer(Gravity.LEFT);
                startActivity(new Intent(HomeScreenActivity.this, AboutUsActivity.class));
            }
        });
        ctvsb_rateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_layout.closeDrawer(Gravity.LEFT);
                startActivity(new Intent(HomeScreenActivity.this, RateandReviewActivity.class));
            }
        });
        ctvsb_contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_layout.closeDrawer(Gravity.LEFT);
                startActivity(new Intent(HomeScreenActivity.this, ContactUsActivity.class));
            }
        });
        ctvsb_my_service_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_layout.closeDrawer(Gravity.LEFT);
                startActivity(new Intent(HomeScreenActivity.this, My_Service_request_screen.class));
            }
        });
        ctvsb_my_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawer_layout.closeDrawer(Gravity.LEFT);

                if (nUserType.equalsIgnoreCase("100")) {
                    fragment = new ProfileIndividualFragment();
                    switchFragment(fragment);

                } else {

                    fragment = new ProfileCompanyFragment();
                    switchFragment(fragment);

                }

//                Intent intent = new Intent(HomeScreenActivity.this, HomeScreenActivity.class);
//                startActivity(intent);
            }
        });
        ctvsb_mark_absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_layout.closeDrawer(Gravity.LEFT);
                startActivity(new Intent(HomeScreenActivity.this, Mark_absent.class));
            }
        });
        ctvsb_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_layout.closeDrawer(Gravity.LEFT);
                startActivity(new Intent(HomeScreenActivity.this, Raise_tickets_screen.class));
            }
        });
        ctvsb_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_layout.closeDrawer(Gravity.LEFT);
                startActivity(new Intent(HomeScreenActivity.this, Feedback_domestic_help_screen.class));
            }
        });
        ctvsb_payments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_layout.closeDrawer(Gravity.LEFT);
                startActivity(new Intent(HomeScreenActivity.this, Navigation_payments_screen.class));
            }
        });
        ctvsb_changepwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_layout.closeDrawer(Gravity.LEFT);
                startActivity(new Intent(HomeScreenActivity.this, ResetPasswordActivity.class));
            }
        });
        ctvsb_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(HomeScreenActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Signout")
                        .setMessage("Are you sure want to signout?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Map<String, String> requestBody = new HashMap<>();
                                presenter.user_signout(HomeScreenActivity.this, apiResponseCallback, requestBody, nClientid);

                            }
                        })
                        .setNegativeButton("No", null)
                        .show();


            }
        });


        iv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer_layout.isDrawerOpen(Gravity.LEFT)) {
                    drawer_layout.closeDrawer(Gravity.LEFT);
                    getWindow().setSoftInputMode(android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                } else {
                    drawer_layout.openDrawer(Gravity.LEFT);

                }
            }
        });

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeScreenActivity.this, HomeScreenActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        //  Log.e("tagelse", "mnasxjk");

        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit App")
                .setMessage("Are you sure want to close this activity ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }
                })
                .setNegativeButton("No", null)
                .show();


    }


    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {

                Toast.makeText(HomeScreenActivity.this, "Please check your internet connection", Toast.LENGTH_LONG).show();


            } else if (NetworkConstants.RequestCode.USER_LOCATIONS == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {

                    JSONObject jsonObject1 = jsonObject.getJSONObject("data");

                    if (nUserType.equalsIgnoreCase("100")) {
                        JSONObject jsonObject2 = jsonObject1.getJSONObject("individualSelection");

                        JSONArray locarray = jsonObject2.getJSONArray("sLocation");

                        loclist.clear();
                        loclist.add("Select");
                        loclistids.add("");
//                    pricetypeids.clear();
                        int selected_position = -1;
                        String sm_locname = userSession.getLocDetails().get(UserSession.KEY_LOCNAME);


                        for (int i = 0; i < locarray.length(); i++) {
                            JSONObject jsonObjectt = locarray.getJSONObject(i);

                            sloc = jsonObjectt.getString("sLocationName");
                            slocid = jsonObjectt.getString("sLocationID");

                            if (sm_locname != null) {

                                if (sloc.equalsIgnoreCase(sm_locname)) {

                                    selected_position = i;
                                }
                            }

                            loclist.add(sloc);
                            loclistids.add(slocid);
                            //currancy_tv.setText(price);
                        }


                        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, loclist);

                        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
                        spinner.setAdapter(adapter);

                        if (sm_locname != null) {

                            //  sp_text.setText(selected_position+1);

                            spinner.setSelection(selected_position + 1);


                        }

                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                                String locname = loclist.get(position);

                                userSession.StoreLocDetails(locname);

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    } else {

                        JSONObject jsonObject2 = jsonObject1.getJSONObject("companySelection");

                        JSONArray locarray = jsonObject2.getJSONArray("sLocation");

                        loclist.clear();
                        loclist.add("Select");
                        loclistids.add("");
//                    pricetypeids.clear();
                        int selected_position = -1;
                        String sm_locname = userSession.getLocDetails().get(UserSession.KEY_LOCNAME);


                        for (int i = 0; i < locarray.length(); i++) {
                            JSONObject jsonObjectt = locarray.getJSONObject(i);

                            sloc = jsonObjectt.getString("sLocationName");
                            slocid = jsonObjectt.getString("sLocationID");

                            if (sm_locname != null) {

                                if (sloc.equalsIgnoreCase(sm_locname)) {

                                    selected_position = i;
                                }
                            }

                            loclist.add(sloc);
                            loclistids.add(slocid);
                            //currancy_tv.setText(price);
                        }


                        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, loclist);

                        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
                        spinner.setAdapter(adapter);

                        if (sm_locname != null) {

                            //  sp_text.setText(selected_position+1);

                            spinner.setSelection(selected_position + 1);


                        }

                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                                String locname = loclist.get(position);

                                userSession.StoreLocDetails(locname);

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    }

                } else {

                    //                   Util.getInstance().cusToast(HomeScreenActivity.this, jsonObject.optString("message"));
                }
            } else if (NetworkConstants.RequestCode.USER_SIGNOUT == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {

                    JSONObject jsonObject1 = jsonObject.getJSONObject("data");

                    Util.getInstance().cusToast(HomeScreenActivity.this, jsonObject1.optString("signoutmessage"));


                    try {
                        userSession.logoutUser();

                        Intent i = new Intent(HomeScreenActivity.this, LoginActivity.class);
                        startActivity(i);

                        //       ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_WELCOME_SCREEN,new Bundle());
                        finishAffinity();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {

                    Util.getInstance().cusToast(HomeScreenActivity.this, jsonObject.optString("status"));
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
