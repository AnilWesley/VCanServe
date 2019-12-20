package com.vupadhi.heyhelp.ui.activity;

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
import android.widget.Toast;

import com.google.gson.Gson;
import com.vupadhi.heyhelp.Adapter.FeedbackRecyclerAdapter;
import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.app.constants.AppConstants;
import com.vupadhi.heyhelp.app.controller.ApplicationController;
import com.vupadhi.heyhelp.base.BaseAbstractActivity;
import com.vupadhi.heyhelp.dao.AdapterCallback;
import com.vupadhi.heyhelp.models.FeedbackModel;
import com.vupadhi.heyhelp.mvp.contract.activity.FeedbackDomesticHelpScreenContract;
import com.vupadhi.heyhelp.mvp.presenter.activity.FeedbackDomesticHelpScreenImpl;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.sharepref.UserSession;
import com.vupadhi.heyhelp.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Feedback_domestic_help_screen extends BaseAbstractActivity<FeedbackDomesticHelpScreenImpl> implements FeedbackDomesticHelpScreenContract.IView, APIResponseCallback, AdapterCallback {
    ImageView imageView;
    LinearLayout linearLayout;
    RecyclerView feedback_rv;
    FeedbackRecyclerAdapter adapter;
    AdapterCallback adapterCallback;
    FeedbackModel feedbackModel;



    APIResponseCallback apiResponseCallback;
    UserSession userSession;
    String nClientid;

    JSONArray selectedlistarray=new JSONArray();
    JSONArray answerslistarray=new JSONArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();

    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_feedback_domestic_help_screen, null);
        return view;
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        apiResponseCallback = this;
        userSession = new UserSession(Feedback_domestic_help_screen.this);
        nClientid = userSession.getUserDetails().get(UserSession.KEY_nCLIENTID);

        adapterCallback = this;

        imageView = findViewById(R.id.feedback_back_but);
        feedback_rv = findViewById(R.id.feedback_rv);
        linearLayout = findViewById(R.id.feedback_save_changes);
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

                    answerslistarray = new JSONArray();
                    selectedlistarray = new JSONArray();
                    int answercount=0;
                    for (int i=0;i<FeedbackRecyclerAdapter.feedbackModel.getData().getFeedback().getFQuestionList().size();i++)
                    {
                        for (int j=0;j<FeedbackRecyclerAdapter.feedbackModel.getData().getFeedback().getFQuestionList().get(i).getFAnswersList().size();j++)
                        {
                            Boolean isselected = FeedbackRecyclerAdapter.feedbackModel.getData().getFeedback().getFQuestionList().get(i).getFAnswersList().get(j).isSelected();

                            if (isselected)
                            {
                                answercount=answercount+1;
                            }
                        }
                    }

                    if (answercount<FeedbackRecyclerAdapter.feedbackModel.getData().getFeedback().getFQuestionList().size()){

                        Toast.makeText(activity, "Please choose one option for all questions", Toast.LENGTH_SHORT).show();
                    }else {

                        Toast.makeText(activity, "answered all questions", Toast.LENGTH_SHORT).show();

                        for (int i=0;i<FeedbackRecyclerAdapter.feedbackModel.getData().getFeedback().getFQuestionList().size();i++) {
                            for (int j = 0; j < FeedbackRecyclerAdapter.feedbackModel.getData().getFeedback().getFQuestionList().get(i).getFAnswersList().size(); j++) {
                                JSONObject json1 = new JSONObject();
                                json1.put("sAnswerID", FeedbackRecyclerAdapter.feedbackModel.getData().getFeedback().getFQuestionList().get(i).getFAnswersList().get(j).getSAnswerID());
                                json1.put("sAnswerName",FeedbackRecyclerAdapter.feedbackModel.getData().getFeedback().getFQuestionList().get(i).getFAnswersList().get(j).getSAnswerName());

                                answerslistarray.put(json1);
                            }

                            JSONObject json = new JSONObject();
                            json.put("sName",FeedbackRecyclerAdapter.feedbackModel.getData().getFeedback().getFQuestionList().get(i).getSName());
                            json.put("id", FeedbackRecyclerAdapter.feedbackModel.getData().getFeedback().getFQuestionList().get(i).getId());
                            json.put("fAnswerList", answerslistarray);

                            selectedlistarray.put(json);

                        }

                        JSONObject requestBody = new JSONObject();
                        requestBody.put("feedbackquestions",selectedlistarray);
                        requestBody.put("nClientID",nClientid);
                        presenter.savefeedback(Feedback_domestic_help_screen.this, apiResponseCallback, requestBody);

                    }

                }catch (Exception e)
                {
                    e.printStackTrace();
                }


