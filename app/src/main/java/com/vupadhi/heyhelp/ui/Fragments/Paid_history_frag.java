package com.vupadhi.heyhelp.ui.Fragments;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.vupadhi.heyhelp.Adapter.PaidHistoryRecyclerAdapter;
import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.base.BaseAbstractFragment;
import com.vupadhi.heyhelp.models.PaidHistoryModel;
import com.vupadhi.heyhelp.mvp.contract.fragment.PaidHistoryfragmentContract;
import com.vupadhi.heyhelp.mvp.presenter.fragment.PaidHistoryfragmentImpl;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.sharepref.UserSession;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Paid_history_frag extends BaseAbstractFragment<PaidHistoryfragmentImpl> implements PaidHistoryfragmentContract, APIResponseCallback {

    RecyclerView paidhistory_rv;
    PaidHistoryRecyclerAdapter adapter;

    APIResponseCallback apiResponseCallback;
    UserSession userSession;
    String nClientid;
    TextView nodata_tv;


    public Paid_history_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_paid_history_frag, null);
        return view;
    }

    @Override
    protected void initialiseViews() {
        super.initialiseViews();

        apiResponseCallback = this;
        userSession = new UserSession(getActivity());
        nClientid = userSession.getUserDetails().get(UserSession.KEY_nCLIENTID);


        paidhistory_rv=view.findViewById(R.id.paidhistory_rv);
        nodata_tv=view.findViewById(R.id.nodata_tv);

        Map<String, String> requestBody = new HashMap<>();
        presenter.paidhistory_inform(getActivity(), apiResponseCallback, requestBody, nClientid);

    }

    @Override
    public void setPresenter() {
        presenter = new PaidHistoryfragmentImpl(this, getActivity());

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {

                Toast.makeText(getActivity(), "Please check your internet connection", Toast.LENGTH_LONG).show();


            } else if (NetworkConstants.RequestCode.PAIDHISTORY_INFORM == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {

                    PaidHistoryModel paidHistoryModel = new Gson().fromJson(responseString, PaidHistoryModel.class);

                    paidhistory_rv.setVisibility(View.VISIBLE);
                    nodata_tv.setVisibility(View.GONE);

                    if (paidHistoryModel.getData().getPaidHistory().size()>0) {

                        paidhistory_rv.setVisibility(View.VISIBLE);
                        nodata_tv.setVisibility(View.GONE);

                        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                        //setting horizontal list
                        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        paidhistory_rv.setLayoutManager(mLayoutManager);
                        paidhistory_rv.setItemAnimator(new DefaultItemAnimator());


                        //Adding Adapter to recyclerView
                        adapter = new PaidHistoryRecyclerAdapter(paidHistoryModel, getActivity());
                        paidhistory_rv.setAdapter(adapter);
                    }else {

                        paidhistory_rv.setVisibility(View.GONE);
                        nodata_tv.setVisibility(View.VISIBLE);
                    }


                } else {

                    paidhistory_rv.setVisibility(View.GONE);
                    nodata_tv.setVisibility(View.VISIBLE);
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
