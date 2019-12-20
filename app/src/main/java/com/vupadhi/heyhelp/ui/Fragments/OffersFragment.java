package com.vupadhi.heyhelp.ui.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.vupadhi.heyhelp.Adapter.OffersRecyclerAdapter;
import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.base.BaseAbstractFragment;
import com.vupadhi.heyhelp.models.OffersModel;
import com.vupadhi.heyhelp.mvp.contract.fragment.OffersfragmentContract;
import com.vupadhi.heyhelp.mvp.presenter.fragment.OffersfragmentImpl;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.sharepref.UserSession;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class OffersFragment extends BaseAbstractFragment<OffersfragmentImpl> implements OffersfragmentContract, APIResponseCallback {

    OffersRecyclerAdapter offerAdapter;
    private RecyclerView offer_recycler;
    APIResponseCallback apiResponseCallback;
    UserSession userSession;
    String nClientid;
    OffersModel offersModel;
    TextView nodata_tv;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //       View view = inflater.inflate(R.layout.offers_fragment, container, false);


        return view;
    }

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.offers_fragment, null);
        return view;
    }


    @Override
    protected void initialiseViews() {
        super.initialiseViews();
        offer_recycler = view.findViewById(R.id.offer_recycler);
        nodata_tv = view.findViewById(R.id.nodata_tv);
        apiResponseCallback = this;
        userSession = new UserSession(getActivity());
        nClientid = userSession.getUserDetails().get(UserSession.KEY_nCLIENTID);


        Map<String, String> requestBody = new HashMap<>();
        presenter.promotions_offers(getActivity(), apiResponseCallback, requestBody, nClientid);


    }

    @Override
    public void setPresenter() {

        presenter = new OffersfragmentImpl(this, getActivity());

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {

                Toast.makeText(getActivity(), "Please check your internet connection", Toast.LENGTH_LONG).show();


            } else if (NetworkConstants.RequestCode.PROMOTIONS_OFFERS == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {

                    offersModel = new Gson().fromJson(responseString, OffersModel.class);

                    nodata_tv.setVisibility(View.GONE);
                    offer_recycler.setVisibility(View.VISIBLE);
                    if (offersModel.getData().getPromotionsAndOffers().size() > 0) {
                        nodata_tv.setVisibility(View.GONE);
                        offer_recycler.setVisibility(View.VISIBLE);

                        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                        //setting horizontal list
                        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        offer_recycler.setLayoutManager(mLayoutManager);
                        offer_recycler.setItemAnimator(new DefaultItemAnimator());


                        //Adding Adapter to recyclerView
                        offerAdapter = new OffersRecyclerAdapter(offersModel, getActivity());
                        offer_recycler.setAdapter(offerAdapter);
                    }else {

                        nodata_tv.setVisibility(View.VISIBLE);
                        offer_recycler.setVisibility(View.GONE);
                    }


                } else {

                    nodata_tv.setVisibility(View.VISIBLE);
                    offer_recycler.setVisibility(View.GONE);
   //                 Util.getInstance().cusToast(getActivity(), jsonObject.optString("message"));
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
