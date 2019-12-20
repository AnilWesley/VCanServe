package com.vupadhi.heyhelp.ui.activity;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.vupadhi.heyhelp.Adapter.RateandReviewRecyclerAdapter;
import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.app.constants.AppConstants;
import com.vupadhi.heyhelp.app.controller.ApplicationController;
import com.vupadhi.heyhelp.base.BaseAbstractActivity;
import com.vupadhi.heyhelp.customfonts.CustomTextViewBold;
import com.vupadhi.heyhelp.dao.AdapterratingCallback;
import com.vupadhi.heyhelp.models.RateandReviewModel;
import com.vupadhi.heyhelp.mvp.contract.activity.RateandReviewActivityContract;
import com.vupadhi.heyhelp.mvp.presenter.activity.RateandReviewActivityImpl;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.sharepref.UserSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RateandReviewActivity extends BaseAbstractActivity<RateandReviewActivityImpl> implements RateandReviewActivityContract.IView, APIResponseCallback, AdapterratingCallback {

    private ImageView back_arrow;
    private CustomTextViewBold ctvb_submit;
    RecyclerView workerslist_rv;
    RateandReviewRecyclerAdapter adapter;
    RateandReviewModel rateandReviewModel;

    AdapterratingCallback adapterratingCallback;

    TextView nodata_tv;

    APIResponseCallback apiResponseCallback;
    UserSession userSession;
    String nClientid, rating, ratingvalue1;
    int ratingg = 0;

    RatingBar ratingbar;
    EditText review_et;


    ArrayList<String> ratinglist, listt;

    JSONArray ratingvaluesarray = new JSONArray();
    JSONArray selectedlistarray = new JSONArray();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();


    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_rateand_review, null);
        return view;
    }

    @Override
    public void setPresenter() {

        presenter = new RateandReviewActivityImpl(this, this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        apiResponseCallback = this;
        userSession = new UserSession(RateandReviewActivity.this);
        nClientid = userSession.getUserDetails().get(UserSession.KEY_nCLIENTID);

        ratinglist = new ArrayList<>();


        adapterratingCallback = this;
        ctvb_submit = findViewById(R.id.ctvb_submit);
        back_arrow = findViewById(R.id.back_arrow);
        workerslist_rv = findViewById(R.id.workerslist_rv);
        nodata_tv = findViewById(R.id.nodata_tv);
        ratingbar = findViewById(R.id.ratingbar);
        review_et = findViewById(R.id.review_et);

        ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                if (rating == 1.0) {
//                    ratingvalue1 = "1.0";
                    ratingg = 1;
                } else if (rating == 2.0) {

//                    ratingvalue1 = "2.0";
                    ratingg = 2;
                } else if (rating == 3.0) {
//                    ratingvalue1 = "3.0";
                    ratingg = 3;

                } else if (rating == 4.0) {

//                    ratingvalue1 = "4.0";
                    ratingg = 4;

                } else if (rating == 5.0) {
//                    ratingvalue1 = "5.0";
                    ratingg = 5;
                }
                System.out.println("ghdfhagd" + ratingg);
            }
        });

        Map<String, String> requestBody = new HashMap<>();
        presenter.rated_workers(RateandReviewActivity.this, apiResponseCallback, requestBody, nClientid);


        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        ctvb_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    ratingvaluesarray = new JSONArray();
                    selectedlistarray = new JSONArray();
                    if (ratingg == 0) {
                        System.out.println("ratinggg" + ratingg);
                        Toast.makeText(RateandReviewActivity.this, "please give service rating", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (review_et.getText().toString().length() == 0) {
                        Toast.makeText(activity, "Please enter review", Toast.LENGTH_SHORT).show();
                    } else {
                        if (RateandReviewRecyclerAdapter.rateandReviewModel!=null) {
                            for (int i = 0; i < RateandReviewRecyclerAdapter.rateandReviewModel.getData().getWorkerRatingVM().getWorkerList().size(); i++) {
                                if (ratinglist.get(i).equalsIgnoreCase("0")) {
                                    Toast.makeText(activity, "Please give rating", Toast.LENGTH_SHORT).show();
                                    break;

                                } else {

                                    Toast.makeText(activity, "Given rating", Toast.LENGTH_SHORT).show();

                                    JSONObject json1 = new JSONObject();
                                    json1.put("nWorkerID", RateandReviewRecyclerAdapter.rateandReviewModel.getData().getWorkerRatingVM().getWorkerList().get(i).getNWorkerID());
                                    json1.put("nRatingValue", ratinglist.get(i));
                                    json1.put("nPartnerID", RateandReviewRecyclerAdapter.rateandReviewModel.getData().getWorkerRatingVM().getWorkerList().get(i).getNPartnerID());

                                    ratingvaluesarray.put(json1);

                                }

                            }
                        } else {

                            Toast.makeText(activity, "No workers found", Toast.LENGTH_SHORT).show();
                        }

                        JSONObject requestBody = new JSONObject();
                        requestBody.put("writeaReview", review_et.getText().toString());
                        requestBody.put("nClientID", Integer.parseInt(nClientid));
                        requestBody.put("nServiceRating", ratingg);
                        requestBody.put("workerratelist", ratingvaluesarray);
                        presenter.savecustomer_rating(RateandReviewActivity.this, apiResponseCallback, requestBody);

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


//                startActivity(new Intent(RateandReviewActivity.this, HomeScreenActivity.class));
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

                Toast.makeText(RateandReviewActivity.this, "Please check your internet connection", Toast.LENGTH_LONG).show();


            } else if (NetworkConstants.RequestCode.RATED_WORKERS == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {

                    rateandReviewModel = new Gson().fromJson(responseString, RateandReviewModel.class);

                    nodata_tv.setVisibility(View.GONE);
                    workerslist_rv.setVisibility(View.VISIBLE);

                    if (rateandReviewModel.getData().getWorkerRatingVM().getWorkerList().size() > 0) {

                        for (int i = 0; i < rateandReviewModel.getData().getWorkerRatingVM().getWorkerList().size(); i++) {

                            ratinglist.add("0");
                        }

                        nodata_tv.setVisibility(View.GONE);
                        workerslist_rv.setVisibility(View.VISIBLE);

                        LinearLayoutManager mLayoutManager = new LinearLayoutManager(RateandReviewActivity.this);
                        //setting horizontal list
                        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        workerslist_rv.setLayoutManager(mLayoutManager);
                        workerslist_rv.setItemAnimator(new DefaultItemAnimator());


                        //Adding Adapter to recyclerView
                        adapter = new RateandReviewRecyclerAdapter(rateandReviewModel, RateandReviewActivity.this, adapterratingCallback);
                        workerslist_rv.setAdapter(adapter);


                        adapter.setOnItemClickListener(new RateandReviewRecyclerAdapter.OnitemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {


                            }
                        });

                    } else {
                        nodata_tv.setVisibility(View.VISIBLE);
                        workerslist_rv.setVisibility(View.GONE);
                    }

                } else {

                    nodata_tv.setVisibility(View.VISIBLE);
                    workerslist_rv.setVisibility(View.GONE);
                    //                  Util.getInstance().cusToast(Mark_absent.this, jsonObject.optString("message"));
                }
            } else if (NetworkConstants.RequestCode.SAVECUSTOMER_RATING == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {


                    Toast.makeText(activity, "Rating submitted successfully", Toast.LENGTH_SHORT).show();

                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_HOME_SCREEN, new Bundle());

                } else {

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
    public void onClickCallback(String ratingvalue, int position, View view) {


        rating = ratingvalue;


        ratinglist.set(position, ratingvalue);

        Log.d("ratinglist...", ratinglist.toString());


       /* for (int i = 0; i < RateandReviewRecyclerAdapter.rateandReviewModel.getData().getWorkerRatingVM().getWorkerList().size(); i++) {

            if (i==position) {

                ratinglist.add(ratingvalue);
            }
            Log.d("ratinglist...",ratinglist.toString());

        }*/
    }
}
