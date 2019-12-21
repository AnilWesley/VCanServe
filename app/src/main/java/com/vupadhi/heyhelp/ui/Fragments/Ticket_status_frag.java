package com.vupadhi.heyhelp.ui.Fragments;


import android.content.Intent;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.vupadhi.heyhelp.adapter.Ticket_satus_adapter;
import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.base.BaseAbstractFragment;
import com.vupadhi.heyhelp.models.TicketStatusModel;
import com.vupadhi.heyhelp.mvp.contract.fragment.TicketStatusFragmentContract;
import com.vupadhi.heyhelp.mvp.presenter.fragment.TicketStatusFragmentImpl;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.sharepref.UserSession;
import com.vupadhi.heyhelp.ui.activity.Raising_okay_screen;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Ticket_status_frag extends BaseAbstractFragment<TicketStatusFragmentImpl> implements TicketStatusFragmentContract, APIResponseCallback {
    LinearLayout linearLayout;
    RecyclerView recyclerView;
    Ticket_satus_adapter adapter;
    TicketStatusModel ticketStatusModel;
    APIResponseCallback apiResponseCallback;
    String nClientid;
    UserSession userSession;
    TextView nodata_tv;

    public Ticket_status_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View view= inflater.inflate(R.layout.fragment_ticket_status_frag, container, false);

        return view;
    }

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_ticket_status_frag, null);
        return view;
    }


    @Override
    protected void initialiseViews() {
        super.initialiseViews();

        apiResponseCallback = this;
        userSession = new UserSession(getActivity());
        nClientid = userSession.getUserDetails().get(UserSession.KEY_nCLIENTID);

        recyclerView = view.findViewById(R.id.ticket_status_recycle_view);
        linearLayout = view.findViewById(R.id.ticket_status_submit_lay_out);
        nodata_tv = view.findViewById(R.id.nodata_tv);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Raising_okay_screen.class);
                startActivity(intent);
            }
        });

        Map<String, String> requestBody = new HashMap<>();
        presenter.ticketstatus(getActivity(), apiResponseCallback, requestBody, nClientid);

    }

    @Override
    public void setPresenter() {

        presenter = new TicketStatusFragmentImpl(this, getActivity());

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {

                Toast.makeText(getActivity(), "Please check your internet connection", Toast.LENGTH_LONG).show();


            } else if (NetworkConstants.RequestCode.TICKETSTATUS == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {

                    ticketStatusModel = new Gson().fromJson(responseString, TicketStatusModel.class);
                    nodata_tv.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);

                    if (ticketStatusModel.getData().getTicketsStatus().size() > 0) {
                        nodata_tv.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);

                        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                        //setting horizontal list
                        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());

                        //Adding Adapter to recyclerView
                        adapter = new Ticket_satus_adapter(ticketStatusModel, getActivity());
                        recyclerView.setAdapter(adapter);
                    }else {

                        nodata_tv.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    }

                } else {

                    nodata_tv.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
//                    Util.getInstance().cusToast(getActivity(), jsonObject.optString("message"));
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