//                Intent intent = new Intent(Feedback_domestic_help_screen.this, HomeScreenActivity.class);
//                startActivity(intent);
            }
        });

        Map<String, String> requestBody = new HashMap<>();
        presenter.feedback_questions(Feedback_domestic_help_screen.this, apiResponseCallback, requestBody, nClientid);

    }

    @Override
    public void setPresenter() {
        presenter = new FeedbackDomesticHelpScreenImpl(this, this);

    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {

                Toast.makeText(Feedback_domestic_help_screen.this, "Please check your internet connection", Toast.LENGTH_LONG).show();


            } else if (NetworkConstants.RequestCode.FEEDBACK_QUESTIONS == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {

                    feedbackModel = new Gson().fromJson(responseString, FeedbackModel.class);


                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(Feedback_domestic_help_screen.this);
                    //setting horizontal list
                    mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    feedback_rv.setLayoutManager(mLayoutManager);
                    feedback_rv.setItemAnimator(new DefaultItemAnimator());


                    //Adding Adapter to recyclerView
                    adapter = new FeedbackRecyclerAdapter(feedbackModel, Feedback_domestic_help_screen.this, adapterCallback);
                    feedback_rv.setAdapter(adapter);

                    adapter.setOnItemClickListener(new FeedbackRecyclerAdapter.OnitemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {


                        }
                    });


                } else {

                    Util.getInstance().cusToast(Feedback_domestic_help_screen.this, jsonObject.optString("message"));
                }
            }else if (NetworkConstants.RequestCode.SAVEFEEDBACK == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {

                    JSONObject jsonObject1=jsonObject.getJSONObject("data");

                    Util.getInstance().cusToast(Feedback_domestic_help_screen.this, jsonObject1.optString("feedbackMsg"));

                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_HOME_SCREEN,new Bundle());

                } else {

                    Util.getInstance().cusToast(Feedback_domestic_help_screen.this, jsonObject.optString("message"));
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
    public void onClickCallback(int groupposition, int childposition, View view) {


        for (int i = 0; i < FeedbackRecyclerAdapter.feedbackModel.getData().getFeedback().getFQuestionList().size(); i++) {

            for (int j = 0; j < FeedbackRecyclerAdapter.feedbackModel.getData().getFeedback().getFQuestionList().get(i).getFAnswersList().size(); j++) {

                if (i == groupposition) {
                    if (childposition == j) {
                        Boolean isselected = FeedbackRecyclerAdapter.feedbackModel.getData().getFeedback().getFQuestionList().get(i).getFAnswersList().get(j).isSelected();
                        if (isselected) {
                            FeedbackRecyclerAdapter.feedbackModel.getData().getFeedback().getFQuestionList().get(i).getFAnswersList().get(j).setSelected(false);
                        } else {
                            FeedbackRecyclerAdapter.feedbackModel.getData().getFeedback().getFQuestionList().get(i).getFAnswersList().get(j).setSelected(true);

                        }
                    } else {
                        FeedbackRecyclerAdapter.feedbackModel.getData().getFeedback().getFQuestionList().get(i).getFAnswersList().get(j).setSelected(false);

                    }

                } else {

                }

            }

        }


        // adapter.notifyDataSetChanged();
        adapter.notifyItemChanged(groupposition);
    }
}
