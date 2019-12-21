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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.vupadhi.heyhelp.Adapter.RaiseTicketsRecyclerAdapter;
import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.app.constants.AppConstants;
import com.vupadhi.heyhelp.app.controller.ApplicationController;
import com.vupadhi.heyhelp.base.BaseAbstractFragment;
import com.vupadhi.heyhelp.models.RaiseTicketsModel;
import com.vupadhi.heyhelp.mvp.contract.fragment.RaiseTicketFragmentContract;
import com.vupadhi.heyhelp.mvp.presenter.fragment.RaiseTicketfragmentImpl;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.sharepref.UserSession;
import com.vupadhi.heyhelp.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Raise_ticket_frag extends BaseAbstractFragment<RaiseTicketfragmentImpl> implements RaiseTicketFragmentContract, APIResponseCallback {
    LinearLayout linearLayout;
    RecyclerView category_rv;
    RaiseTicketsRecyclerAdapter adapter;
    APIResponseCallback apiResponseCallback;
    UserSession userSession;
    String nClientid;
    int count=0;
    RelativeLayout category_rl;
    LinearLayout recycler_ll;
    RaiseTicketsModel raiseTicketsModel;
    TextView category_tv;
    int position1;
    EditText comment_et;
    String categoryid="";

    public Raise_ticket_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View view= inflater.inflate(R.layout.fragment_raise_ticket_frag, container, false);

        return view;
    }

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_raise_ticket_frag, null);
        return view;
    }


    @Override
    protected void initialiseViews() {
        super.initialiseViews();

        apiResponseCallback = this;
        userSession = new UserSession(getActivity());
        nClientid = userSession.getUserDetails().get(UserSession.KEY_nCLIENTID);



        linearLayout = view.findViewById(R.id.raise_frag_submit_lay_out);
        category_rv = view.findViewById(R.id.category_rv);
        category_rl = view.findViewById(R.id.category_rl);
        recycler_ll = view.findViewById(R.id.recycler_ll);
        category_tv = view.findViewById(R.id.category_tv);
        comment_et = view.findViewById(R.id.comment_et);

        category_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (count==0){

                    recycler_ll.setVisibility(View.GONE);
                    count=1;
                }else {

                    recycler_ll.setVisibility(View.VISIBLE);
                    count=1;
                }
            }
        });

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (categoryid.isEmpty()||categoryid.equalsIgnoreCase(""))
                {
                    Toast.makeText(getActivity(), "Please select Category", Toast.LENGTH_SHORT).show();
                }else if (comment_et.getText().toString().length()==0)
                {
                    Toast.makeText(getActivity(), "Please enter Comments", Toast.LENGTH_SHORT).show();

                }else {
                    Map<String, String> requestBody = new HashMap<>();
                    requestBody.put("nClientID", nClientid);
                    requestBody.put("id", String.valueOf(raiseTicketsModel.getData().getServiceCategory().get(position1).getId()));
                    requestBody.put("data", comment_et.getText().toString());
                    presenter.saveraisetickets(getActivity(), apiResponseCallback, requestBody);
                }

//                Intent intent = new Intent(getActivity(), Raising_okay_screen.class);
//                startActivity(intent);
            }
        });

        Map<String, String> requestBody = new HashMap<>();
        presenter.raisetickets(getActivity(), apiResponseCallback, requestBody, nClientid);

    }

    @Override
    public void setPresenter() {
        presenter = new RaiseTicketfragmentImpl(this, getActivity());

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {

                Toast.makeText(getActivity(), "Please check your internet connection", Toast.LENGTH_LONG).show();


            } else if (NetworkConstants.RequestCode.RAISETICKETS == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {

                     raiseTicketsModel = new Gson().fromJson(responseString, RaiseTicketsModel.class);



                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    //setting horizontal list
                    mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    category_rv.setLayoutManager(mLayoutManager);
                    category_rv.setItemAnimator(new DefaultItemAnimator());


                    //Adding Adapter to recyclerView
                    adapter = new RaiseTicketsRecyclerAdapter(raiseTicketsModel, getActivity());
                    category_rv.setAdapter(adapter);

                    adapter.setOnItemClickListener(new RaiseTicketsRecyclerAdapter.OnitemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {

                            position1=position;
                            categoryid= String.valueOf(raiseTicketsModel.getData().getServiceCategory().get(position).getId());
                            category_tv.setText(raiseTicketsModel.getData().getServiceCategory().get(position).getName());
                            recycler_ll.setVisibility(View.GONE);
                        }
                    });


                } else {

                    Util.getInstance().cusToast(getActivity(), jsonObject.optString("message"));
                }
            }else if (NetworkConstants.RequestCode.SAVERAISETICKETS == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {
                    JSONObject jsonObject1=jsonObject.getJSONObject("data");

                    Toast.makeText(getActivity(),jsonObject1.getString("tkt_message"),Toast.LENGTH_LONG).show();
                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_HOME_SCREEN,new Bundle());

                } else {

                    Util.getInstance().cusToast(getActivity(), jsonObject.optString("message"));
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
