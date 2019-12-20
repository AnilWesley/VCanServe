package com.vupadhi.heyhelp.ui.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.vupadhi.heyhelp.base.BaseAbstractActivity;
import com.vupadhi.heyhelp.mvp.contract.activity.RaiseTicketsScreenContract;
import com.vupadhi.heyhelp.mvp.presenter.activity.RaiseticketsScreenImpl;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.ui.Fragments.Raise_ticket_frag;
import com.vupadhi.heyhelp.ui.Fragments.Ticket_status_frag;
import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.customfonts.CustomTextViewBold;

public class Raise_tickets_screen extends BaseAbstractActivity<RaiseticketsScreenImpl> implements RaiseTicketsScreenContract.IView, APIResponseCallback {
    ImageView imageView;
    CustomTextViewBold customTextViewBold_one, customTextViewBold_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();


    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_raise_tickets_screen, null);
        return view;
    }

    public void fragment_paid() {
        Raise_ticket_frag raise_ticket_frag = new Raise_ticket_frag();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frag_one, raise_ticket_frag);
        transaction.commit();
    }

    public void fragment_pending() {
        Ticket_status_frag ticket_status_frag = new Ticket_status_frag();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frag_one, ticket_status_frag);
        transaction.commit();
    }

    @Override
    public void setPresenter() {

        presenter = new RaiseticketsScreenImpl(this, this);


    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        fragment_paid();
        imageView = findViewById(R.id.raise_back_but);
        customTextViewBold_one = findViewById(R.id.ticket_but);
        customTextViewBold_two = findViewById(R.id.status_but);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        customTextViewBold_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_paid();
                customTextViewBold_one.setTextColor(getResources().getColor(R.color.text_black));
                customTextViewBold_two.setTextColor(getResources().getColor(R.color.normal_gray));
            }
        });
        customTextViewBold_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_pending();
                customTextViewBold_one.setTextColor(getResources().getColor(R.color.normal_gray));
                customTextViewBold_two.setTextColor(getResources().getColor(R.color.text_black));
            }
        });
    }


    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }
}
