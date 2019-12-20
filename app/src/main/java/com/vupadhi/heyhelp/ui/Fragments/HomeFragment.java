package com.vupadhi.heyhelp.ui.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.vupadhi.heyhelp.Adapter.HomeSlideAdapter;
import com.vupadhi.heyhelp.base.BaseAbstractFragment;
import com.vupadhi.heyhelp.mvp.contract.fragment.HomefragmentContract;
import com.vupadhi.heyhelp.mvp.presenter.fragment.HomefragmentImpl;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.sharepref.UserSession;
import com.vupadhi.heyhelp.ui.activity.DomesticHelpMaidActivity;
import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.ui.activity.HomeScreenActivity;
import com.vupadhi.heyhelp.ui.activity.LoginActivity;
import com.vupadhi.heyhelp.ui.activity.Mark_absent;
import com.vupadhi.heyhelp.ui.activity.Navigation_payments_screen;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class HomeFragment extends BaseAbstractFragment<HomefragmentImpl> implements HomefragmentContract, APIResponseCallback {

    private ViewPager viewPager;
    private LinearLayout vllDost;
    LinearLayout linearLayout_one, linearLayout_two, linearLayout_three, linearLayout_four, linearLayout_five;
    private TextView[] mdots;
    APIResponseCallback apiResponseCallback;
    private HomeSlideAdapter homeSlideAdapter;
    Context context;
    String nClientid;
    UserSession userSession;
    TextView tv1, tv2, tv3, tv4, tv5;

    LinearLayout servicereq_lay_out, payments_lay_out, mark_absent_lay_out, services1_ll, services2_ll;

    ArrayList<String> bottomlist, datalist, datalistids;


    public int[] slide_images = {
            R.drawable.service_one,
            R.drawable.service_two,
            R.drawable.service_one,
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//          View view = inflater.inflate(R.layout.home_fragment, container, false);


        return view;
    }

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.home_fragment, null);
        return view;
    }

    @Override
    protected void initialiseViews() {
        super.initialiseViews();
        apiResponseCallback = this;
        userSession = new UserSession(getActivity());
        nClientid = userSession.getUserDetails().get(UserSession.KEY_nCLIENTID);

        viewPager = view.findViewById(R.id.home_fragment_viewpager);
        linearLayout_one = view.findViewById(R.id.help_maid_lay_out);
        linearLayout_two = view.findViewById(R.id.child_care_lay_out);
        linearLayout_three = view.findViewById(R.id.elder_care_lay_out);
        linearLayout_four = view.findViewById(R.id.cook_lay_out);
        linearLayout_five = view.findViewById(R.id.security_lay_out);

        servicereq_lay_out = view.findViewById(R.id.servicereq_lay_out);
        payments_lay_out = view.findViewById(R.id.payments_lay_out);
        mark_absent_lay_out = view.findViewById(R.id.mark_absent_lay_out);
        services1_ll = view.findViewById(R.id.services1_ll);
        services2_ll = view.findViewById(R.id.services2_ll);

        tv1 = view.findViewById(R.id.tv1);
        tv2 = view.findViewById(R.id.tv2);
        tv3 = view.findViewById(R.id.tv3);
        tv4 = view.findViewById(R.id.tv4);
        tv5 = view.findViewById(R.id.tv5);

        bottomlist = new ArrayList<>();
        datalist = new ArrayList<>();
        datalistids = new ArrayList<>();


        if (HomeScreenActivity.newrequest != null) {
            if (HomeScreenActivity.newrequest.equalsIgnoreCase("newrequest")) {
                services1_ll.setVisibility(View.GONE);
                services2_ll.setVisibility(View.VISIBLE);

            } else {
                services1_ll.setVisibility(View.VISIBLE);
                services2_ll.setVisibility(View.GONE);
            }
        }

        servicereq_lay_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                services1_ll.setVisibility(View.GONE);
                services2_ll.setVisibility(View.VISIBLE);

            }
        });
        payments_lay_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), Navigation_payments_screen.class));

            }
        });
        mark_absent_lay_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), Mark_absent.class));

            }
        });

        homeSlideAdapter = new HomeSlideAdapter(this.getActivity());
        vllDost = view.findViewById(R.id.vDots);
        setupDotBar(slide_images.length);
        viewPagerButtons();
        viewPager.setAdapter(homeSlideAdapter);


        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("dummy", "dummy");
        presenter.homescreen(getActivity(), apiResponseCallback, requestBody, nClientid);


        linearLayout_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DomesticHelpMaidActivity.class);
                intent.putExtra("nServiceid", datalistids.get(0));
                startActivity(intent);
            }
        });
        linearLayout_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DomesticHelpMaidActivity.class);
                intent.putExtra("nServiceid", datalistids.get(1));
                startActivity(intent);
            }
        });
        linearLayout_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DomesticHelpMaidActivity.class);
                intent.putExtra("nServiceid", datalistids.get(2));
                startActivity(intent);
            }
        });
        linearLayout_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DomesticHelpMaidActivity.class);
                intent.putExtra("nServiceid", datalistids.get(3));
                startActivity(intent);
            }
        });
        linearLayout_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DomesticHelpMaidActivity.class);
                intent.putExtra("nServiceid", datalistids.get(4));
                startActivity(intent);
            }
        });

    }

    public void viewPagerButtons() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (vllDost != null && vllDost.getTag() != null) {
                    ((ImageView) vllDost.getTag()).setImageResource(R.drawable.dot_gray);
                    ((ImageView) vllDost.getChildAt(position)).setImageResource(R.drawable.dot_white);
                    vllDost.setTag(vllDost.getChildAt(position));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void setupDotBar(final Integer length) {
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(20, 20);
        param.setMargins(5, 0, 5, 0);
        vllDost.removeAllViews();
        for (int i = 0; i < length; i++) {
            int i2;
            ImageView img = new ImageView(this.getActivity());
/*LinearLayout.LayoutParams ivParams = new LinearLayout.LayoutParams(5,5);
img.setLayoutParams(ivParams);*/
            if (i == 0) {
                i2 = R.drawable.dot_gray;
            } else {
                i2 = R.drawable.dot_white;
            }
            img.setImageResource(i2);
            img.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            vllDost.addView(img, param);
            if (i == 0) {
                vllDost.setTag(img);
            }
        }
    }

    @Override
    public void setPresenter() {
        presenter = new HomefragmentImpl(this, getActivity());

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {

                Toast.makeText(getActivity(), "Please check your internet connection", Toast.LENGTH_LONG).show();


            } else if (NetworkConstants.RequestCode.HOMESCREEN == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {


                    JSONObject datajsonObject = jsonObject.getJSONObject("data");

                    JSONArray jsonArray = datajsonObject.getJSONArray("servicetypelist");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String id = jsonObject1.getString("nServiceID");
                        String servicename = jsonObject1.getString("sServiceName");

                        datalist.add(servicename);
                        datalistids.add(id);
                    }

                    TextView[] tv = new TextView[5];
                    tv[0] = tv1;
                    tv[1] = tv2;
                    tv[2] = tv3;
                    tv[3] = tv4;
                    tv[4] = tv5;


                    for (int i = 0; i < datalist.size(); i++) {
                        tv[i].setText(datalist.get(i));
                    }


                    JSONArray jsonArray1 = datajsonObject.getJSONArray("lowerMenuBar");
                    for (int i = 0; i < jsonArray1.length(); i++) {
                        JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                        String name = jsonObject1.getString("name");

                        bottomlist.add(name);
                    }

                } else {

                    new AlertDialog.Builder(getActivity())
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle("Your account is logged in by another user")
                            .setMessage("Please Logout")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    try {
                                        userSession.logoutUser();

                                        Intent i = new Intent(getActivity(), LoginActivity.class);
                                        startActivity(i);

                                        //       ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_WELCOME_SCREEN,new Bundle());
                                        getActivity().finishAffinity();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }
                            })
                           // .setNegativeButton("No", null)
                            .show();


                    //                   Util.getInstance().cusToast(getActivity(), jsonObject.optString("message"));
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
